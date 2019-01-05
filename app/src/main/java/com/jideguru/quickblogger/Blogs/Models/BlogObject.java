package com.jideguru.quickblogger.Blogs.Models;

import java.util.List;

public class BlogObject {
    public List<Item> items;
    public String kind;

    public BlogObject(List<Item> items, String kind) {
        this.items = items;
        this.kind = kind;
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
}

