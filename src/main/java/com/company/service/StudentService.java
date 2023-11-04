package com.company.service;

import com.company.dao.StudentRepository;
import com.company.entity.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
            if (newStudent.getName() != null && !newStudent.getName().isEmpty()) {
                student.setName(newStudent.getName());
            }

            if (newStudent.getAge() != null && !newStudent.getAge().describeConstable().isEmpty()) {
                student.setAge(newStudent.getAge());
            }

            if (newStudent.getPassword() != null && !newStudent.getPassword().isEmpty()) {
                student.setPassword(newStudent.getPassword());
            }

            if (newStudent.getEmail() != null && !newStudent.getEmail().isEmpty()) {
                student.setEmail(newStudent.getEmail());
            }

            if (newStudent.getBooksReading() != null && !newStudent.getBooksReading().isEmpty()) {
                student.setBooksReading(newStudent.getBooksReading());
            }
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
