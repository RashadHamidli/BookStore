package com.company.service;

import com.company.dao.AuthorRepository;
import com.company.dao.BookRepository;
import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.mapper.AuthorMapper;
import com.company.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, AuthorMapper authorMapper, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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

    public AuthorDTO login(AuthorDTO authorDTO) {
        Author author = authorMapper.authorConvertToAuthor(authorDTO);
        Author foundAuthor = authorRepository.findByEmail(author.getEmail());
        if (foundAuthor != null && foundAuthor.getPassword().equals(author.getPassword())) {
            return authorDTO; // Burada gerçek JWT üretme işlemi yapılmalıdır
        } else {
            throw new RuntimeException("Invalid email or password.");
        }
    }

    public BookDTO createBook(Long id, BookDTO newBookDTO) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            Book book = bookMapper.BookDTOConvertToBook(author, newBookDTO);
            Book saveBook = bookRepository.save(book);
            return bookMapper.bookConvertToBookDTO(saveBook);
        }
        return null;
    }

    public boolean deleteBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }

    public boolean deleteBookByAuthor(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + authorId));

        Book bookToDelete = author.getAuthoredBooks().stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId + " for the author"));

        author.getAuthoredBooks().remove(bookToDelete);
        authorRepository.save(author);
        return true;
    }
//    public void notifySubscribersForNewBook(Long authorId, BookDTO book) {
//        Author author = authorRepository.findById(authorId)
//                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + authorId));
//
//        // Burada yeni kitapla ilgili işlemler yapılabilir, örneğin kitabı veritabanına kaydedebilirsiniz.
//
//        // Ardından, abonelerin e-posta ile bilgilendirilmesi gerçekleştirilebilir.
//        List<Student> subscribers = /* Burada abonelerin alınması, örneğin: author.getSubscribers() */;
//        for (Student subscriber : subscribers) {
//            emailService.sendEmail(subscriber.getEmail(), "Yeni Kitap Yayınlandı",
//                    "Sevgili " + subscriber.getName() + ",\n" +
//                            author.getName() + " adlı yazarın yeni kitabı '" + book.getName() + "' şimdi raflardaki yerini aldı!");
//        }
//    }
}
