package com.jideguru.quickblogger.Posts.Models;

/**
 * Created by jideguru on 14/09/2018.
 */
public class Author {
    public String url;
    public Image image;
    public String displayName;
    public String id;

    public Author(String url, Image image, String displayName, String id) {
        this.url = url;
        this.image = image;
        this.displayName = displayName;
        this.id = id;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
