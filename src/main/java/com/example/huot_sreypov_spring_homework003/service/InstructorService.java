package com.example.huot_sreypov_spring_homework003.service;

import com.example.huot_sreypov_spring_homework003.model.entity.Instructor;
import com.example.huot_sreypov_spring_homework003.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructor();

    Instructor addNewInstructor(InstructorRequest instructorRequest);

    Instructor getInstructorById(int id);

    Instructor updateInstructorById(int id, InstructorRequest instructorRequest);

    void removeInstructorById(int id);
}
