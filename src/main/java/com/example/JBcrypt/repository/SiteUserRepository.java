package com.example.JBcrypt.repository;


import com.example.JBcrypt.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long>
{
    SiteUser findByUsername(String username);
}