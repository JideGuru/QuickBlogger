package com.jideguru.quickblogger.Blogs.Models;

/**
* Created by jideguru on 14/09/2018.
*/
public class Pages {
    public int totalItems ;
    public String selfLink ;

    public Pages(int totalItems, String selfLink) {
        this.totalItems = totalItems;
        this.selfLink = selfLink;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }
}
