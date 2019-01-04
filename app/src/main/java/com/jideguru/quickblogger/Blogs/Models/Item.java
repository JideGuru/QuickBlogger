package com.jideguru.quickblogger.Blogs.Models;


/**
* Created by jideguru on 14/09/2018.
*/
public class Item {
    public String status;
    public String kind;
    public String description;
    public String url;
    public Posts posts;
    public String updated;
    public Pages pages;
    public Locale locale;
    public String published;
    public String id;
    public String selfLink;
    public String name ;

    public Item(String status, String kind, String description, String url, Posts posts, String updated, Pages pages, Locale locale, String published, String id, String selfLink, String name) {
        this.status = status;
        this.kind = kind;
        this.description = description;
        this.url = url;
        this.posts = posts;
        this.updated = updated;
        this.pages = pages;
        this.locale = locale;
        this.published = published;
        this.id = id;
        this.selfLink = selfLink;
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
