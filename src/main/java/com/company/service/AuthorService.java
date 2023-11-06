package com.company.service;

import com.company.dao.AuthorRepository;
import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import jakarta.persistence.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Cacheable
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthor() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(author -> {
            AuthorDTO authorDTO = convertToDTO(author);
            List<BookDTO> authoredBooks = author.getAuthoredBooks()
                    .stream()
                    .map(this::convertToBookDTO)
                    .collect(Collectors.toList());
            authorDTO.setAuthoredBooks(authoredBooks);
            return authorDTO;
        }).collect(Collectors.toList());
    }

    public AuthorDTO getAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author foundedAuthor = optionalAuthor.get();
            AuthorDTO authorDTO = convertToDTO(foundedAuthor);
            List<Book> authoredBooks = foundedAuthor.getAuthoredBooks();
            List<BookDTO> bookDTOList = authoredBooks.stream().map(this::convertToBookDTO).toList();
            authorDTO.setAuthoredBooks(bookDTOList);
            return authorDTO;
        }
        return new AuthorDTO();
    }


    public AuthorDTO creatAuthor(AuthorDTO newAuthorDTO) {
        Author author = convertToEntity(newAuthorDTO);
        Author saveAuthor = authorRepository.save(author);
        return convertToDTO(saveAuthor);
    }

    public boolean deleteAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO newAuthorDTO) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author foundedAuthor = optionalAuthor.get();
            if (newAuthorDTO.getName() != null && !newAuthorDTO.getName().isEmpty())
                foundedAuthor.setName(newAuthorDTO.getName());
            if (newAuthorDTO.getEmail() != null && !newAuthorDTO.getEmail().isEmpty())
                foundedAuthor.setEmail(newAuthorDTO.getEmail());
            if (newAuthorDTO.getAge() != null)
                foundedAuthor.setAge(newAuthorDTO.getAge());
            if (newAuthorDTO.getPassword() != null && !newAuthorDTO.getPassword().isEmpty())
                foundedAuthor.setPassword(newAuthorDTO.getPassword());
            if (newAuthorDTO.getAuthoredBooks() != null && !newAuthorDTO.getAuthoredBooks().isEmpty()) {
                List<BookDTO> bookDTOList = newAuthorDTO.getAuthoredBooks();
                List<Book> list = bookDTOList.stream().map(this::convertToBookEntity).toList();
                foundedAuthor.setAuthoredBooks(list);
            }
            foundedAuthor.setId(id);
            Author author = authorRepository.save(foundedAuthor);
            return convertToDTO(author);
        }
        return new AuthorDTO();
    }


    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setAge(author.getAge());
        authorDTO.setPassword(author.getPassword());
        return authorDTO;
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

    private BookDTO convertToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

    private Book convertToBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        Author author = new Author();
        author.setId(bookDTO.getAuthorId());
        book.setAuthor(author);
        return book;
    }


}
