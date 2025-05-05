package com.example.huot_sreypov_spring_homework003.controller;

import com.example.huot_sreypov_spring_homework003.model.entity.Instructor;
import com.example.huot_sreypov_spring_homework003.model.request.InstructorRequest;
import com.example.huot_sreypov_spring_homework003.model.response.Response;
import com.example.huot_sreypov_spring_homework003.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
@Tag(name = "Instructor Controller", description = "Instructor management APIs")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    @Operation(summary = "Find all instructor")
    public ResponseEntity<Response<List<Instructor>>> getAllInstructors() {
        try{
        List<Instructor> instructors = instructorService.getAllInstructor();

        Response<List<Instructor>> response = new Response<>("Find all instructors successfully",instructors, HttpStatus.OK.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response<List<Instructor>> response =  new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    @Operation(summary = "Add new instructor")
    public ResponseEntity<Response<Instructor>> addNewInstructor(@RequestBody InstructorRequest instructorRequest) {
        try{
            Instructor instructor = instructorService.addNewInstructor(instructorRequest);

            Response<Instructor> response = new Response<>("Add new instructor successfully",instructor, HttpStatus.CREATED.value(), LocalDateTime.now());
            return  ResponseEntity.ok(response);
        }catch (Exception e) {
            Response<Instructor> response =  new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find instructor by id")
    public ResponseEntity<Response<Instructor>> getInstructorById(@PathVariable int id) {
        try{
            Instructor instructor = instructorService.getInstructorById(id);

            if(instructor != null) {
                Response<Instructor> response = new Response<>("Instructor found successfully", instructor, HttpStatus.OK.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            }
            Response<Instructor> response = new Response<>("Instructor not found",null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);

        }catch (Exception e) {
            Response<Instructor> response =  new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "update information instructor by id")
    public ResponseEntity<Response<Instructor>> updateInstructorById(@PathVariable int id, @RequestBody InstructorRequest instructorRequest) {
        try{
            Instructor existingInstructor = instructorService.updateInstructorById(id, instructorRequest);
            if(existingInstructor != null) {
                Response<Instructor> response = new Response<>("Update instructor successfully", existingInstructor, HttpStatus.OK.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            }
            Response<Instructor> response = new Response<>("Update instructor not found",null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            Response<Instructor> response =  new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = " remove instructor by id")
    public ResponseEntity<Response<String>> removeInstructorById(@PathVariable int id) {
        try{
            instructorService.removeInstructorById(id);
            Response<String> response = new Response<>("Remove instructor successfully", null, HttpStatus.OK.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            Response<String> response =  new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
