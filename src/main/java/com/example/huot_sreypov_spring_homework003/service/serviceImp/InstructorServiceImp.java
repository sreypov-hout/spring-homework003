package com.example.huot_sreypov_spring_homework003.service.serviceImp;

import com.example.huot_sreypov_spring_homework003.model.entity.Instructor;
import com.example.huot_sreypov_spring_homework003.model.request.InstructorRequest;
import com.example.huot_sreypov_spring_homework003.repository.InstructorRepo;
import com.example.huot_sreypov_spring_homework003.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstructorServiceImp implements InstructorService {

    private final InstructorRepo instructorRepo;

    public InstructorServiceImp(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }


    @Override
    public List<Instructor> getAllInstructor() {
        return instructorRepo.getAllInstructor();
    }

    @Override
    public Instructor addNewInstructor(InstructorRequest instructorRequest) {
        return instructorRepo.addNewInstructor(instructorRequest);
    }

    @Override
    public Instructor getInstructorById(int id) {
        return instructorRepo.getInstructorById(id);
    }

    @Override
    public Instructor updateInstructorById(int id, InstructorRequest instructorRequest) {
        return instructorRepo.updateInstructorById(id,instructorRequest);
    }

    @Override
    public void removeInstructorById(int id) {
        Instructor instructor = instructorRepo.getInstructorById(id);
        if (instructor == null) {
            throw new RuntimeException("Instructor not found with ID: " + id);
        }
        instructorRepo.removeInstructorById(id);
    }


}
