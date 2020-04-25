package com.mynic.warehouse.service.user;

import com.mynic.warehouse.constant.RoleType;
import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.entity.Role;
import com.mynic.warehouse.entity.User;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.user.CreateUserReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.repository.RoleRepository;
import com.mynic.warehouse.repository.UserRepository;
import com.mynic.warehouse.service.AbstractMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CreateUserService extends AbstractMainService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public MainReq process(MainReq req) {
        CreateUserReq createUserReq = (CreateUserReq) req;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        createUserReq.setPassword(passwordEncoder.encode(createUserReq.getPassword()));
        log.info("encoded pass="+createUserReq.getPassword());
        return createUserReq;
    }

    @Override
    public MainReq update(MainReq req) {
        CreateUserReq createUserReq = (CreateUserReq) req;
        List<Role> roles = createUserReq.getRoles().stream().map(role -> roleRepository.findByName(RoleType.valueOf(role))).collect(Collectors.toList());
        User entity = User.builder().name(createUserReq.getName())
                .password(createUserReq.getPassword())
                .username(createUserReq.getUsername())
                .roles(new HashSet<>(roles))
                .build();
        try {
            repository.save(entity);
            createUserReq.setStatus(Status.SUCCESS);
        } catch (Exception e) {
            createUserReq.setStatus(Status.INTERNAL_SERVER_ERROR);
        }
        return createUserReq;
    }



    @Override
    public MainResp buildResponse(MainResp resp) {
        return resp;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByUsername(s).get(0);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set grantedAuthorities = getAuthorities(user);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private Set getAuthorities(User user) {
        Set<Role> roleByUserId = user.getRoles();
        final Set authorities = roleByUserId.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase())).collect(Collectors.toSet());
        return authorities;
    }


}
