package com.example.huot_sreypov_spring_homework003.service;

import com.example.huot_sreypov_spring_homework003.model.entity.Course;
import com.example.huot_sreypov_spring_homework003.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course addNewCourse(CourseRequest courseRequest);

    Course getCourseById(int id);

    Course updateCourseById(int id, CourseRequest courseRequest);

    boolean removeCourseById(int id);
}
