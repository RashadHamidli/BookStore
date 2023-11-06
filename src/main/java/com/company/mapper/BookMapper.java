package com.company.mapper;

import com.company.dto.BookDTO;
import com.company.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "authorId", target = "authorId")
    Book bookDTOToBook(BookDTO bookDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "author", target = "author")
    BookDTO bookToBookDTO(Book book);
}
