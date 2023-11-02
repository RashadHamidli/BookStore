package com.company.controller;

import com.company.dto.AuthorDTO;
import com.company.entity.Author;
import com.company.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getOneAuthor(@PathVariable Long id) {
        Author author = authorService.getOneAuthorById(id);
        return ResponseEntity.ok(author);
    }
    @PostMapping()
    public ResponseEntity<Author> createOneAuthor(@RequestBody AuthorDTO authorDTO){
        return null;
    }
}
