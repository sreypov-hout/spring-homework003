package com.example.huot_sreypov_spring_homework003.repository;

import com.example.huot_sreypov_spring_homework003.model.entity.Course;
import com.example.huot_sreypov_spring_homework003.model.entity.Student;
import com.example.huot_sreypov_spring_homework003.model.request.StudentRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface StudentRepo {

    @Select("SELECT * FROM students")
    @Results(id = "studentMapping" , value = {
            @Result(property = "studentId" , column = "student_id"),
            @Result(property = "studentName" , column = "student_name"),
            @Result(property = "email" , column = "email"),
            @Result(property = "phoneNumber" , column = "phone_number"),
            @Result(property = "courses", column = "student_id", many = @Many(select = "com.example.huot_sreypov_spring_homework003.repository.StudentRepo.getCoursesByStudentId"))
    })
    List<Student> findAllStudents();

    @Select("""
        SELECT c.course_id, c.course_name, c.description, c.instructor_id
        FROM student_course sc
        JOIN courses c ON sc.course_id = c.course_id
        WHERE sc.student_id = #{studentId}
    """)
    @Results(id = "courseMapping", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.example.huot_sreypov_spring_homework003.repository.InstructorRepo.getInstructorById"))
    })
    List<Course> getCoursesByStudentId(int studentId);

    @Delete("DELETE FROM student_course WHERE course_id = #{courseId}")
    @ResultMap("studentMapping")
    void deleteStudentCourseByCourseId(int courseId);


    @Select("SELECT * FROM students WHERE student_id = #{id}")
    @ResultMap("studentMapping")
    Student findStudentById(int id);

    @Select("""
             INSERT INTO students (student_name, email, phone_number) 
             VALUES (#{request.studentName}, #{request.email}, #{request.phoneNumber})
             RETURNING *;
             """)
    @ResultMap("studentMapping")
    Student addStudent(@Param("request") StudentRequest studentRequest);


    @Select("""
        SELECT * FROM students WHERE student_id = #{studentId}
    """)
    @ResultMap("studentMapping")
    Student getStudentById(int studentId);


    @Delete("DELETE FROM student_course WHERE student_id = #{studentId}")
    void deleteStudentCourseByStudentId(int studentId);

    @Delete("DELETE FROM students WHERE student_id = #{studentId}")
    void deleteStudentById(int studentId);

    @Select("""
        UPDATE students SET student_name = #{request.studentName}, email = #{request.email}, phone_number = #{request.phoneNumber}
        WHERE student_id = #{studentId}
        RETURNING *;
        """)
    @ResultMap("studentMapping")
    Student updateStudentById(@Param("studentId") int id,@Param("request") StudentRequest studentRequest);
}
