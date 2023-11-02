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

    public Optional<Student> getOneStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateOneStudentById(Long id, Student newStudent) {
        Optional<Student> foundedStudent = studentRepository.findById(id);
        if (foundedStudent.isPresent()) {
            Student student = foundedStudent.get();
            student.setName(newStudent.getName());
            student.setAge(newStudent.getAge());
            student.setPassword(newStudent.getPassword());
            student.setEmail(newStudent.getEmail());
            student.setBooksReading(student.getBooksReading());
            studentRepository.save(student);
            return student;
        } else
            return null;
    }

    public void deleteOneStudentById(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Student " + id + " doesn't exist");
        }
    }

    public Student getOneStudentByStudentName(String name) {
        return studentRepository.findByName(name);
    }

}
