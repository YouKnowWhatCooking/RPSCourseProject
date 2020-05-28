package com.model;

public class Image {
    private int id;
    private String title;
    private String theme;
    private String link;
    private String author;
    private String status;


    public Image(int id, String title, String theme, String link, String author, String status) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.link = link;
        this.author = author;
        this.status = status;
    }

    public Image(String title, String theme, String link, String author, String status) {
        this.title = title;
        this.theme = theme;
        this.link = link;
        this.author = author;
        this.status = status;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}