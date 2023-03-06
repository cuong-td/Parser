package com.example.messageparser.feature.comment;

import androidx.lifecycle.ViewModel;

import com.example.messageparser.domain.usecase.AnalyzeCommentUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CommentViewModel extends ViewModel {
    private AnalyzeCommentUseCase analyzeComment;

    @Inject
    public CommentViewModel(AnalyzeCommentUseCase analyzeComment) {
        this.analyzeComment = analyzeComment;
    }
}
