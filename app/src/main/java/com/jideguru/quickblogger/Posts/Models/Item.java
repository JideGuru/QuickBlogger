package com.jideguru.quickblogger.Posts.Models;


import java.util.List;

/**
 * Created by jideguru on 14/09/2018.
 */
public class Item {
    public String content;
    public String kind;
    public List<String> labels;
    public String title;
    public String url;
    public Author author;
    public String updated;
    public Replies replies;
    public Blog blog;
    public String etag;
    public String published;
    public String id;
    public String selfLink;

    public Item(String content, String kind, List<String> labels, String title, String url, Author author, String updated, Replies replies, Blog blog, String etag, String published, String id, String selfLink) {
        this.content = content;
        this.kind = kind;
        this.labels = labels;
        this.title = title;
        this.url = url;
        this.author = author;
        this.updated = updated;
        this.replies = replies;
        this.blog = blog;
        this.etag = etag;
        this.published = published;
        this.id = id;
        this.selfLink = selfLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Replies getReplies() {
        return replies;
    }

    public void setReplies(Replies replies) {
        this.replies = replies;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
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

}
