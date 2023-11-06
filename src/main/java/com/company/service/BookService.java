package com.company.service;

import com.company.dao.BookRepository;
import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.mapper.AuthorMapper;
import com.company.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, AuthorService authorService, AuthorMapper authorMapper, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
    }
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::bookConvertToBookDTO).collect(Collectors.toList());
    }
    public BookDTO getBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return bookMapper.bookConvertToBookDTO(book);
        }
        return new BookDTO();
    }
    public BookDTO creatBook(BookDTO newBookDTO) {
        Book book = bookMapper.BookDTOConvertToBook(newBookDTO);
        Book saveBook = bookRepository.save(book);
        return bookMapper.bookConvertToBookDTO(saveBook);
    }

    public BookDTO creatBookByAuthorId(Long id, BookDTO newBookDTO) {
        AuthorDTO authorDTO = authorService.getAuthor(id);
        Author author = authorMapper.authorConvertToAuthor(authorDTO);
        Book book = bookMapper.BookDTOConvertToBook(author, newBookDTO);
        Book saveBook = bookRepository.save(book);
        return bookMapper.bookConvertToBookDTO(saveBook);
    }
}


