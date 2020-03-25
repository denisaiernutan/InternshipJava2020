package com.arobs.project.converter;

import com.arobs.project.dto.bookRent.BookRentReturnDTO;
import com.arobs.project.dto.bookRent.BookRentSimpleDTO;
import com.arobs.project.dto.bookRent.BookRentWithIdDTO;
import com.arobs.project.dto.copy.CopyWithoutBookDTO;
import com.arobs.project.entity.BookRent;
import org.modelmapper.ModelMapper;

public class BookRentConverter {

    private static ModelMapper modelMapper = new ModelMapper();


    public static BookRent convertToEntity(BookRentReturnDTO bookRentReturnDTO) {
        return modelMapper.map(bookRentReturnDTO, BookRent.class);
    }

    public static BookRentWithIdDTO convertToBookRentWithIdDTO(BookRent bookRent) {
        BookRentWithIdDTO bookRentWithIdDTO = new BookRentWithIdDTO(bookRent.getBookRentId(), bookRent.getRentalDate(),
                bookRent.getReturnDate(), bookRent.getBookRentStatus(), bookRent.getGrade());

        bookRentWithIdDTO.setBookId(bookRent.getBook().getBookId());
        CopyWithoutBookDTO copyWithoutBookDTO = new CopyWithoutBookDTO(bookRent.getCopy().getCopyId(),
                bookRent.getCopy().isCopyFlag(), bookRent.getCopy().getCopyStatus());
        bookRentWithIdDTO.setCopy(copyWithoutBookDTO);
        bookRentWithIdDTO.setEmployee(bookRent.getEmployee().getEmployeeId());

        return bookRentWithIdDTO;
    }

    public static BookRentSimpleDTO convertToSimpleDTO(BookRent bookRent) {
        return modelMapper.map(bookRent, BookRentSimpleDTO.class);
    }

}
