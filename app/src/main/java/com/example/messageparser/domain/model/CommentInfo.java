package com.example.messageparser.domain.model;

import java.util.Collections;
import java.util.List;

public class CommentInfo {
    public static final CommentInfo EMPTY = new CommentInfo(Collections.emptyList(), Collections.emptyList());
    private List<String> mentions;
    private List<Link> links;

    public CommentInfo(List<String> mentions, List<Link> links) {
        this.mentions = mentions;
        this.links = links;
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
