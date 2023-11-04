package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Columns;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Long id;
    @Column(name = "student_name")
    private String name;
    @Column(name = "student_age")
    private Integer age;
    @Column(name = "student_email")
    private String email;
    @Column(name = "student_password")
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "books",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @JsonIgnore
    private List<Book> booksReading;
}
