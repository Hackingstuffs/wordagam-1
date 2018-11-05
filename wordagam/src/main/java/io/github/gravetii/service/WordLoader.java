package io.github.gravetii.service;

import io.github.gravetii.scheduler.TaskScheduler;
import io.github.gravetii.util.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class WordLoader {

    private static final Logger logger = Logger.getLogger(WordLoader.class.getCanonicalName());

    private static final String WORDS_FILE = "words.txt";

    private static volatile WordLoader INSTANCE;

    private Trie trie;

    public static WordLoader get() {
        if (INSTANCE == null) {
            synchronized (WordLoader.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WordLoader();
                    logger.info("Created instance of WordLoader.");
                }
            }
        }

        return INSTANCE;
    }

    private WordLoader() {
        this.trie = new Trie();
        TaskScheduler.get().submit(new WordLoaderTask());
    }

    public boolean search(String word) {
        return trie.search(word);
    }

    public boolean prefix(String word) {
        return trie.prefix(word);
    }

    private class WordLoaderTask implements Runnable {

        @Override
        public void run() {
            try {
                InputStream istream = ClassLoader.getSystemResourceAsStream(WORDS_FILE);
                BufferedReader reader = new BufferedReader(new InputStreamReader(istream));
                String word;
                while ((word = reader.readLine()) != null) {
                    word = word.trim();
                    trie.insert(word);
                }
            }
            catch (IOException e) {
                System.exit(1);
            }
        }
    }
}