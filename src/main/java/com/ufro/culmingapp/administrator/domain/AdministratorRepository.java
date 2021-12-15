package com.ufro.culmingapp.administrator.domain;

import java.util.Optional;

import com.ufro.culmingapp.administrator.application.DTOs.AdminHomeDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    @Query("SELECT a FROM Administrator a WHERE a.email.email = :email")
    Optional<Administrator> fetchByEmail(@Param("email") String email);

    @Query("SELECT new com.ufro.culmingapp.administrator.application.DTOs.AdminHomeDTO(a.id, a.fullName) FROM Administrator a WHERE a.id = :adminId")
    Optional<AdminHomeDTO> fetchAdminHomeById(@Param("adminId") Long adminId);
}
