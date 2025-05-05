CREATE DATABASE learn_db;

CREATE TABLE instructors(
    instructor_id SERIAL PRIMARY KEY,
    instructor_name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS courses(
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(50),
    description TEXT,
    instructor_id INT,
    FOREIGN KEY (instructor_id) REFERENCES instructors(instructor_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE students(
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(100),
    email VARCHAR(100),
    phone_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS student_course(
    id SERIAL PRIMARY KEY,
    student_id INT,
    course_id INT,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE ON UPDATE CASCADE
)