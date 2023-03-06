package com.example.messageparser.domain.usecase;

import com.example.messageparser.data.RemoteSource;
import com.example.messageparser.domain.model.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class GetLinkUseCase {
    private static final String URL_REGEX = "\\bhttps?://\\S+\\b";

    // This should be a repository
    private RemoteSource remoteSource;

    @Inject
    public GetLinkUseCase(RemoteSource source) {
        this.remoteSource = source;
    }

    public List<Link> execute(String input) {
        List<Link> links = new ArrayList<>();
        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(input);
        while (m.find()) {
            String url = m.group();
            String title = remoteSource.getTitle(url);
            links.add(new Link(url, title));
        }
        return links;
    }
}
