package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authors")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;
    @Column(name = "author_name")
    private String name;
    @Column(name = "author_age")
    private String age;
    @Column(name = "author_password")
    private String password;
    @OneToMany(mappedBy = "author")
    private List<Book> authoredBooks;
}
