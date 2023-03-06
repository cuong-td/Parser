package com.example.messageparser.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.messageparser.domain.usecase.GetMentionUseCase;

import org.junit.Test;

import java.util.Arrays;

import io.reactivex.rxjava3.observers.TestObserver;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GetMentionUnitTest {
    private GetMentionUseCase getMention = new GetMentionUseCase();
    private TestObserver<String> observer = new TestObserver<>();

    @Test
    public void getMention_happyCaseWithUnderscore() {
        getMention.execute("@billgates do you know where is @elon_musk?")
                .subscribe(observer);
        observer.assertComplete();
        observer.assertNoErrors();
        assertEquals(
                Arrays.asList("billgates", "elon_musk"),
                observer.values()
        );
    }

    @Test
    public void getMention_adjacentAndHyphen() {
        getMention.execute("@billgates@clintons do you know where is @elon-musk?")
                .subscribe(observer);
        observer.assertComplete();
        observer.assertNoErrors();
        assertEquals(
                Arrays.asList("billgates", "clintons", "elon-musk"),
                observer.values()
        );
//        assertEquals(
//                Arrays.asList("billgates", "elonmusk"),
//                getMention.execute("@billgates do you know where is @elonmusk@?")
//        );
    }
}