package com.example.huot_sreypov_spring_homework003.repository;

import com.example.huot_sreypov_spring_homework003.model.entity.Course;
import com.example.huot_sreypov_spring_homework003.model.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentCourseRepo {

    @Select("""
        SELECT c.* FROM courses c
        JOIN student_course sc ON sc.course_id = c.course_id
        WHERE sc.student_id = #{studentId}
    """)
    @Results(id="StudentCourseMapper", value = {
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "courseName",column = "course_name"),
            @Result(property = "description",column = "description"),
            @Result(property = "instructor",column = "instructor_id",
                    one = @One(select = "com.example.huot_sreypov_spring_homework003.repository.InstructorRepo.getInstructorById"))
    })
    List<Course> getStudentCoursesByStudentId(int studentId);

    @Select("insert into student_course(student_id,course_id) values(#{studentId},#{courseId})")
    @ResultMap("StudentCourseMapper")
    void insertStudentIdAndCourseId(int studentId, int courseId);


}
