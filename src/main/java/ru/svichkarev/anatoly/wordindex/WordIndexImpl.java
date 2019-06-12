package ru.svichkarev.anatoly.wordindex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordIndexImpl implements WordIndex {

    private final static Pattern WORD_PATTERN = Pattern.compile("[\\w']+");

    private Trie trie;

    public void loadFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            loadFromReader(reader);
        } catch (IOException e) {
            System.err.println("Problem loading file: " + filename);
            e.printStackTrace();
        }
    }

    public void loadFromReader(BufferedReader reader) throws IOException {
        trie = new Trie();

        int startLineOffset = 0;
        String line = "";
        Matcher matcher = WORD_PATTERN.matcher(line);
        while ((line = reader.readLine()) != null) {
            matcher.reset(line);
            while (matcher.find()) {
                String word = matcher.group();
                final int wordOffset = startLineOffset + matcher.start();
                trie.addWord(word, wordOffset);
            }
            startLineOffset += line.length() + System.lineSeparator().length();
        }
    }

    public Set<Integer> getIndexes4Word(String searchWord) {
        return trie.isWord(searchWord);
    }
}
