package com.example.huot_sreypov_spring_homework003.service.serviceImp;

import com.example.huot_sreypov_spring_homework003.model.entity.Student;
import com.example.huot_sreypov_spring_homework003.model.entity.StudentCourse;
import com.example.huot_sreypov_spring_homework003.model.request.StudentRequest;
import com.example.huot_sreypov_spring_homework003.repository.StudentCourseRepo;
import com.example.huot_sreypov_spring_homework003.repository.StudentRepo;
import com.example.huot_sreypov_spring_homework003.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepo studentRepo;
    private final StudentCourseRepo studentCourseRepo;
//    private final StudentCourse studentCourse;

    public StudentServiceImp(StudentRepo studentRepo, StudentCourseRepo studentCourseRepo) {
        this.studentRepo = studentRepo;
        this.studentCourseRepo = studentCourseRepo;
    }


    @Override
    public List<Student> findAllStudents() {
        return studentRepo.findAllStudents();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepo.findStudentById(id);
    }

//    @Override
//    public Student addStudent(StudentRequest studentRequest) {
//        Student student = studentRepo.addStudent(studentRequest);
//        for(Integer studentId : studentRequest.getCourseId())
//        {
//            studentCourseRepo.addStudentCourse(student.getStudentId(),studentId);
//        }
//        return studentRepo.getStudentById(String.valueOf(student.getStudentId()));
//    }

    @Override
    public Student addStudent(StudentRequest studentRequest) {
        Student student = studentRepo.addStudent(studentRequest);
        for (Integer courseId : studentRequest.getCourseIds()){
                studentCourseRepo.insertStudentIdAndCourseId(student.getStudentId(), courseId);
        }
        return studentRepo.getStudentById(student.getStudentId());
    }

    @Override
    public boolean deleteStudentById(int id) {
        Student student = studentRepo.findStudentById(id);
        if (student != null) {
            studentRepo.deleteStudentCourseByStudentId(id); // Delete relationships first
            studentRepo.deleteStudentById(id); // Delete the student
            return true;
        }
        return false;
    }

    @Override
    public Student updateStudentById(int id, StudentRequest studentRequest) {
        return studentRepo.updateStudentById(id, studentRequest);
    }

}
