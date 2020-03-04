package com.arobs.project.dto.copy;

public class CopyUpdateDTO {

    private int copyId;

    private boolean copyFlag;

    private String copyStatus;

    public CopyUpdateDTO(int copyId, boolean copyFlag, String copyStatus) {
        this.copyId = copyId;
        this.copyFlag = copyFlag;
        this.copyStatus = copyStatus;
    }

    public CopyUpdateDTO() {
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
}
