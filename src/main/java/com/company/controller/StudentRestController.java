package com.company.controller;

import com.company.dto.StudentDTO;
import com.company.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestController {
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        List<StudentDTO> studentDTOs = studentService.getAllStudent();
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getOneStudent(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudent(id);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> creatOneStudent(@RequestBody StudentDTO newStudentDTO) {
        StudentDTO studentDTO = studentService.creatStudent(newStudentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<StudentDTO> updateOneStudent(@PathVariable Long id, @RequestBody StudentDTO newStudentDTO) {
        StudentDTO studentDTO = studentService.updateStudent(id, newStudentDTO);
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id) ? ResponseEntity.ok("Student with ID " + id + " has been deleted")
                : ResponseEntity.notFound().build();

    }
    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        // Implement user registration for a student
        studentService.registerStudent(student);
        return ResponseEntity.ok("Student registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        // Implement user login and JWT token generation
        String jwt = studentService.login(userLogin);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/{studentId}/reading-list")
    public ResponseEntity<List<Book>> getStudentReadingList(@PathVariable Long studentId) {
        // Implement retrieving the reading list of a student
        List<Book> readingList = studentService.getReadingList(studentId);
        return ResponseEntity.ok(readingList);
    }

}
