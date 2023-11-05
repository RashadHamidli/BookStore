package com.company.service;

import com.company.dao.BookRepository;
import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public BookDTO creatBook(BookDTO newBookDTO) {
        Book book = convertToEntity(newBookDTO);
        Book saveBook = bookRepository.save(book);
        return convertToDTO(saveBook);
    }
    //------------------------------------------------------------
    public BookDTO creatBookByAuthorId(Long id, BookDTO newBookDTO) {
        AuthorDTO authorDTO = authorService.getAuthor(id);
        Author author = convertToEntity(authorDTO);
        Book book = convertToBookEntity(author, newBookDTO);
        Book saveBook = bookRepository.save(book);
        return convertToDTO(saveBook);
    }

    private Book convertToBookEntity(Author author, BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(author);
        return book;
    }

    private Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setAge(authorDTO.getAge());
        author.setPassword(authorDTO.getPassword());
        return author;
    }

    //------------------------------------------------------------
    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        Author author = new Author();
        author.setId(bookDTO.getAuthorId());
        book.setAuthor(author);
        return book;
    }
}


