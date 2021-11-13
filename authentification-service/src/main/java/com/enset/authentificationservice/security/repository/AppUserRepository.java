package com.enset.authentificationservice.security.repository;

import com.enset.authentificationservice.security.entitis.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String userName);
}
