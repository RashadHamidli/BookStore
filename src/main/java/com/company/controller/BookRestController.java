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

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        // Retrieve all books
        List<BookDTO> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId) {
        // Implementation to get book details by ID
        BookDTO bookDTO = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookDTO);
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


    //    @GetMapping("/{bookId}/readers")
//    public ResponseEntity<List<Reader>> getReadersOfBook(@PathVariable Long bookId) {
//        // Implement retrieving readers of a specific book
//        List<Reader> readers = bookService.getReadersOfBook(bookId);
//        return ResponseEntity.ok(readers);
//    }
    @PostMapping("/{bookId}/readers/{studentId}")
    public ResponseEntity<String> addReaderToBook(@PathVariable Long bookId, @PathVariable Long studentId) {
        // Implementation to add a reader to a book
        return ResponseEntity.ok("Reader added to the book.");
    }

}
