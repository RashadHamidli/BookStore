package com.company.mapper;

import com.company.dto.BookDTO;
import com.company.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "author.id", target = "authorId")
    BookDTO bookToBookDTO(Book book);

    @Mapping(source = "authorId", target = "author.id")
    Book bookDTOtoBook(BookDTO bookDTO);
}
