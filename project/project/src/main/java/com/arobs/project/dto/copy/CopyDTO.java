package com.arobs.project.dto.copy;

public class CopyDTO {


    private boolean copyFlag;

    private String copyStatus;

    private int book;

    public CopyDTO(boolean copyFlag, String copyStatus, int book) {
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

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }
}
