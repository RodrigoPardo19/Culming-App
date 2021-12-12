package com.ufro.culmingapp.Role.application;

import java.util.Optional;

import com.ufro.culmingapp.Role.domain.Role;
import com.ufro.culmingapp.Role.domain.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleFinder {

    @Autowired
    private RoleRepository repository;

    public Role getRole(String authority) {
        Optional<Role> role = repository.findByAuthority(authority);
        return role.get();
    }

}
