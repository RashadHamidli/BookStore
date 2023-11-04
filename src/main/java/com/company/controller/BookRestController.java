package com.company.controller;

import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.entity.Student;
import com.company.service.AuthorService;
import com.company.service.BookService;
import com.company.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/books")
public class BookRestController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final StudentService studentService;

    public BookRestController(BookService bookService, AuthorService authorService, StudentService studentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOneBook(@PathVariable Long id) {
        Book book = bookService.getOneBookById(id).orElse(null);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/{authorid}")
    public ResponseEntity<Book> createBook(@PathVariable Long authorid, @RequestBody BookDTO bookDTO) {
        Author author = authorService.getOneAuthorById(authorid).get();
        Book book = new Book();
        book.setAuthor(author);
        book.setName(bookDTO.getName());
        bookService.saveOneBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteOneBook(@PathVariable Long id) {
        bookService.deleteOneBookById(id);
        ResponseEntity.ok("delete successfully");
    }
//    @PostMapping("/{id}")
//    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
//        Optional<Book> optionalBook = bookService.getOneBookById(id);
//        if (optionalBook.isPresent()) {
//            Book book = new Book();
//            book.setName(bookDTO.getName());
//            bookService.updateOneBookById(id, book);
//            return ResponseEntity.ok("update successfullu");
//        } else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book not found");
//    }
}
