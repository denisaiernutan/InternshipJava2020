package com.arobs.project.converter;

import com.arobs.project.dto.bookRent.BookRentInsertDTO;
import com.arobs.project.dto.bookRent.BookRentWithIdDTO;
import com.arobs.project.dto.copy.CopyWithoutBookDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookRent;
import com.arobs.project.entity.Employee;
import org.modelmapper.ModelMapper;

public class BookRentConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static BookRent convertToEntity(BookRentInsertDTO bookRentInsertDTO) {
        BookRent bookRent = modelMapper.map(bookRentInsertDTO, BookRent.class);
        bookRent.setEmployee(new Employee(bookRentInsertDTO.getEmployee()));
        bookRent.setBook(new Book(bookRentInsertDTO.getBook()));
        return bookRent;
    }

    public static BookRentInsertDTO convertToBookRentInsertDTO(BookRent bookRent) {
        return modelMapper.map(bookRent, BookRentInsertDTO.class);
    }

    public static BookRentWithIdDTO convertToBookRentWithIdDTO(BookRent bookRent) {
        BookRentWithIdDTO bookRentWithIdDTO = new BookRentWithIdDTO(bookRent.getBookRentId(), bookRent.getRentalDate(), bookRent.getReturnDate(), bookRent.getBookRentStatus(), bookRent.getGrade());

        bookRentWithIdDTO.setBook(bookRent.getBook().getBookId());
        bookRentWithIdDTO.setCopy(new CopyWithoutBookDTO(bookRent.getCopy().getCopyId(), bookRent.getCopy().isCopyFlag(), bookRent.getCopy().getCopyStatus()));
        bookRentWithIdDTO.setEmployee(bookRent.getEmployee().getEmployeeId());

        return bookRentWithIdDTO;
    }
}
