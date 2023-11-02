package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "book_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;



}
