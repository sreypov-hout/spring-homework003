package com.example.huot_sreypov_spring_homework003.service.serviceImp;

import com.example.huot_sreypov_spring_homework003.model.entity.Course;
import com.example.huot_sreypov_spring_homework003.model.request.CourseRequest;
import com.example.huot_sreypov_spring_homework003.repository.CourseRepo;
import com.example.huot_sreypov_spring_homework003.repository.StudentRepo;
import com.example.huot_sreypov_spring_homework003.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    public CourseServiceImp(CourseRepo courseRepo, StudentRepo studentRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.getAllCourses();
    }

    @Override
    public Course addNewCourse(CourseRequest courseRequest) {
        return courseRepo.addNewCourse(courseRequest);
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepo.getCourseById(id);
    }

    @Override
    public Course updateCourseById(int id, CourseRequest courseRequest) {
        return courseRepo.updateCourseById(id, courseRequest);
    }

    @Override
    public boolean removeCourseById(int id) {
        Course course = courseRepo.getCourseById(id);
        if (course != null) {
            studentRepo.deleteStudentCourseByCourseId(id);       // Delete all related rows in student_course
            courseRepo.removeCourseById(id);                     // Now safely delete the course
            return true;
        }
        return false;
    }
}
