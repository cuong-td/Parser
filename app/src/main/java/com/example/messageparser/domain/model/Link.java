package com.example.messageparser.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {
    private String url;

    private String title;

    public Link(String url, String title) {
        this.url = url;
        this.title = title;
    }

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
                        "url: '" + url + "', " +
                        "title: '" + title + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link link = (Link) o;
        return url.equals(link.url) && title.equals(link.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title);
    }
}