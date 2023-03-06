package com.example.messageparser.feature.comment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.messageparser.domain.model.CommentInfo;
import com.example.messageparser.domain.usecase.AnalyzeCommentUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class CommentViewModel extends ViewModel {
    private AnalyzeCommentUseCase analyzeComment;
    private Disposable disposable;

    public LiveData<CommentInfo> commentInfo = new MutableLiveData<>();
    public LiveData<String> commentResult = new MutableLiveData<>();


    @Inject
    public CommentViewModel(AnalyzeCommentUseCase analyzeComment) {
        this.analyzeComment = analyzeComment;
    }

    public void analyzeComment(String comment) {
        disposable = Observable.fromCallable(() -> analyzeComment.execute(comment))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        info -> ((MutableLiveData<String>) commentResult).setValue(parseComment(info)),
                        throwable -> ((MutableLiveData<String>) commentResult).setValue(parseComment(CommentInfo.EMPTY))
                );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) disposable.dispose();
    }

    private String parseComment(CommentInfo info) {
        StringBuilder result = new StringBuilder("{\n");
        result.append("\t\"mentions\": [\n");
        for (int i = 0; i < info.getMentions().size(); i++) {
            result.append("\t\t\"").append(info.getMentions().get(i)).append("\"");
            if (i != info.getMentions().size() - 1)
                result.append(",");
            result.append("\n");
        }
        result.append("\t],\n");
        result.append("\t\"links\": [\n");
        for (int i = 0; i < info.getLinks().size(); i++) {
            result.append("\t\t{\n")
                    .append("\t\t\t\"url\": \"").append(info.getLinks().get(i).getUrl()).append("\",\n")
                    .append("\t\t\t\"title\": \"").append(info.getLinks().get(i).getTitle()).append("\"\n")
                    .append("\t\t}");
            if (i != info.getLinks().size() - 1)
                result.append(",");
            result.append("\n");
        }
        result.append("\t],\n");
        result.append("}");
        return result.toString();
    }
}
