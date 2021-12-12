package com.ufro.culmingapp.administrator.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    @Query("SELECT a FROM Administrator a WHERE a.email.email = :email")
    Optional<Administrator> fetchByEmail(@Param("email") String email);

}
