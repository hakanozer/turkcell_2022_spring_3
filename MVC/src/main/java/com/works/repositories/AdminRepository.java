package com.works.repositories;

import com.works.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // select * from admin where email = ?
    // Optional<Admin> findByEmailEquals(String email);

    Optional<Admin> findByEmailEqualsIgnoreCase(String email);



}