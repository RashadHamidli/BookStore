package com.company.mapper;

import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import org.springframework.stereotype.Component;


@Component
public class BookMapper {

    public BookDTO bookConvertToBookDTO(Book book) {//toDTO
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

    public Book BookDTOConvertToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        Author author = new Author();
        author.setId(bookDTO.getAuthorId());
        book.setAuthor(author);
        return book;
    }

}
