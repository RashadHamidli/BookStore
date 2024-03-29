package com.company.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

@Entity
@Table(name = "students")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_age")
    private Integer age;

    @Column(name = "student_email")
    private String email;

    @Column(name = "student_password")
    private String password;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "student_books",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    private List<Book> booksReading;
}
