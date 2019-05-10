package com.search;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SimpleSearch {
    public static void main(String[] args) {
	    if(args.length == 0) {
	        throw new IllegalArgumentException("No directory path given.");
        }

	    final File dirToSearch = new File(args[0]);
	    System.out.print("Indexing files...");

        File[] files = dirToSearch.listFiles();
        if (files == null) {
            System.out.println("No Files Found");
            System.exit(0);
        }

        List<FileRanker> fileRankers = new LinkedList<>();
        for (File file : files) {
            fileRankers.add(new FileRanker(file.getAbsolutePath()));
        }
        System.out.println("done");

        SearchRanker searchRanker = new SearchRanker();
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("search>");
            final String line = in.nextLine();

            if(line.equals("quit")) {
                break;
            } else {
                rankFiles(line, searchRanker, fileRankers);
                String searchRank = searchRanker.rankFileSearch(line);
                System.out.println(searchRank);
            }
        }
        System.out.println("Exiting...");
    }

    private static void rankFiles(final String line, final SearchRanker searchRanker, final List<FileRanker> fileRankers) {
        for (FileRanker ranker : fileRankers) {
            int ranking = ranker.getRanking(line);
            searchRanker.addSearchEntry(ranker.getFileName(), ranking);
        }
    }
}

