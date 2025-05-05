package com.example.huot_sreypov_spring_homework003.controller;

import com.example.huot_sreypov_spring_homework003.model.entity.Course;
import com.example.huot_sreypov_spring_homework003.model.request.CourseRequest;
import com.example.huot_sreypov_spring_homework003.model.response.Response;
import com.example.huot_sreypov_spring_homework003.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
@Tag(name = "Course Controller", description = "Course management APIs")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @Operation(summary = "Get all course")
    public ResponseEntity<Response<List<Course>>> getAllCourses() {
      try{
          List<Course> courses = courseService.getAllCourses();
          Response<List<Course>> response = new Response<>("Get all course successfully",courses,HttpStatus.OK.value(),LocalDateTime.now());
          return ResponseEntity.ok(response);
      }catch (Exception e){
          Response<List<Course>> response = new Response<>(e.getMessage(),null,HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now());
          return ResponseEntity.ok(response);
      }
    }

    @PostMapping
    @Operation(summary = "Add new course")
    public ResponseEntity<Response<Course>> addNewCourse(@RequestBody CourseRequest courseRequest) {
        try{
            Course course = courseService.addNewCourse(courseRequest);
            Response<Course> response = new Response<>("Create new course successfully",course,HttpStatus.CREATED.value(),LocalDateTime.now());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            Response<Course> response = new Response<>(e.getMessage(),null,HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary =  "Find course by id")
    public ResponseEntity<Response<Course>> getCourseById(@PathVariable int id) {
        try{
            Course course = courseService.getCourseById(id);
            if(course != null) {
                Response<Course> response = new Response<>("Course found successfully", course, HttpStatus.OK.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            }
            Response<Course> response = new Response<>("Course not found", null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            Response<Course> response = new Response<>(e.getMessage(), null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update course by id")
    public ResponseEntity<Response<Course>> updateCourseById(@PathVariable int id, @RequestBody CourseRequest courseRequest) {
        try{
            Course course = courseService.updateCourseById(id, courseRequest);
            if(course != null) {
                Response<Course> response = new Response<>("Update course successfully", course, HttpStatus.OK.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            }
            Response<Course> response = new Response<>("Course not found", null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            Response<Course> response = new Response<>(e.getMessage(), null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove course by id")
    public ResponseEntity<Response<String>> removeCourseById(@PathVariable int id) {
        try {
            boolean isDeleted = courseService.removeCourseById(id);
            if (isDeleted) {
                Response<String> response = new Response<>("Course deleted successfully", null, HttpStatus.OK.value(), LocalDateTime.now());
                return ResponseEntity.ok(response);
            } else {
                Response<String> response = new Response<>("Course not found", null, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            Response<String> response = new Response<>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
