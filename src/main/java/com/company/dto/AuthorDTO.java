package com.company.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String name;
    private int age;
    private String email;
}
