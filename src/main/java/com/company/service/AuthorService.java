package com.company.service;

import com.company.dao.AuthorRepository;
import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.dto.StudentDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    private List<AuthorDTO> getAllAuthor() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(author -> {
            AuthorDTO authorDTO = convertToDTO(author);
            List<BookDTO> authoredBooks = author.getAuthoredBooks()
                    .stream()
                    .map(this::converteToBookDTO)
                    .collect(Collectors.toList());
            authorDTO.setAuthoredBooks(authoredBooks);
            return authorDTO;
        }).collect(Collectors.toList());
    }

    public AuthorDTO getOneAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author foundedAuthor = optionalAuthor.get();
            AuthorDTO authorDTO = convertToDTO(foundedAuthor);
            List<Book> authoredBooks = foundedAuthor.getAuthoredBooks();
            List<BookDTO> bookDTOList = authoredBooks.stream().map(this::converteToBookDTO).toList();
            authorDTO.setAuthoredBooks(bookDTOList);
            return authorDTO;
        }
        return new AuthorDTO();
    }

    private List<BookDTO> convertToAuthorDTOList(List<Book> authors) {
        return authors.stream().map(this::converteToBookDTO).collect(Collectors.toList());
    }

    public AuthorDTO creatOneAuthor(AuthorDTO newAuthorDTO) {
        Author author = converteToEntity(newAuthorDTO);
        Author saveAuthor = authorRepository.save(author);
        return convertToDTO(saveAuthor);
    }


    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(authorDTO.getId());
        authorDTO.setName(author.getName());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setAge(author.getAge());
        authorDTO.setPassword(author.getPassword());
        return authorDTO;
    }

    private Author converteToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setAge(authorDTO.getAge());
        author.setPassword(authorDTO.getPassword());
        return author;
    }

    private BookDTO converteToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

    private Book converteToBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        return book;
    }


}
