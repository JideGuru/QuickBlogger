package com.jideguru.quickblogger.pages.models;

import java.util.List;

public class PageObject {
    public List<Item> items;
    public String kind;
    public String etag;

    public PageObject(List<Item> items, String kind, String etag) {
        this.items = items;
        this.kind = kind;
        this.etag = etag;
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
