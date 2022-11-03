package web;

import java.util.Date;

public class PageDTO {

    private String title;//◦ title: Un título de la página
    private String text;//◦ text: El texto completo de la página
    private String author;//◦ author: El autor de la página.
    private Date date;//◦ date: La fecha de publicación de la página en el blog.

    public PageDTO(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
