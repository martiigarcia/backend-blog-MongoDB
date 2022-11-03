package modelo;

import exceptions.NotNullNotEmptyException;

import java.util.Date;
import java.util.Objects;

public class Post {

    private String title; //◦ title: Un título del post
    private String text; //◦ text: El texto completo del post.
    private String tags; //◦ tags: Palabras o frases cortas que definen la temática específica del post.
    private String resume; //◦ resume: resumen del post
    private String relatedLinks; //◦ relatedLinks: URLs de sitios web que hablen sobre el mismo tema (o relacionados) del que habla el post.
    private String author; //◦ author: El autor del post.
    private Date date; //◦ date: La fecha de publicación del post en el blog.

    public Post(){

    }

    public Post(String title, String text, String author, Date date, String tags, String resume, String relatedLinks) {

        var check = new NotNullNotEmptyException("titulo", title, "texto", text, "autor", author, "fecha", date);
        check.throwOnError();

        check = new NotNullNotEmptyException("tags", tags, "resumen", resume, "links relacionados", relatedLinks);
        check.throwOnError();

        this.title = title;
        this.text = text;
        this.tags = tags;
        this.resume = resume;
        this.relatedLinks = relatedLinks;
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

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", tags='" + tags + '\'' +
                ", resume='" + resume + '\'' +
                ", relatedLinks='" + relatedLinks + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
                Objects.equals(text, post.text) &&
                Objects.equals(tags, post.tags) &&
                Objects.equals(resume, post.resume) &&
                Objects.equals(relatedLinks, post.relatedLinks) &&
                Objects.equals(author, post.author) &&
                Objects.equals(date, post.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, tags, resume, relatedLinks, author, date);
    }
}
