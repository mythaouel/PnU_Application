package com.example.model;

import java.io.Serializable;

public class Blog implements Serializable {
    private String blogTitle;
    private String blogDetail;

    public Blog(String blogTitle, String blogDetail) {
        this.blogTitle = blogTitle;
        this.blogDetail = blogDetail;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDetail() {
        return blogDetail;
    }

    public void setBlogDetail(String blogDetail) {
        this.blogDetail = blogDetail;
    }
}
