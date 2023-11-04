package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_books1")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class studentBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
