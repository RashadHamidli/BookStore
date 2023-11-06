package com.company.controller;

import com.company.dto.AuthorDTO;
import com.company.entity.Author;
import com.company.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthor() {
        List<AuthorDTO> authorDTOS = authorService.getAllAuthor();
        return ResponseEntity.ok(authorDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
        AuthorDTO authorDTO = authorService.getAuthor(id);
        return ResponseEntity.ok(authorDTO);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> creatAuthor(@RequestBody AuthorDTO newAuthorDTO) {
        AuthorDTO authorDTO = authorService.creatAuthor(newAuthorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO newAuthorDTO) {
        AuthorDTO authorDTO = authorService.updateAuthor(id, newAuthorDTO);
        return ResponseEntity.ok(authorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id) ? ResponseEntity.ok("Student with ID " + id + " has been deleted")
                : ResponseEntity.notFound().build();
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerAuthor(@RequestBody Author author) {
        // Implement user registration for an author
        authorService.registerAuthor(author);
        return ResponseEntity.ok("Author registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        // Implement user login and JWT token generation
        String jwt = authorService.login(userLogin);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/{authorId}/books")
    public ResponseEntity<String> createBook(@PathVariable Long authorId, @RequestBody Book book) {
        // Implement creating a new book by the author
        authorService.createBook(authorId, book);
        return ResponseEntity.ok("Book created successfully.");
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        // Implement deleting a book by bookId (validate author's permission)
        authorService.deleteBook(bookId);
        return ResponseEntity.ok("Book deleted successfully.");
    }

    @PostMapping("/{authorId}/notify-subscribers")
    public ResponseEntity<String> notifySubscribersForNewBook(@PathVariable Long authorId, @RequestBody Book book) {
        // Implementation to notify subscribers about a new book from the author
        return ResponseEntity.ok("Subscribers notified about the new book.");
    }

    @DeleteMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<String> deleteBookByAuthor(@PathVariable Long authorId, @PathVariable Long bookId) {
        // Implementation to delete a book by the author
        return ResponseEntity.ok("Book deleted successfully.");
    }
}
