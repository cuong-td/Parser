package com.example.messageparser.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class RemoteSrcImpl implements RemoteSource {
    @Override
    public String getTitle(String url) {
        String title;
        try {
            Document doc = Jsoup.connect(url).get();
            title = doc.title();
        } catch (IOException e) {
            title = "";
        }
        return title;
    }
}
