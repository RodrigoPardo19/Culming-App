package com.ufro.culmingapp.security;

import com.ufro.culmingapp.administrator.application.AdministratorFinder;
import com.ufro.culmingapp.administrator.domain.Administrator;
import com.ufro.culmingapp.student.application.StudentFinderService;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.teacher.application.TeacherFinderService;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.tutor.application.TutorFinder;
import com.ufro.culmingapp.tutor.domain.Tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherFinderService finder;

    @Autowired
    private StudentFinderService studentFinder;

    @Autowired
    private TutorFinder tutorFinder;

    @Autowired
    private AdministratorFinder adminFinder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String role = "";
        String password = "";

        try {
            Optional<Teacher> optionalTeacher = finder.getByEmail(email);
            if (optionalTeacher.isPresent()) {
                Teacher teacher = optionalTeacher.get();
                role = teacher.getRole().getAuthority();
                password = teacher.getPassword().getPassword();
            } else {
                Optional<Student> optionalStudent = studentFinder.getByEmail(email);
                if (optionalStudent.isPresent()) {
                    Student student = optionalStudent.get();
                    role = student.getRole().getAuthority();
                    password = student.getPassword().getPassword();
                } else {
                    Optional<Tutor> optionalTutor = tutorFinder.getByEmail(email);
                    if (optionalTutor.isPresent()) {
                        Tutor tutor = optionalTutor.get();
                        role = tutor.getRole().getAuthority();
                        password = tutor.getPassword().getPassword();
                    } else {
                        Optional<Administrator> optionalAdmin = adminFinder.getByEmail(email);
                        if (optionalAdmin.isPresent()) {
                            Administrator admin = optionalAdmin.get();
                            role = admin.getRole().getAuthority();
                            password = admin.getPassword().getPassword();
                        }
                    }
                }
            }

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            return new User(email, password, true, true, true, true, authorities);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User has not been found: " + email);
        }
    }
}
