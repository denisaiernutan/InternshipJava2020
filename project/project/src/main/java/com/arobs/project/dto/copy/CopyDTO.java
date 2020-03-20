package com.arobs.project.dto.copy;

public class CopyDTO {


    private boolean copyFlag;


    private int book;

    public CopyDTO(boolean copyFlag, int book) {
        this.copyFlag = copyFlag;
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

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }
}
