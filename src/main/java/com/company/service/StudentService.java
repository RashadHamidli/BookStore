package com.company.service;

import com.company.dao.StudentRepository;
import com.company.entity.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveOneStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getOneStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateOneStudent(Long id, Student newStudent) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student foundStudent = student.get();
            foundStudent.setName(newStudent.getName());
            foundStudent.setAge(newStudent.getAge());
            foundStudent.setBooksReading(foundStudent.getBooksReading());
            studentRepository.save(foundStudent);
            return foundStudent;
        } else
            return null;
    }

    public void deleteOneStudent(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Student " + id + " doesn't exist");
        }
    }

    public Student getOneStudentByStudentName(String name) {
        return studentRepository.findByUserName(name);
    }

}
