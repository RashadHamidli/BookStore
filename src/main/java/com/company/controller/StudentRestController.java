package com.company.controller;

import com.company.dto.BookDTO;
import com.company.dto.StudentDTO;
import com.company.entity.Book;
import com.company.entity.Student;
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

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO newStudentDTO) {
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        // Implementation for user login and JWT generation
        return ResponseEntity.ok("JWT generated successfully.");
    }

    @GetMapping("/{studentId}/reading-list")
    public ResponseEntity<List<BookDTO>> getStudentReadingList(@PathVariable Long studentId) {
        // Implementation to get reading list of a student
        return ResponseEntity.ok(/* List of books the student is reading */);
    }

    @PostMapping("/{studentId}/subscribe/{authorId}")
    public ResponseEntity<String> subscribeToAuthor(@PathVariable Long studentId, @PathVariable Long authorId) {
        // Implementation for a student to subscribe to an author
        return ResponseEntity.ok("Student subscribed to author successfully.");
    }

}
