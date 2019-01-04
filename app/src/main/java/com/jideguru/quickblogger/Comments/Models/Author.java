package com.jideguru.quickblogger.Comments.Models;

/**
 * Created by jideguru on 14/09/2018.
 */
public class Author {
    public String id;
    public String displayName;
    public String url;
    public Image image;

    public Author(String id, String displayName, String url, Image image) {
        this.id = id;
        this.displayName = displayName;
        this.url = url;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
