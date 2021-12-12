package com.ufro.culmingapp.administrator.application;

import java.util.Optional;

import com.ufro.culmingapp.administrator.domain.Administrator;
import com.ufro.culmingapp.administrator.domain.AdministratorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorFinder {

    @Autowired
    private AdministratorRepository repository;

    public Optional<Administrator> getByEmail(String email) {
        return repository.fetchByEmail(email);
    }

}
