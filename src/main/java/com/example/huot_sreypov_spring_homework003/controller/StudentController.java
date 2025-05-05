package com.example.huot_sreypov_spring_homework003.controller;

import com.example.huot_sreypov_spring_homework003.model.entity.Student;
import com.example.huot_sreypov_spring_homework003.model.request.StudentRequest;
import com.example.huot_sreypov_spring_homework003.model.response.Response;
import com.example.huot_sreypov_spring_homework003.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@Tag(name = "Student Controller", description = "Student management APIs")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(summary = "Find all students with courses and instructors")
    public ResponseEntity<Response<List<Student>>> findAllStudents() {
        try {
            List<Student> students = studentService.findAllStudents();
            Response<List<Student>> response = new Response<>("Students retrieved successfully", students, HttpStatus.OK.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<List<Student>> response = new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID with their courses and instructor")
    public ResponseEntity<Response<Student>> getStudentById(@PathVariable int id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                Response<Student> response = new Response<>("Student found successfully", student, HttpStatus.OK.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            } else {
                Response<Student> response = new Response<>("Student not found", null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            Response<Student> response = new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping
    @Operation(summary = "Add new student")
    public ResponseEntity<Response<Student>> addStudent(@RequestBody StudentRequest studentRequest) {
        try{
            Student student = studentService.addStudent(studentRequest);
            Response<Student> response = new Response<>("Student added successfully", student, HttpStatus.OK.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            Response<Student> response = new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update information student by id")
    public ResponseEntity<Response<Student>> updateStudentById(@PathVariable int id, @RequestBody StudentRequest studentRequest) {
        try{
            Student student = studentService.updateStudentById(id, studentRequest);
            if (student != null) {
                Response<Student> response = new Response<>("Student updated successfully", student, HttpStatus.OK.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            }else {
                Response<Student> response = new Response<>("Student not found", null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            }
        }catch (Exception e){
            Response<Student> response = new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student by id")
    public ResponseEntity<Response<String>> deleteStudentById(@PathVariable int id) {
        try{
            boolean isDeleted = studentService.deleteStudentById(id);
            if (isDeleted) {
                Response<String> response = new Response<>("Student deleted successfully", null, HttpStatus.OK.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            }else {
            Response<String> response = new Response<>("Student not found", null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
            }
        }catch (Exception e){
            Response<String> response = new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }
    }


}
