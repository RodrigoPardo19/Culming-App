package com.ufro.culmingapp.administrator.infrastructure;

import com.ufro.culmingapp.administrator.application.AdministratorFinder;
import com.ufro.culmingapp.administrator.application.DTOs.AdminHomeDTO;
import com.ufro.culmingapp.administrator.domain.exceptions.AdminNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class AdminGetController {

    @Autowired
    private AdministratorFinder finder;

    @GetMapping("/admins/{id}/home")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getAdminHome(@PathVariable Long id) {
        try {
            AdminHomeDTO adminHome = finder.findAdminHome(id);
            return ResponseEntity.status(HttpStatus.OK).body(adminHome);
        } catch (AdminNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
        }
    }

}
