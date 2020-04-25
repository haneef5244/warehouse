package com.mynic.warehouse.repository;

import com.mynic.warehouse.constant.RoleType;
import com.mynic.warehouse.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

     @Query("SELECT r FROM Role r " +
             "       WHERE r.name=:name")
     Role findByName(@Param("name") RoleType name);
}
