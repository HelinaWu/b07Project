package com.example.b07_project;

public class AnnouncementModel {
    private String title;
    private String content;

    public AnnouncementModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setAnnouncement(String content) {
        this.content = content;
    }
}
