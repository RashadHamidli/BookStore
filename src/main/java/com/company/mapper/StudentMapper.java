package com.company.mapper;

import com.company.dto.StudentDTO;
import com.company.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "booksReading", target = "booksReading")
    Student studentDTOtoStudent(StudentDTO studentDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "booksReading", target = "booksReading")
    StudentDTO studentToStudentDTO(Student student);
}
