package com.enset.authentificationservice.security.repository;

import com.enset.authentificationservice.security.entitis.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
