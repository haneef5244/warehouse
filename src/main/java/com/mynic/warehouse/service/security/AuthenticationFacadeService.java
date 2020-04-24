package com.mynic.warehouse.service.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationFacadeService {

    public Authentication getAuthentication() {
        log.info("getAuthentication()");
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
