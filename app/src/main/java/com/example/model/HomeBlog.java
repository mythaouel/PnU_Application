package com.example.model;

import java.io.Serializable;

public class HomeBlog implements Serializable {
    private int blogThumb;
    private String blogName;
    private String blogContent;
    private int blogBanner;

    public HomeBlog(int blogThumb, String blogName, String blogContent, int blogBanner) {
        this.blogThumb = blogThumb;
        this.blogName = blogName;
        this.blogContent = blogContent;
        this.blogBanner = blogBanner;
    }

    public int getBlogThumb() {
        return blogThumb;
    }

    public void setBlogThumb(int blogThumb) {
        this.blogThumb = blogThumb;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public int getBlogBanner() {
        return blogBanner;
    }

    public void setBlogBanner(int blogBanner) {
        this.blogBanner = blogBanner;
    }
}
