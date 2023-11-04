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
    private Long id;
    private String name;
    private List<BookDTO> booksReading;
}
