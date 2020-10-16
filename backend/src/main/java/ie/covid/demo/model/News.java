package ie.covid.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News {
    private String id;
    private String title;
    private String description;
    private String url;
    private String author;
    private String image;

    public News() {

    }

    public News(String id, String title, String description, String url, String author, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.author = author;
        this.image = image;
    }

    public String getId() {
        return id;
    }
    @JsonSetter("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    @JsonSetter("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }
    @JsonSetter("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }
    @JsonSetter("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }
    @JsonSetter("image")
    public void setImage(String image) {
        this.image = image;
    }
}
