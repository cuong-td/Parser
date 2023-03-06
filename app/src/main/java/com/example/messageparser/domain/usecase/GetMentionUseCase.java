package com.example.messageparser.domain.usecase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.core.Observable;

public class GetMentionUseCase {
    public Observable<String> execute(String input, boolean allowHyphen, boolean allowUnderscore) {
        return Observable.create(emitter -> {
            final int len = input.length();
            for (int i = 0; i < len; i++) {
                if (input.charAt(i) != '@') continue;
                int subLen = 1;
                while (subLen + i < len) {
                    char ch = input.charAt(subLen + i);
                    if (Character.isLetterOrDigit(input.charAt(subLen + i))
                            || (subLen > 1 && allowHyphen && ch == '-')
                            || (subLen > 1 && allowUnderscore && ch == '_'))
                        subLen++;
                    else break;
                }
                if (subLen != 1) {
                    emitter.onNext(input.substring(i + 1, i + subLen));
                    i += subLen - 1;
                }
            }
            emitter.onComplete();
//            Pattern p = Pattern.compile(MENTION_REGEX);
//            Matcher m = p.matcher(input);
//            while (m.find()) {
//                String mention = m.group();
//                emitter.onNext(mention.substring(1, mention.length() - 1)); // Remove '@' and last non-word
//            }
//            emitter.onComplete();
        });
    }

    public Observable<String> execute(String input) {
        return execute(input, true, true);
    }
}
