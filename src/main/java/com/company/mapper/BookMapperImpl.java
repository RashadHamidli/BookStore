package com.company.mapper;

import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl {

    public BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

    public Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        Author author = new Author();
        author.setId(bookDTO.getAuthorId());
        book.setAuthor(author);
        return book;
    }

    public Book convertToEntity(Author author, BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(author);
        return book;
    }

    public Author convertToAuthorEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setAge(authorDTO.getAge());
        author.setPassword(authorDTO.getPassword());
        return author;
    }
}
