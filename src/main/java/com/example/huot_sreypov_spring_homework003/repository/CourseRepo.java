package com.example.huot_sreypov_spring_homework003.repository;

import com.example.huot_sreypov_spring_homework003.model.entity.Course;
import com.example.huot_sreypov_spring_homework003.model.request.CourseRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface CourseRepo {

    @Select("SELECT * FROM courses")
    @Results(id = "courseMapping" , value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.example.huot_sreypov_spring_homework003.repository.InstructorRepo.getInstructorById"))
    })
    List<Course> getAllCourses();

    @Select("""
            INSERT INTO courses(course_name, description, instructor_id)
            VALUES (#{courseName}, #{description}, #{instructorId})
            RETURNING *;
            """)
    @ResultMap("courseMapping")
    Course addNewCourse(@RequestBody CourseRequest courseRequest);

    @Select("SELECT * FROM courses WHERE course_id = #{courseId}")
    @ResultMap("courseMapping")
    Course getCourseById(int id);

    @Select("""
            UPDATE courses 
            SET course_name = #{request.courseName} , description = #{request.description} , instructor_id = #{request.instructorId} 
            WHERE course_id = #{courseId} RETURNING *;
            """)
    @ResultMap("courseMapping")
    Course updateCourseById(@Param("courseId") int id,@Param("request") CourseRequest courseRequest);

    @Delete("DELETE FROM courses WHERE course_id = #{courseId}")
    @ResultMap("courseMapping")
    void removeCourseById(int id);
}
