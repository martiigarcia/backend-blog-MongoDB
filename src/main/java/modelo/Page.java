package modelo;

import exceptions.NotNullNotEmptyException;

import java.util.Date;
import java.util.Objects;

public class Page {

    private String title;//◦ title: Un título de la página
    private String text;//◦ text: El texto completo de la página
    private String author;//◦ author: El autor de la página.
    private Date date;//◦ date: La fecha de publicación de la página en el blog.

    public Page() {

    }

    public Page(String title, String text, String author, Date date) {

        var check = new NotNullNotEmptyException("titulo", title, "texto", text, "autor", author, "fecha", date);
        check.throwOnError();

        this.title = title;
        this.text = text;
        this.author = author;
        this.date = date;
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

    @Override
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(title, page.title) &&
                Objects.equals(text, page.text) &&
                Objects.equals(author, page.author) &&
                Objects.equals(date, page.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, author, date);
    }
}
