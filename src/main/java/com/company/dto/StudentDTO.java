package com.company.dto;

import com.company.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String name;
    private String surname;
    private int age;
    private String email;
    public String password;
    public List<Book> books;
}
