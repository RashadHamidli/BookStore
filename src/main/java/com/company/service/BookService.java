package com.company.service;

import com.company.dao.BookRepository;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO creatBook(BookDTO newBookDTO) {
        Book book = convertToEntity(newBookDTO);
        Book saveBook = bookRepository.save(book);
        return convertToDTO(saveBook);
    }

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


