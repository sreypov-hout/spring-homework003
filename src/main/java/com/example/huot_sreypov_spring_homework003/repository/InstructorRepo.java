package com.example.huot_sreypov_spring_homework003.repository;

import com.example.huot_sreypov_spring_homework003.model.entity.Instructor;
import com.example.huot_sreypov_spring_homework003.model.request.InstructorRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface InstructorRepo {
    @Results(id = "instructorMapping" , value ={
            @Result(property = "instructorId" , column = "instructor_id"),
            @Result(property = "instructorName" , column = "instructor_name"),
            @Result(property = "email" , column = "email")
    })
    @Select("SELECT * FROM instructors")
    List<Instructor> getAllInstructor();


    @Select("""
                INSERT INTO instructors(instructor_name,email) values (#{request.instructorName}, #{request.email})
                RETURNING *;
            """)
    @ResultMap("instructorMapping")
    Instructor addNewInstructor(@Param("request") InstructorRequest instructorRequest);


    @Select("SELECT * FROM instructors WHERE instructor_id = #{instructorId}; ")
    @ResultMap("instructorMapping")
    Instructor getInstructorById(int id);

    @Select("""
                UPDATE instructors SET instructor_name = #{request.instructorName}, email = #{request.email} 
                WHERE instructor_id = #{instructorId} 
                RETURNING *;
            """)
    @ResultMap("instructorMapping")
    Instructor updateInstructorById(@Param("instructorId") int id,@Param("request") InstructorRequest instructorRequest);


    @Select("""
            DELETE FROM instructors WHERE instructor_id = #{instructorId}
            """)
    void removeInstructorById(int id);


}
