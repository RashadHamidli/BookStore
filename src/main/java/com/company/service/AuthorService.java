package com.company.service;

import com.company.dao.AuthorRepository;
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
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
    }

    public List<AuthorDTO> getAllAuthor() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(author -> {
            AuthorDTO authorDTO = authorMapper.authorConvertToAuthorDTO(author);
            List<BookDTO> authoredBooks = author.getAuthoredBooks()
                    .stream()
                    .map(bookMapper::bookConvertToBookDTO)
                    .collect(Collectors.toList());
            authorDTO.setAuthoredBooks(authoredBooks);
            return authorDTO;
        }).collect(Collectors.toList());
    }

    public AuthorDTO getAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author foundedAuthor = optionalAuthor.get();
            AuthorDTO authorDTO = authorMapper.authorConvertToAuthorDTO(foundedAuthor);
            List<Book> authoredBooks = foundedAuthor.getAuthoredBooks();
            List<BookDTO> bookDTOList = authoredBooks.stream()
                    .map(bookMapper::bookConvertToBookDTO)
                    .collect(Collectors.toList());
            authorDTO.setAuthoredBooks(bookDTOList);
            return authorDTO;
        }
        return new AuthorDTO();
    }

    public AuthorDTO creatAuthor(AuthorDTO newAuthorDTO) {
        Author author = authorMapper.authorConvertToAuthor(newAuthorDTO);
        Author saveAuthor = authorRepository.save(author);
        return authorMapper.authorConvertToAuthorDTO(saveAuthor);
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
                List<Book> list = bookDTOList.stream().map(bookMapper::BookDTOConvertToBook).toList();
                foundedAuthor.setAuthoredBooks(list);
            }
            foundedAuthor.setId(id);
            Author author = authorRepository.save(foundedAuthor);
            return authorMapper.authorConvertToAuthorDTO(author);
        }
        return new AuthorDTO();
    }
}
