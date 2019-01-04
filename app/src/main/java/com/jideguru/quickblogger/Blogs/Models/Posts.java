package com.jideguru.quickblogger.Blogs.Models;

/**
 * Created by jideguru on 14/09/2018.
 */



public class Posts{
    public String totalItems;
    public String selfLink;

    public Posts(String totalItems, String selfLink) {
        this.totalItems = totalItems;
        this.selfLink = selfLink;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }
}
