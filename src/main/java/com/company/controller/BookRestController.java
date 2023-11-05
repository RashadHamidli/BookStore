package com.company.controller;

import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.service.AuthorService;
import com.company.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> creatBook(@RequestBody BookDTO newBookDTO) {
        BookDTO bookDTO = bookService.creatBook(newBookDTO);
        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping("author/{id}")
    public ResponseEntity<BookDTO> creatBookByAuthorId(@PathVariable Long id, @RequestBody BookDTO newBookDTO) {
            BookDTO bookDTO = bookService.creatBookByAuthorId(id, newBookDTO);
            return ResponseEntity.ok(bookDTO);
    }
}
