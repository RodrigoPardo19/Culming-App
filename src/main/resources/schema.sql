CREATE SEQUENCE seq_students;
CREATE SEQUENCE seq_teachers;
CREATE SEQUENCE seq_tutors;
CREATE SEQUENCE seq_administrators;
CREATE SEQUENCE seq_courses;
CREATE SEQUENCE seq_subjects;
CREATE SEQUENCE seq_grades;
CREATE SEQUENCE seq_assistances;
CREATE SEQUENCE seq_homeworks;
CREATE SEQUENCE seq_schools;
CREATE SEQUENCE seq_homework_states;

CREATE TABLE students (
  id              bigint NOT NULL, 
  name            varchar(50) NOT NULL, 
  middle_name     varchar(50), 
  last_name       varchar(50) NOT NULL, 
  second_surname  varchar(50), 
  age             int CHECK(age >= 3 AND age < 30), 
  date_of_birth   date NOT NULL CHECK(date_of_birth > '1920-01-01' AND date_of_birth < '2100-12-30'), 
  email           varchar(100) NOT NULL UNIQUE, 
  password        varchar(100), 
  address         varchar(100) NOT NULL, 
  graduation_date date CHECK(graduation_date > '1920-01-01' AND graduation_date < '2100-12-30'), 
  exit_date       date CHECK(exit_date > '1920-01-01' AND exit_date < '2100-12-30'), 
  enrollment_date date NOT NULL CHECK(enrollment_date > '1920-01-01' AND enrollment_date < '2100-12-30'), 
  is_active       boolean NOT NULL, 
  tutor_id        bigint NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE teachers (
  id              bigint NOT NULL, 
  name            varchar(50) NOT NULL, 
  middle_name     varchar(50), 
  last_name       varchar(50) NOT NULL, 
  second_surname  varchar(50), 
  email           varchar(100) NOT NULL UNIQUE, 
  password        varchar(100), 
  address         varchar(100) NOT NULL, 
  date_of_birth   date NOT NULL CHECK(date_of_birth > '1920-01-01' AND date_of_birth < '2100-12-30'), 
  phone           varchar(12) NOT NULL UNIQUE, 
  biography       varchar(700), 
  photo           varchar(100), 
  enrollment_date date NOT NULL CHECK(enrollment_date > '1920-01-01' AND enrollment_date < '2100-12-30'), 
  exit_date       date CHECK(exit_date > '1920-01-01' AND exit_date < '2100-12-30'), 
  PRIMARY KEY (id));

CREATE TABLE tutors (
  id             bigint NOT NULL, 
  name           varchar(50) NOT NULL, 
  middle_name    varchar(50), 
  last_name      varchar(50) NOT NULL, 
  second_surname varchar(50), 
  age            int NOT NULL CHECK(age >= 18 and age < 100), 
  email          varchar(100) NOT NULL UNIQUE, 
  password       varchar(100), 
  address        varchar(100) NOT NULL, 
  phone          varchar(12) UNIQUE, 
  PRIMARY KEY (id));

CREATE TABLE administrators (
  id              bigint NOT NULL, 
  name            varchar(50) NOT NULL, 
  middle_name     varchar(50), 
  last_name       varchar(50) NOT NULL, 
  second_surname  varchar(50), 
  email           varchar(100) NOT NULL UNIQUE, 
  password        varchar(100), 
  address         varchar(100) NOT NULL, 
  phone           varchar(12) UNIQUE, 
  date_of_birth   date NOT NULL CHECK(date_of_birth > '1920-01-01' AND date_of_birth < '2100-12-30'), 
  enrollment_date date NOT NULL CHECK(enrollment_date > '1920-01-01' AND enrollment_date < '2100-12-30'), 
  exit_date       date CHECK(exit_date > '1920-01-01' AND exit_date < '2100-12-30'), 
  school_id       int NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE courses (
  id                 bigint NOT NULL, 
  name               varchar(50) NOT NULL, 
  year_of_generation int NOT NULL, 
  school_id          int NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE subjects (
  id   int NOT NULL, 
  name varchar(100) NOT NULL UNIQUE, 
  PRIMARY KEY (id));

CREATE TABLE students_subjects (
  student_id bigint NOT NULL, 
  subject_id int NOT NULL, 
  PRIMARY KEY (student_id, 
  subject_id));

CREATE TABLE teachers_subjects (
  teacher_id bigint NOT NULL, 
  subject_id int NOT NULL, 
  PRIMARY KEY (teacher_id, 
  subject_id));

CREATE TABLE students_courses (
  student_id bigint NOT NULL, 
  course_id  bigint NOT NULL, 
  PRIMARY KEY (student_id, 
  course_id));

CREATE TABLE grades (
  id         bigint NOT NULL, 
  value      double NOT NULL CHECK(value >= 1.0 AND value <=7.0), 
  "date"     date NOT NULL, 
  student_id bigint NOT NULL, 
  subject_id int NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE assistances (
  id         bigint NOT NULL, 
  is_present boolean NOT NULL, 
  "date"     date NOT NULL, 
  student_id bigint NOT NULL, 
  subject_id int NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE homeworks (
  id                bigint NOT NULL, 
  instruction       varchar(255) NOT NULL, 
  "date"            date NOT NULL, 
  homework_state_id int NOT NULL, 
  student_id        bigint NOT NULL, 
  subject_id        int NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE schools (
  id      int NOT NULL, 
  name    varchar(50) NOT NULL, 
  address varchar(100) NOT NULL, 
  email   varchar(100) NOT NULL UNIQUE, 
  phone   varchar(12) UNIQUE, 
  PRIMARY KEY (id));

CREATE TABLE homework_states (
  id   int NOT NULL, 
  name varchar(20) NOT NULL, 
  PRIMARY KEY (id));

ALTER TABLE students_subjects ADD CONSTRAINT FKstudents_s389094 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE students_subjects ADD CONSTRAINT FKstudents_s253868 FOREIGN KEY (subject_id) REFERENCES subjects (id);
ALTER TABLE teachers_subjects ADD CONSTRAINT FKteachers_s84827 FOREIGN KEY (subject_id) REFERENCES subjects (id);
ALTER TABLE teachers_subjects ADD CONSTRAINT FKteachers_s174058 FOREIGN KEY (teacher_id) REFERENCES teachers (id);
ALTER TABLE students ADD CONSTRAINT FKstudents922794 FOREIGN KEY (tutor_id) REFERENCES tutors (id);
ALTER TABLE students_courses ADD CONSTRAINT FKstudents_c342471 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE students_courses ADD CONSTRAINT FKstudents_c843748 FOREIGN KEY (course_id) REFERENCES courses (id);
ALTER TABLE grades ADD CONSTRAINT FKgrades293692 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE grades ADD CONSTRAINT FKgrades571081 FOREIGN KEY (subject_id) REFERENCES subjects (id);
ALTER TABLE assistances ADD CONSTRAINT FKassistance67284 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE assistances ADD CONSTRAINT FKassistance932057 FOREIGN KEY (subject_id) REFERENCES subjects (id);
ALTER TABLE courses ADD CONSTRAINT FKcourses99820 FOREIGN KEY (school_id) REFERENCES schools (id);
ALTER TABLE administrators ADD CONSTRAINT FKadministra339892 FOREIGN KEY (school_id) REFERENCES schools (id);
ALTER TABLE homeworks ADD CONSTRAINT FKhomeworks135434 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE homeworks ADD CONSTRAINT FKhomeworks232255 FOREIGN KEY (homework_state_id) REFERENCES homework_states (id);
ALTER TABLE homeworks ADD CONSTRAINT FKhomeworks270660 FOREIGN KEY (subject_id) REFERENCES subjects (id);
