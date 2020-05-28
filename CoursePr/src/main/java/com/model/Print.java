package com.model;

public class Print {
    int id;
    private Template template;
    private Image image;

    public Print(int id, Template template, Image image){
        this.id = id;
        this.template = template;
        this.image = image;
    }

    public Print(Template template, Image image){
        this.template = template;
        this.image = image;
    }

    public Print(){
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
