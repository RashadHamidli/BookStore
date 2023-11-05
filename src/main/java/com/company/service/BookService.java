package com.company.service;

import com.company.dao.BookRepository;
import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.mapper.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, AuthorService authorService, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookMapper = bookMapper;
    }

    public BookDTO creatBook(BookDTO newBookDTO) {
        Book book = bookMapper.convertToEntity(newBookDTO);
        Book saveBook = bookRepository.save(book);
        return bookMapper.convertToDTO(saveBook);
    }

    public BookDTO creatBookByAuthorId(Long id, BookDTO newBookDTO) {
        AuthorDTO authorDTO = authorService.getAuthor(id);
        Author author = bookMapper.convertToAuthorEntity(authorDTO);
        Book book = bookMapper.convertToEntity(author, newBookDTO);
        Book saveBook = bookRepository.save(book);
        return bookMapper.convertToDTO(saveBook);
    }
}


