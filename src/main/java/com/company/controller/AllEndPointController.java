//package com.company.controller;
//
//import com.company.entity.Author;
//import com.company.entity.Book;
//import com.company.entity.Student;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.Reader;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class AllEndPointController {
//
//    // Dependency injections for services
//
//    // User Registration and Authentication
//
//    @PostMapping("/register/student")
//    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
//        // Implementation for registering a student
//        return ResponseEntity.ok("Student registered successfully.");
//    }
//
//    @PostMapping("/register/author")
//    public ResponseEntity<String> registerAuthor(@RequestBody Author author) {
//        // Implementation for registering an author
//        return ResponseEntity.ok("Author registered successfully.");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
//        // Implementation for user login and JWT generation
//        return ResponseEntity.ok("JWT generated successfully.");
//    }
//
//    // Book Operations
//
//    @GetMapping("/books")
//    public ResponseEntity<List<Book>> getAllBooks() {
//        // Implementation to get all books
//        return ResponseEntity.ok(/* List of all books */);
//    }
//
//    @GetMapping("/books/{bookId}")
//    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
//        // Implementation to get book details by ID
//        return ResponseEntity.ok(/* Book details */);
//    }
//
//    @PostMapping("/books")
//    public ResponseEntity<String> createBook(@RequestBody Book book) {
//        // Implementation to create a new book
//        return ResponseEntity.ok("Book created successfully.");
//    }
//
//    @DeleteMapping("/books/{bookId}")
//    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
//        // Implementation to delete a book by ID
//        return ResponseEntity.ok("Book deleted successfully.");
//    }
//
//    @PostMapping("/books/{bookId}/readers/{studentId}")
//    public ResponseEntity<String> addReaderToBook(@PathVariable Long bookId, @PathVariable Long studentId) {
//        // Implementation to add a reader to a book
//        return ResponseEntity.ok("Reader added to the book.");
//    }
//
//    // Book Subscriptions
//
//    @PostMapping("/students/{studentId}/subscribe/{authorId}")
//    public ResponseEntity<String> subscribeToAuthor(@PathVariable Long studentId, @PathVariable Long authorId) {
//        // Implementation for a student to subscribe to an author
//        return ResponseEntity.ok("Student subscribed to author successfully.");
//    }
//
//    @GetMapping("/authors/{authorId}/books")
//    public ResponseEntity<List<Book>> getAuthorBooks(@PathVariable Long authorId) {
//        // Implementation to get books of a specific author
//        return ResponseEntity.ok(/* List of books by author */);
//    }
//
//    // Reader Lists
//
//    @GetMapping("/books/{bookId}/readers")
//    public ResponseEntity<List<Reader>> getReadersOfBook(@PathVariable Long bookId) {
//        // Implementation to get readers of a specific book
//        return ResponseEntity.ok(/* List of readers for the book */);
//    }
//
//    @GetMapping("/students/{studentId}/reading-list")
//    public ResponseEntity<List<Book>> getStudentReadingList(@PathVariable Long studentId) {
//        // Implementation to get reading list of a student
//        return ResponseEntity.ok(/* List of books the student is reading */);
//    }
//
//    // Other functionalities based on the requirements
//
//    // ...Additional endpoints for further functionalities, notifications, etc.
//}
//
