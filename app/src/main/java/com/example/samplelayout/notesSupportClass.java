package com.example.samplelayout;

public class notesSupportClass {
    String notes, userId;

    public notesSupportClass() {
    }

    public notesSupportClass(String notes, String userId) {
        this.notes = notes;
        this.userId=userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
