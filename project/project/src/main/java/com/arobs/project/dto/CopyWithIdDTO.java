package com.arobs.project.dto;

public class CopyWithIdDTO {

    private int copyId;

    private boolean copyFlag;

    private String copyStatus;

    private BookWithIdDTO book;

    public CopyWithIdDTO(int copyId, boolean copyFlag, String copyStatus, BookWithIdDTO book) {
        this.copyId = copyId;
        this.copyFlag = copyFlag;
        this.copyStatus = copyStatus;
        this.book = book;
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

    public BookWithIdDTO getBook() {
        return book;
    }

    public void setBook(BookWithIdDTO book) {
        this.book = book;
    }
}
