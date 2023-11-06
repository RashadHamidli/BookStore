package com.company.mapper;

import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    private final BookMapper bookMapper;

    public AuthorMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public AuthorDTO authorConvertToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setAge(author.getAge());
        authorDTO.setPassword(author.getPassword());

        List<BookDTO> authoredBooks = author.getAuthoredBooks()
                .stream()
                .map(bookMapper::bookConvertToBookDTO)
                .collect(Collectors.toList());
        authorDTO.setAuthoredBooks(authoredBooks);

        return authorDTO;
    }

    public Author authorConvertToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setAge(authorDTO.getAge());
        author.setPassword(authorDTO.getPassword());

        List<Book> authoredBooks = authorDTO.getAuthoredBooks()
                .stream()
                .map(bookMapper::BookDTOConvertToBook)
                .collect(Collectors.toList());
        author.setAuthoredBooks(authoredBooks);

        return author;
    }

}
