package com.company.controller;

import com.company.dto.BookDTO;
import com.company.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
