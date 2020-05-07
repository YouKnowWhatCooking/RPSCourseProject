package com.model;

public class Image {
    private int id;
    private String title;
    private String theme;
    private String link;

    public Image(int id, String title, String theme, String link) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.link = link;
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

}