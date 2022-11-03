package web;

import java.util.Date;

public class PostDTO {

    private String title; //◦ title: Un título del post
    private String text; //◦ text: El texto completo del post.
    private String tags; //◦ tags: Palabras o frases cortas que definen la temática específica del post.
    private String resume; //◦ resume: resumen del post
    private String relatedLinks; //◦ relatedLinks: URLs de sitios web que hablen sobre el mismo tema (o relacionados) del que habla el post.
    private String author; //◦ author: El autor del post.
    private Date date; //◦ date: La fecha de publicación del post en el blog.

    public PostDTO(){

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getRelatedLinks() {
        return relatedLinks;
    }

    public void setRelatedLinks(String relatedLinks) {
        this.relatedLinks = relatedLinks;
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
