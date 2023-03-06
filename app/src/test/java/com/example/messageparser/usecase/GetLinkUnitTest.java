package com.example.messageparser.usecase;

import static org.junit.Assert.assertEquals;

import com.example.messageparser.data.RemoteSource;
import com.example.messageparser.data.RemoteSrcImpl;
import com.example.messageparser.domain.model.Link;
import com.example.messageparser.domain.usecase.GetLinkUseCase;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GetLinkUnitTest {
    private RemoteSource remoteSource = new RemoteSrcImpl();
    private GetLinkUseCase getLink = new GetLinkUseCase(remoteSource);

    @Test
    public void getLink_None() {
        List<Link> links = getLink.execute("This is a test message without url");
        assertEquals(0, links.size());
    }

    @Test
    public void getLink_One() {
        List<Link> links = getLink.execute("Olympics 2020 is happening; https://olympics.com/tokyo-2020/en/");
        List<String> urls = links.stream()
                .map(link -> {
                    System.out.println(link.getTitle());
                    return link.getUrl();
                })
                .collect(Collectors.toList());

        assertEquals(Collections.singletonList("https://olympics.com/tokyo-2020/en"), urls);
    }

    @Test
    public void getLink_Multiple() {
        List<Link> links = getLink.execute("Check out these websites: https://www.google.com, https://www.youtube.com");
        List<String> urls = links.stream()
                .map(link -> {
                    System.out.println(link.getUrl() + " -> " + link.getTitle());
                    return link.getUrl();
                })
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("https://www.google.com", "https://www.youtube.com"), urls);
    }
}