package com.company.service;

import com.company.dao.BookRepository;
import com.company.entity.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getOneBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveOneBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateOneBookById(Long id, Book newBook) {
        Optional<Book> foundedBook = bookRepository.findById(id);
        if (foundedBook.isPresent()) {
            Book book = foundedBook.get();
            book.setName(newBook.getName());
            book.setAuthor(newBook.getAuthor());
            bookRepository.save(book);
            return book;
        } else
            return null;
    }

    public void deleteOneBookById(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Book " + id + " doesn't exist");
        }
    }

    public Book getOneBookByName(String name) {
        return bookRepository.findByName(name);
    }
}


