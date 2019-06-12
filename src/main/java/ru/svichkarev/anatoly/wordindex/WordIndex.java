package ru.svichkarev.anatoly.wordindex;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public interface WordIndex {

    /**
     * Loading data from a file and building an index
     *
     * @param filename Path to the file
     */
    void loadFile(String filename);

    /**
     * Loading data from a reader and building an index
     *
     * @param reader Reader with input text
     */
    void loadFromReader(BufferedReader reader) throws IOException;

    /**
     * Get the set of positions of the word in the file.
     * If there is no given word in the file, it returns null.
     *
     * @param searchWord Word for searching in file
     * @return Set of positions of search word in the file.
     */
    Set<Integer> getIndexes4Word(String searchWord);
}
