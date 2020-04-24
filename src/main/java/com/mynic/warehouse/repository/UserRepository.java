package com.mynic.warehouse.repository;

import com.mynic.warehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameAndPassword(String username, String password);

    List<User> findByUsername(String username);

    List<User> findByIdAndUsernameAndPassword(Long id, String username, String password);

    void deleteById(Long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u " +
            "       SET u.name=:name" +
            "     WHERE u.username=:username" +
            "       AND u.password=:password")
    void updateUser(@Param("name") String name,
                    @Param("username") String username,
                    @Param("password") String password);
}

