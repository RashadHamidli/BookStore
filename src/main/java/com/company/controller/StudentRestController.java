package com.company.controller;

import com.company.dto.StudentDTO;
import com.company.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentRestController {
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        List<StudentDTO> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getOneStudent(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getOneStudent(id);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> creatOneStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO student = studentService.creatOneStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PostMapping("/{id}")
    public ResponseEntity<StudentDTO> updateOneStudent(@PathVariable Long id, @RequestBody StudentDTO newStudentDTO) {
        StudentDTO studentDTO = studentService.updateOneStudent(id, newStudentDTO);
        return ResponseEntity.ok(studentDTO);
    }

}
