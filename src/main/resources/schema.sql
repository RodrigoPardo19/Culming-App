CREATE SEQUENCE seq_students;
CREATE SEQUENCE seq_teachers;
CREATE SEQUENCE seq_tutors;
CREATE SEQUENCE seq_administrators;
CREATE SEQUENCE seq_courses;
CREATE SEQUENCE seq_subjects;
CREATE SEQUENCE seq_students_subjects;
CREATE SEQUENCE seq_evaluations;
CREATE SEQUENCE seq_assistances;
CREATE SEQUENCE seq_homeworks;
CREATE SEQUENCE seq_schools;
CREATE SEQUENCE seq_homework_states;
CREATE SEQUENCE seq_courses_subjects_teachers;
CREATE SEQUENCE seq_evaluation_types;
CREATE SEQUENCE seq_homeworks_students;
CREATE SEQUENCE seq_students_evaluations;
CREATE SEQUENCE seq_subject_types;

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
  school_id       bigint NOT NULL, 
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
  school_id       bigint NOT NULL, 
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
  school_id       bigint NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE courses (
  id    int NOT NULL, 
  level varchar(50) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE subjects (
  id              int NOT NULL, 
  name            varchar(100) NOT NULL UNIQUE, 
  subject_type_id int NOT NULL, 
  quotas          int, 
  PRIMARY KEY (id));

CREATE TABLE students_subjects (
  id          bigint NOT NULL,
  student_id  bigint NOT NULL, 
  subject_id  int NOT NULL, 
  year        int NOT NULL, 
  is_approved boolean, 
  final_grade double, 
  PRIMARY KEY (id));

CREATE TABLE students_courses (
  id          bigint NOT NULL,
  student_id  bigint NOT NULL, 
  course_id   int NOT NULL, 
  year        int NOT NULL, 
  is_approved boolean, 
  PRIMARY KEY (id, 
  year));

CREATE TABLE evaluations (
  id                 bigint NOT NULL, 
  type_id            int NOT NULL, 
  description        varchar(50) NOT NULL, 
  qualification_date date NOT NULL,
  year               int NOT NULL,
  subject_id         int NOT NULL,
  course_id          int NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE assistances (
  id                 bigint NOT NULL, 
  date_of_assistance date NOT NULL,
  year               int NOT NULL,
  subject_id         int NOT NULL,
  course_id          int NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE homeworks (
  id          bigint NOT NULL, 
  instruction varchar(255) NOT NULL, 
  deadline    date NOT NULL,
  year               int NOT NULL,
  subject_id  int NOT NULL,
  course_id          int NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE schools (
  id      bigint NOT NULL, 
  name    varchar(50) NOT NULL, 
  address varchar(100) NOT NULL, 
  email   varchar(100) NOT NULL UNIQUE, 
  phone   varchar(12) UNIQUE, 
  PRIMARY KEY (id));

CREATE TABLE homework_states (
  id   int NOT NULL, 
  name varchar(20) NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE courses_subjects_teachers (
  id         bigint NOT NULL,
  course_id  int NOT NULL, 
  subject_id int NOT NULL, 
  teacher_id bigint NOT NULL, 
  year       int NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE evaluation_types (
  id   int NOT NULL, 
  type varchar(30) NOT NULL UNIQUE, 
  PRIMARY KEY (id));

CREATE TABLE courses_schools (
  course_id int NOT NULL, 
  school_id bigint NOT NULL, 
  PRIMARY KEY (course_id, 
  school_id));

CREATE TABLE students_homeworks (
  id          bigint NOT NULL,
  student_id  bigint NOT NULL, 
  homework_id bigint NOT NULL, 
  state_id    int NOT NULL, 
  PRIMARY KEY (id));

CREATE TABLE students_assistances (
  student_id    bigint NOT NULL, 
  assistance_id bigint NOT NULL, 
  is_present    boolean NOT NULL, 
  PRIMARY KEY (student_id,
  assistance_id));

CREATE TABLE students_evaluations (
  id            bigint NOT NULL,
  student_id    bigint NOT NULL, 
  evaluation_id bigint NOT NULL, 
  grade         double,
  PRIMARY KEY (id));

CREATE TABLE subject_types (
  id   int NOT NULL, 
  type varchar(30) NOT NULL UNIQUE, 
  PRIMARY KEY (id));

ALTER TABLE students_subjects ADD CONSTRAINT FKstudents_s389094 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE students_subjects ADD CONSTRAINT FKstudents_s253868 FOREIGN KEY (subject_id) REFERENCES subjects (id);
ALTER TABLE students ADD CONSTRAINT FKstudents922794 FOREIGN KEY (tutor_id) REFERENCES tutors (id);
ALTER TABLE students_courses ADD CONSTRAINT FKstudents_c342471 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE students_courses ADD CONSTRAINT FKstudents_c843748 FOREIGN KEY (course_id) REFERENCES courses (id);
ALTER TABLE assistances ADD CONSTRAINT FKassistance932057 FOREIGN KEY (subject_id) REFERENCES subjects (id);
ALTER TABLE administrators ADD CONSTRAINT FKadministra339892 FOREIGN KEY (school_id) REFERENCES schools (id);
ALTER TABLE homeworks ADD CONSTRAINT FKhomeworks270660 FOREIGN KEY (subject_id) REFERENCES subjects (id);
ALTER TABLE courses_subjects_teachers ADD CONSTRAINT FKcourses_su346873 FOREIGN KEY (course_id) REFERENCES courses (id);
ALTER TABLE courses_subjects_teachers ADD CONSTRAINT FKcourses_su25427 FOREIGN KEY (subject_id) REFERENCES subjects (id);
ALTER TABLE teachers ADD CONSTRAINT FKteachers702696 FOREIGN KEY (school_id) REFERENCES schools (id);
ALTER TABLE students ADD CONSTRAINT FKstudents29174 FOREIGN KEY (school_id) REFERENCES schools (id);
ALTER TABLE evaluations ADD CONSTRAINT FKevaluation603687 FOREIGN KEY (type_id) REFERENCES evaluation_types (id);
ALTER TABLE courses_schools ADD CONSTRAINT FKcourses_sc849875 FOREIGN KEY (course_id) REFERENCES courses (id);
ALTER TABLE courses_schools ADD CONSTRAINT FKcourses_sc255630 FOREIGN KEY (school_id) REFERENCES schools (id);
ALTER TABLE students_homeworks ADD CONSTRAINT FKstudents_820944 FOREIGN KEY (homework_id) REFERENCES homeworks (id);
ALTER TABLE students_homeworks ADD CONSTRAINT FKstudents_245033 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE students_homeworks ADD CONSTRAINT FKstudents_470163 FOREIGN KEY (state_id) REFERENCES homework_states (id);
ALTER TABLE students_assistances ADD CONSTRAINT FKstudents_a180861 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE students_assistances ADD CONSTRAINT FKstudents_a94160 FOREIGN KEY (assistance_id) REFERENCES assistances (id);
ALTER TABLE students_evaluations ADD CONSTRAINT FKstudents_e222128 FOREIGN KEY (student_id) REFERENCES students (id);
ALTER TABLE students_evaluations ADD CONSTRAINT FKstudents_e391155 FOREIGN KEY (evaluation_id) REFERENCES evaluations (id);
ALTER TABLE courses_subjects_teachers ADD CONSTRAINT FKcourses_su936195 FOREIGN KEY (teacher_id) REFERENCES teachers (id);
ALTER TABLE subjects ADD CONSTRAINT FKsubjects729051 FOREIGN KEY (subject_type_id) REFERENCES subject_types (id);
ALTER TABLE evaluations ADD CONSTRAINT FKevaluation80800 FOREIGN KEY (subject_id) REFERENCES subjects (id);
