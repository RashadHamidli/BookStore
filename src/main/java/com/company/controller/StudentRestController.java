package com.company.controller;

import com.company.dto.StudentDTO;
import com.company.entity.Book;
import com.company.entity.Student;
import com.company.service.BookService;
import com.company.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentRestController {
    private final StudentService studentService;
    private final BookService bookService;

    public StudentRestController(StudentService studentService, BookService bookService) {
        this.studentService = studentService;
        this.bookService = bookService;
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
//        student.setAge(studentDTO.getAge());
//        student.setEmail(studentDTO.getEmail());
//        student.setPassword(studentDTO.getPassword());
//        student.setBooksReading(studentDTO.getBooks());
        studentService.saveOneStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateOneStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        Optional<Student> optionalStudent = studentService.getOneStudentById(id);
        if (optionalStudent.isPresent()) {
            Student student = new Student();
            student.setName(studentDTO.getName());
//            student.setAge(studentDTO.getAge());
//            student.setEmail(studentDTO.getEmail());
//            student.setPassword(studentDTO.getPassword());
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

    @PostMapping("/read/{id}")
    public ResponseEntity<Book> readBook(@PathVariable Long id, @RequestBody Student newStudent) {
        Long sId = newStudent.getId();
        Long stId = studentService.getOneStudentById(sId).get().getId();
        Optional<Book> optionalBook = bookService.getOneBookById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookService.updateOneBookById(stId, book);
            return ResponseEntity.ok(book);
        }
        return null;
    }
}
