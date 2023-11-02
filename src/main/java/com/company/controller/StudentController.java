package com.company.controller;

import com.company.dto.StudentDTO;
import com.company.entity.Student;
import com.company.service.StudentService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getOneStudent(@PathVariable Long id) {
        Student student = studentService.getOneStudentById(id).orElse(null);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> createOneStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        studentService.saveOneStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateOneStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        Optional<Student> optionalStudent = studentService.getOneStudentById(id);
        if (optionalStudent.isPresent()) {
            Student student = new Student();
            student.setName(studentDTO.getName());
            student.setAge(studentDTO.getAge());
            student.setEmail(studentDTO.getEmail());
            student.setPassword(studentDTO.getPassword());
            studentService.updateOneStudentById(id, student);
            return ResponseEntity.ok("update successfully");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @DeleteMapping("/{id}")
    public void deleteOneStudent(@PathVariable Long id) {
        studentService.deleteOneStudentById(id);
        ResponseEntity.ok("delete successfully");
    }
}
