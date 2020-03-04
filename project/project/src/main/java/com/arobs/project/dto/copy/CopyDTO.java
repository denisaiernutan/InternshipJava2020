package com.arobs.project.dto.copy;

import com.arobs.project.dto.book.BookIdDTO;


public class CopyDTO {


    private boolean copyFlag;

    private String copyStatus;

    private BookIdDTO book;

    public CopyDTO(boolean copyFlag, String copyStatus, BookIdDTO book) {
        this.copyFlag = copyFlag;
        this.copyStatus = copyStatus;
        this.book = book;
    }

    public CopyDTO() {
    }

    public boolean isCopyFlag() {
        return copyFlag;
    }

    public void setCopyFlag(boolean copyFlag) {
        this.copyFlag = copyFlag;
    }

    public String getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(String copyStatus) {
        this.copyStatus = copyStatus;
    }

    public BookIdDTO getBook() {
        return book;
    }

    public void setBook(BookIdDTO book) {
        this.book = book;
    }
}
