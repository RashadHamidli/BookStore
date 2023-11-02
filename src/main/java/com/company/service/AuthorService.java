package com.company.service;

import com.company.dao.AuthorRepository;
import com.company.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    public Author getOneAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author creatOneAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteOneAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author updateOneAuthorById(Long id, Author newAuthor) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            Author foundAuthor = new Author();
            foundAuthor.setName(newAuthor.getName());
            foundAuthor.setAge(newAuthor.getAge());
            foundAuthor.setPassword(newAuthor.getPassword());
            foundAuthor.setAuthoredBooks(newAuthor.getAuthoredBooks());
            return authorRepository.save(foundAuthor);
        } else
            return null;
    }

    public Author getOneAuthorByName(String name) {
        return authorRepository.findByName(name);
    }
}
