package com.ufro.culmingapp.security;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufro.culmingapp.administrator.application.AdministratorFinder;
import com.ufro.culmingapp.administrator.domain.Administrator;
import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.student.application.StudentFinderService;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.studentcourse.domain.StudentCourse;
import com.ufro.culmingapp.studentcourse.domain.StudentCourseRepository;
import com.ufro.culmingapp.teacher.application.TeacherFinderService;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherAuthenticationDTO;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.tutor.application.TutorFinder;
import com.ufro.culmingapp.tutor.domain.Tutor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    private AdministratorFinder adminFinder;

    @Autowired
    private StudentFinderService studentFinder;

    @Autowired
    private TeacherFinderService teacherFinder;

    @Autowired
    private TutorFinder tutorFinder;

    @Autowired
    private CourseFinderService courseFinder;

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext context) {
        this.authenticationManager = authenticationManager;
        this.adminFinder = context.getBean(AdministratorFinder.class);
        this.studentFinder = context.getBean(StudentFinderService.class);
        this.teacherFinder = context.getBean(TeacherFinderService.class);
        this.tutorFinder = context.getBean(TutorFinder.class);
        this.courseFinder = context.getBean(CourseFinderService.class);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username != null && password != null) {

        } else {
            try {
                TeacherAuthenticationDTO teacher = new ObjectMapper().readValue(request.getInputStream(),
                        TeacherAuthenticationDTO.class);
                username = teacher.getEmail();
                password = teacher.getPassword();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException {

        String username = ((User) authResult.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        claims.putAll(fetchUserTypeData(roles, username));

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(SECRET_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();

        response.addHeader("Authorization", "Bearer " + token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("user", (User) authResult.getPrincipal());
        body.put("message", "Se ha autenticado correctamente");

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Wrong email or password");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }

    private Map<String, Object> fetchUserTypeData(Collection<? extends GrantedAuthority> roles, String email) {
        Map<String, Object> claims = new HashMap<>();
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            Optional<Administrator> optionalAdmin = adminFinder.getByEmail(email);
            Administrator admin = optionalAdmin.get();
            claims.put("id", admin.getId());
        } else if (roles.contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
            Optional<Teacher> optionalTeacher = teacherFinder.getByEmail(email);
            Teacher teacher = optionalTeacher.get();
            claims.put("id", teacher.getId());
        } else if (roles.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
            Optional<Student> optionalStudent = studentFinder.getByEmail(email);
            Student student = optionalStudent.get();
            claims.put("id", student.getId());
            Optional<Course> optionalCourse = courseFinder.findStudentCourseByYear(student.getId());
            if (optionalCourse.isPresent()) {
                Course course = optionalCourse.get();
                claims.put("courseId", course.getId());
            }
        } else if (roles.contains(new SimpleGrantedAuthority("ROLE_TUTOR"))) {
            Optional<Tutor> optionalTutor = tutorFinder.getByEmail(email);
            Tutor tutor = optionalTutor.get();
            claims.put("id", tutor.getId());
        }
        return claims;
    }

}
