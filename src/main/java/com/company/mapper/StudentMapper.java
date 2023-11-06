package com.company.mapper;

import com.company.dto.StudentDTO;
import com.company.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "booksReading", target = "booksReading")
    Student studentDTOtoStudent(StudentDTO studentDTO);

    @Mapping(source = "booksReading", target = "booksReading")
    StudentDTO studentToStudentDTO(Student student);
}
