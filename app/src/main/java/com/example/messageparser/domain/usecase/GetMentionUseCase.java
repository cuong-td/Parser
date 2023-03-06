package com.example.messageparser.domain.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMentionUseCase {
    private static final String MENTION_REGEX = "@\\w+";

    public List<String> execute(String input/*, boolean allowHyphen, boolean allowUnderscore*/) {
        List<String> mentions = new ArrayList<>();
        Pattern p = Pattern.compile(MENTION_REGEX);
        Matcher m = p.matcher(input);
        while (m.find()) {
            String mention = m.group();
            mentions.add(mention.substring(1)); // Remove '@'
        }
        return mentions;
    }
}
