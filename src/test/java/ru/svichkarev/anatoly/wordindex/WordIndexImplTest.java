package ru.svichkarev.anatoly.wordindex;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class WordIndexImplTest {

    @Test
    public void checkTwoIndexes() throws IOException {
        final String text = "This That? This Those???";

        WordIndexImpl wordIndex = new WordIndexImpl();
        wordIndex.loadFromReader(new BufferedReader(new StringReader(text)));

        assertThat(wordIndex.getIndexes4Word("This"))
                .containsExactlyInAnyOrder(0, 11);
    }

    @Test
    public void checkCaseSensitive() throws IOException {
        final String text = "This That? This Those???";

        WordIndexImpl wordIndex = new WordIndexImpl();
        wordIndex.loadFromReader(new BufferedReader(new StringReader(text)));

        assertThat(wordIndex.getIndexes4Word("this")).isNull();
    }

    @Test
    public void checkMultilineText() throws IOException {
        final String text =
                "#Second Attacks of Syphilis.#--Instances of re-infection of syphilis\n" +
                        "have been recorded with greater frequency since the more general\n" +
                        "introduction of arsenical treatment. A remarkable feature in such cases\n" +
                        "is the shortness of the interval between the original infection and the\n" +
                        "alleged re-infection; in a recent series of twenty-eight cases, this\n" +
                        "interval was less than a year. Another feature of interest is that when\n" +
                        "patients in the tertiary stage of syphilis are inoculated with the virus\n" +
                        "from lesions from these in the primary and secondary stage lesions of\n" +
                        "the tertiary type are produced.";

        WordIndexImpl wordIndex = new WordIndexImpl();
        wordIndex.loadFromReader(new BufferedReader(new StringReader(text)));

        assertThat(wordIndex.getIndexes4Word("Second"))
                .containsExactlyInAnyOrder(1);
        assertThat(wordIndex.getIndexes4Word("have"))
                .containsExactlyInAnyOrder(69);
        assertThat(wordIndex.getIndexes4Word("in"))
                .hasSize(4);
    }

    @Test
    public void checkOnHugeText() throws IOException {
        URL url = new URL("http://www.gutenberg.org/files/74/74-0.txt");

        WordIndexImpl wordIndex = new WordIndexImpl();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        wordIndex.loadFromReader(reader);

        assertThat(wordIndex.getIndexes4Word("Tom"))
                .hasSize(713);
        assertThat(wordIndex.getIndexes4Word("Sawyer"))
                .hasSize(36);

        assertThat(wordIndex.getIndexes4Word("have"))
                .hasSize(157);
        assertThat(wordIndex.getIndexes4Word("in"))
                .hasSize(970);
    }
}