package com.jideguru.quickblogger.Posts.Models;

/**
 * Created by jideguru on 14/09/2018.
 */
public class Blog
{
    public String id;

    public Blog(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
