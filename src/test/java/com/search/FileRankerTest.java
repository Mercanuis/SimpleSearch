package com.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;


public class FileRankerTest {
    private final String TEST_PATH = "C:\\Users\\brogr\\IdeaProjects\\DirectorySearch\\files\\data1.txt";

    @Test
    public void testFileRanker_ValidFile()
    {
        FileRanker test = new FileRanker(TEST_PATH);
        Assertions.assertEquals("data1.txt", test.getFileName());

        test = new FileRanker("ThisIsABadPath");
        Assertions.assertEquals("ThisIsABadPath", test.getFileName());
    }

    @Test
    public void testGetRanking()
    {
        String p1 = "This is one sort of phrase";
        String p2 = "This is another sort of phrase";
        String p3 = "This is the first one with question";
        String p4 = "This one is a stupid sentence";
        String p5 = "This one has, a little bit of, punctuation!";

        FileRanker test = new FileRanker(TEST_PATH);
        Assertions.assertEquals(16, test.getRanking(p1));
        Assertions.assertEquals(16, test.getRanking(p2));
        Assertions.assertEquals(42, test.getRanking(p3));
        Assertions.assertEquals(66, test.getRanking(p4));
        Assertions.assertEquals(12, test.getRanking(p5));
    }
}
