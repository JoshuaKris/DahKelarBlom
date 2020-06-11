package com.example.dahkelarblom.model;

public class SpinnerStatus {
    private String status;
    private boolean selected;

    public SpinnerStatus(String status, boolean selected) {
        this.status = status;
        this.selected = selected;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
