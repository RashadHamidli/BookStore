package com.company.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private String name;
    private String surname;
    private int age;
    private String email;
    public String password;
}
