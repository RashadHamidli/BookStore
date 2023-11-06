package com.company.mapper;

import com.company.dto.StudentDTO;
import com.company.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
        public StudentDTO studentConvertToStudentDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAge(student.getAge());
        studentDTO.setPassword(student.getPassword());
        return studentDTO;
    }

    public Student studentConvertToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student.setPassword(student.getPassword());
        return student;
    }

}
