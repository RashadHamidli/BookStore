package com.company.controller;

import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.service.AuthorService;
import com.company.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookRestController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOs = books.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getOneBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.getOneBookById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            BookDTO bookDTO = convertToBookDTO(book);
            return ResponseEntity.ok(bookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> createBook(@RequestBody BookDTO newBookDTO) {
        Optional<Author> optionalAuthor = authorService.getOneAuthorById(newBookDTO.getAuthorId());
        if (optionalAuthor.isPresent()) {
            Book newBook = convertToBookEntity(newBookDTO, optionalAuthor.get());
            bookService.saveOneBook(newBook);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteOneBook(@PathVariable Long id) {
//        boolean isDeleted = bookService.deleteOneBookById(id);
//        return isDeleted ? ResponseEntity.ok("Deleted successfully") : ResponseEntity.notFound().build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookDTO updatedBookDTO) {
        Optional<Book> optionalBook = bookService.getOneBookById(id);
        if (optionalBook.isPresent()) {
            Book updatedBook = convertToBookEntity(updatedBookDTO);
            bookService.updateOneBookById(id, updatedBook);
            return ResponseEntity.ok("Updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private BookDTO convertToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

    private Book convertToBookEntity(BookDTO bookDTO, Author author) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(author);
        return book;
    }

    private Book convertToBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        // Set other properties if needed
        return book;
    }
}

