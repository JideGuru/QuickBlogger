package com.jideguru.quickblogger.posts.models;

import java.util.List;

public class PostObject {
    public String nextPageToken;
    public List<Item> items;
    public String kind;
    public String etag;

    public PostObject(String nextPageToken, List<Item> items, String kind, String etag) {
        this.nextPageToken = nextPageToken;
        this.items = items;
        this.kind = kind;
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}