package com.company.service;

import com.company.dao.AuthorRepository;
import com.company.entity.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getOneAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author saveOneAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteOneAuthorById(Long id) {
        try {
            authorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Author " + id + " doesn't exist");
        }
    }

    public Author updateOneAuthorById(Long id, Author newAuthor) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            Author foundAuthor = new Author();
            foundAuthor.setName(newAuthor.getName());
            foundAuthor.setAge(newAuthor.getAge());
            foundAuthor.setEmail(newAuthor.getEmail());
            foundAuthor.setPassword(newAuthor.getPassword());
            foundAuthor.setAuthoredBooks(newAuthor.getAuthoredBooks());
            authorRepository.save(foundAuthor);
            return foundAuthor;
        } else
            return null;
    }

    public Author getOneAuthorByName(String name) {
        return authorRepository.findByName(name);
    }
}
