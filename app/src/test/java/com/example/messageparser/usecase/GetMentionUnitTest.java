package com.example.messageparser.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.messageparser.domain.usecase.GetMentionUseCase;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.observers.TestObserver;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GetMentionUnitTest {
    private GetMentionUseCase getMention = new GetMentionUseCase();

    @Test
    public void getMention_None() {
        List<String> mentions = getMention.execute("This is a sample with no mention");
        assertEquals(
                Collections.emptyList(),
                mentions
        );
    }

    @Test
    public void getMention_One() {
        List<String> mentions = getMention.execute("Hi @billgates, how are you?");
        assertEquals(
                Arrays.asList("billgates"),
                mentions
        );
    }

    @Test
    public void getMention_Multiple() {
        assertEquals(
                Arrays.asList("billgates", "steve", "elonmusk"),
                getMention.execute("@billgates@steve do you know where is @elonmusk?")
        );
    }
}