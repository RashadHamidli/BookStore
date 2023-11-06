package com.company.mapper;

import com.company.dto.AuthorDTO;
import com.company.entity.Author;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(source = "authoredBooks", target = "authoredBooks")
    Author authorDTOtoAuthor(AuthorDTO authorDTO);

    @Mapping(source = "authoredBooks", target = "authoredBooks")
    AuthorDTO authorToAuthorDTO(Author author);
}
