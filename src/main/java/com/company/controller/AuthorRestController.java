package com.company.controller;

import com.company.dto.AuthorDTO;
import com.company.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
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
}
