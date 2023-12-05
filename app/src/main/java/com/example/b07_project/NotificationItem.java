package com.example.b07_project;

public class NotificationItem {

    private String title;
    private String content;

    // Empty constructor required for Firebase
    public NotificationItem() {
    }

    public NotificationItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return title + ": " + content;
    }
}