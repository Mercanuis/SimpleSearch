package com.search;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Hashtable;

/**
 * FileRanker is a class that manages, and ranks a given file per the passed in phrase
 * The FileRanker attempts to use 'Dependency Injection' so that, all relevant information
 * is passed to it from initialization, to which once created, the FileRanker is ready to use
 * immediately with no further data needed to pass to it.
 */
class FileRanker {
    private File file;
    private Hashtable<String, Integer> tokenIndex;

    FileRanker(final String file) {
        this.file = new File(file);
        try {
            initializeTable();
        } catch (IOException e) {
            System.out.println("[Error] - FileRanker failed to be created with file: " + file);
        }
    }

    private void initializeTable() throws IOException {
        tokenIndex = new Hashtable<>();
        try {
            FileReader fileReader = new FileReader(file.getAbsolutePath());
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (String token : tokens) {
                    if(tokenIndex.contains(token)) {
                        Integer val = tokenIndex.get(token);
                        tokenIndex.put(token, val++);
                    } else {
                        tokenIndex.put(token, 1);
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            //TODO: Figure a way to verify this and a cleaner method than stack trace
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @return The FileRanker's associated file name
     */
    String getFileName() {
        return file.getName();
    }

    /**
     * Performs a 'Ranking' check on the file given the passed in phrase
     * Currently, the Ranking is a simple 'percentage' of the phrase that can be found in the respective file
     * But, there is a chance to change the forumla to be more of an 'relative percentage' where:
     *  Ranking = (percentage of the phrase found in the file) / The size of the file in total words/tokens
     *  TODO: Do we want to encourage this? what are the benefits to this compared to a more accurate 'ranking' of finding all the tokens?
     * @param phrase String that is being compared to
     * @return a percentage of the file's (tokens found/tokens total) ratio, in integer form,
     */
    int getRanking(String phrase) {
        String[] tokens = phrase.split(" ");

        int totalTokens = tokens.length;
        int tokensFound = 0;

        for (String token: tokens) {
            if(tokenIndex.containsKey(token)) {
                tokensFound++;
            }
        }

        int percent = (tokensFound * 100) / totalTokens;
        return Math.min(100, percent);
    }
}
