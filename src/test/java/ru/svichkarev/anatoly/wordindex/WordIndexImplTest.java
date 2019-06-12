package ru.svichkarev.anatoly.wordindex;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class WordIndexImplTest {

    @Test
    public void test1() throws IOException {
        final String text = "This method adds a word by creating and linking the necessary trie nodes";

        WordIndexImpl wordIndex = new WordIndexImpl();
        wordIndex.loadFromReader(new BufferedReader(new StringReader(text)));

        assertThat(wordIndex.getIndexes4Word("This"))
                .containsExactlyInAnyOrder(0);
    }

    @Test
    public void testLoadFromReader() {
    }

    @Test
    public void testGetIndexes4Word() {
    }
}