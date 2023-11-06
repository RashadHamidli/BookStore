package com.company.mapper;

import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapperImpl {

    public AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setAge(author.getAge());
        authorDTO.setPassword(author.getPassword());

        List<BookDTO> authoredBooks = author.getAuthoredBooks()
                .stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
        authorDTO.setAuthoredBooks(authoredBooks);

        return authorDTO;
    }

    public Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setAge(authorDTO.getAge());
        author.setPassword(authorDTO.getPassword());

        List<Book> authoredBooks = authorDTO.getAuthoredBooks()
                .stream()
                .map(this::convertToBookEntity)
                .collect(Collectors.toList());
        author.setAuthoredBooks(authoredBooks);

        return author;
    }

    public BookDTO convertToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

    public Book convertToBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        Author author = new Author();
        author.setId(bookDTO.getAuthorId());
        book.setAuthor(author);
        return book;
    }
}

