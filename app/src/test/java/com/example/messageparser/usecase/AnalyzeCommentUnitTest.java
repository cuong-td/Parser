package com.example.messageparser.usecase;

import static org.junit.Assert.assertEquals;

import com.example.messageparser.data.RemoteSrcImpl;
import com.example.messageparser.domain.model.CommentInfo;
import com.example.messageparser.domain.model.Link;
import com.example.messageparser.domain.usecase.AnalyzeCommentUseCase;
import com.example.messageparser.domain.usecase.GetLinkUseCase;
import com.example.messageparser.domain.usecase.GetMentionUseCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AnalyzeCommentUnitTest {
    private AnalyzeCommentUseCase analyzeComment;

    @Before
    public void setUp() throws Exception {
        GetMentionUseCase getMention = new GetMentionUseCase();
        GetLinkUseCase getLink = new GetLinkUseCase(new RemoteSrcImpl());
        analyzeComment = new AnalyzeCommentUseCase(getMention, getLink);
    }

    @Test
    public void testExecute_NoMentionsOrLinks() {
        String comment = "This is a comment with no mentions or links.";
        CommentInfo commentInfo = analyzeComment.execute(comment);
        assertEquals(0, commentInfo.getLinks().size());
        assertEquals(0, commentInfo.getMentions().size());
    }

    @Test
    public void testExecute_MentionsAndLinks() {
        String mention1 = "@jane";
        String mention2 = "@john";
        String url1 = "https://www.google.com";
        String title1 = "Google";
        String url2 = "https://www.youtube.com";
        String title2 = "YouTube";
        String commentText = mention1 + " and " + mention2 + ", please check these awesome websites: " + url1 + ", " + url2;
        CommentInfo commentInfo = analyzeComment.execute(commentText);
        assertEquals(
                Arrays.asList(mention1.substring(1), mention2.substring(1)),
                commentInfo.getMentions()
        );
        assertEquals(
                Arrays.asList(url1, url2),
                commentInfo.getLinks().stream().map(Link::getUrl).collect(Collectors.toList())
        );
        assertEquals(
                Arrays.asList(title1, title2),
                commentInfo.getLinks().stream().map(Link::getTitle).collect(Collectors.toList())
        );
    }
}