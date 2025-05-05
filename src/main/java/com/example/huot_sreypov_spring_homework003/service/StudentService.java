package com.example.huot_sreypov_spring_homework003.service;

import com.example.huot_sreypov_spring_homework003.model.entity.Student;
import com.example.huot_sreypov_spring_homework003.model.request.StudentRequest;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();

    Student getStudentById(int id);

    Student addStudent(StudentRequest studentRequest);

    boolean deleteStudentById(int id);

    Student updateStudentById(int id, StudentRequest studentRequest);
}
