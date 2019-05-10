package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;

public class SearchRankerTest {
    @InjectMocks
    SearchRanker underTest = new SearchRanker();

    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRankFileSearch()
    {
        underTest.addSearchEntry("data1.txt", 90);
        underTest.addSearchEntry("data2.txt", 45);
        underTest.addSearchEntry("data3.txt", 45);
        underTest.addSearchEntry("data4.txt", 100);

        String expected = "data4.txt: 100%\ndata1.txt: 90%\ndata3.txt: 45%\ndata2.txt: 45%\n";
        String result = underTest.rankFileSearch("To be or not to be");

        Assertions.assertEquals(expected, result);
    }
}
