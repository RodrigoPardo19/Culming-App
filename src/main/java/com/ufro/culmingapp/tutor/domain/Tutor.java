package com.ufro.culmingapp.tutor.domain;

import com.ufro.culmingapp.shared.domain.valueobjects.*;
import com.ufro.culmingapp.student.domain.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutors")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tutor_generator")
    @SequenceGenerator(name = "tutor_generator", sequenceName = "seq_tutors", allocationSize = 1)
    private Long id;

    @Embedded
    private FullName fullName;

    @Embedded
    private TutorAge age;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Address address;

    @Embedded
    private Phone phone;

    @OneToMany(mappedBy = "tutor")
    private List<Student> students = new ArrayList<>();

    public Tutor() {
        // Used only for spring
    }

    public Tutor(FullName fullName, TutorAge age, Email email, Address address) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public TutorAge getAge() {
        return age;
    }

    public void setAge(TutorAge age) {
        this.age = age;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
