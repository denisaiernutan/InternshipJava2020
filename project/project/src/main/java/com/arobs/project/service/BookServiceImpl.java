package com.arobs.project.service;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.BookDTO;
import com.arobs.project.dto.TagDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookTag;
import com.arobs.project.entity.Tag;
import com.arobs.project.repository.BookJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class BookServiceImpl implements  BookService{

    BookJDBCRepository bookJDBCRepository;
    TagServiceImpl tagService;
    BookTagService bookTagService;

    @Autowired
    public BookServiceImpl(BookJDBCRepository bookJDBCRepository, TagServiceImpl tagService, BookTagService bookTagService) {
        this.bookJDBCRepository = bookJDBCRepository;
        this.tagService = tagService;
        this.bookTagService = bookTagService;
    }

    public BookDTO insertBook(BookDTO bookDTO) {
        Book book= BookConverter.convertToEntity(bookDTO);
        book.setBookAddedDate(new Timestamp(System.currentTimeMillis()));
        book=bookJDBCRepository.insertBook(book);

        if(bookDTO.getTagList().size()!=0){
            for(TagDTO tagDTO: bookDTO.getTagList()){
                Tag tag=tagService.findByDescription(tagDTO.getTagDescription());
                if (tag == null) {
                    tag = TagConverter.convertToEntity(tagService.insertTag(tagDTO.getTagDescription()));
                }
                bookTagService.insertBookTag(new BookTag(book.getBookId(),tag.getTagId()));
            }
        }
        return BookConverter.convertToDTO(book);
    }

}
