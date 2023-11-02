package com.company.controller;

import com.company.dto.AuthorDTO;
import com.company.entity.Author;
import com.company.service.AuthorService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Author author = authorService.getOneAuthorById(id).orElse(null);
        return ResponseEntity.ok(author);
    }

    @PostMapping()
    public ResponseEntity<Author> createOneAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setAge(authorDTO.getAge());
        author.setPassword(author.getPassword());
        authorService.saveOneAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateOneAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        Optional<Author> optionalAuthor = authorService.getOneAuthorById(id);
        if (optionalAuthor.isPresent()) {
            Author author = new Author();
            author.setName(authorDTO.getName());
            author.setEmail(authorDTO.getEmail());
            author.setAge(authorDTO.getAge());
            author.setPassword(author.getPassword());
            authorService.updateOneAuthorById(id, author);
            return ResponseEntity.ok("update successfully");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("author not found");
    }
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
            authorService.deleteOneAuthorById(id);
            ResponseEntity.ok("delete successfully");
    }
}
