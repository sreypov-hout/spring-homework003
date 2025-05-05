package com.example.huot_sreypov_spring_homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Course> courses;
}
