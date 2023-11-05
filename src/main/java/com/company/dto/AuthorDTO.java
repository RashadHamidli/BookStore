package com.company.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String password;
    private List<BookDTO> authoredBooks;
}
