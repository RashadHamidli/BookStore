package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
}
