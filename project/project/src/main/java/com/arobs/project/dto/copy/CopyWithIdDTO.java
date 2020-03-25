package com.arobs.project.dto.copy;

import com.arobs.project.dto.book.BookWithIdDTO;

public class CopyWithIdDTO {

    private int copyId;

    private boolean copyFlag;

    private String copyStatus;

    private BookWithIdDTO bookWithIdDTO;

    public CopyWithIdDTO(int copyId, boolean copyFlag, String copyStatus, BookWithIdDTO bookWithIdDTO) {
        this.copyId = copyId;
        this.copyFlag = copyFlag;
        this.copyStatus = copyStatus;
        this.bookWithIdDTO = bookWithIdDTO;
    }

    public CopyWithIdDTO() {
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
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

    public BookWithIdDTO getBookWithIdDTO() {
        return bookWithIdDTO;
    }

    public void setBookWithIdDTO(BookWithIdDTO bookWithIdDTO) {
        this.bookWithIdDTO = bookWithIdDTO;
    }
}
