package com.example.messageparser.domain.usecase;

import com.example.messageparser.domain.model.CommentInfo;

import javax.inject.Inject;

public class AnalyzeCommentUseCase {
    private GetMentionUseCase getMention;
    private GetLinkUseCase getLink;

    @Inject
    public AnalyzeCommentUseCase(GetMentionUseCase getMention, GetLinkUseCase getLink) {
        this.getMention = getMention;
        this.getLink = getLink;
    }

    public CommentInfo execute(String comment) {
        return new CommentInfo(getMention.execute(comment), getLink.execute(comment));
    }
}
