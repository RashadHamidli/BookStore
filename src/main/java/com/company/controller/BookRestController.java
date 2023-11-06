package com.company.controller;

import com.company.dto.BookDTO;
import com.company.entity.Book;
import com.company.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
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
    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        // Retrieve all books
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{bookId}/readers")
    public ResponseEntity<List<Reader>> getReadersOfBook(@PathVariable Long bookId) {
        // Implement retrieving readers of a specific book
        List<Reader> readers = bookService.getReadersOfBook(bookId);
        return ResponseEntity.ok(readers);
    }

}
