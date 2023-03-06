package com.example.messageparser.domain.model;

import java.io.Serializable;

public class Link implements Serializable {

    private String url;

    private String title;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return
                "{" +
                        "url: '" + url + '\'' +
                        ",title: '" + title + '\'' +
                        "}";
    }
}