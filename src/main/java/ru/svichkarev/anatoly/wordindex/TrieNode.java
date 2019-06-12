package ru.svichkarev.anatoly.wordindex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.singleton;

/**
 * Represents a node in a Trie
 */
class TrieNode {

    private HashMap<Character, TrieNode> children;
    private Set<Integer> indexes;
    private boolean isWord;

    /**
     * Create a new TrieNode
     */
    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    /**
     * Return the TrieNode that is the child when you follow the
     * link from the given Character
     *
     * @param c The next character in the key
     * @return The TrieNode that character links to, or null if that link
     * is not in the trie.
     */
    public TrieNode getChild(Character c) {
        return children.get(c);
    }

    /**
     * Inserts this character at this node.
     * Returns the newly created node, if c wasn't already
     * in the trie.  If it was, it does not modify the trie
     * and returns null.
     *
     * @param c The character that will link to the new node
     * @return The newly created TrieNode, or null if the node is already
     * in the trie.
     */
    public TrieNode insert(Character c) {
        TrieNode next = new TrieNode();
        children.put(c, next);
        return next;
    }

    public Set<Integer> getIndexes() {
        return indexes;
    }

    /**
     * Set this node ends a word in the trie and add index in the text.
     *
     * @param wordIndex Index of word
     */
    public void setEndsWord(int wordIndex) {
        isWord = true;
        indexes = new HashSet<>(singleton(wordIndex));
    }

    /**
     * Add word index to the node.
     *
     * @param wordIndex Index of word
     */
    public void addWordIndex(int wordIndex) {
        indexes.add(wordIndex);
    }

    /**
     * Return whether or not this node ends a word in the trie.
     */
    public boolean endsWord() {
        return isWord;
    }

    /**
     * Return the set of characters that have links from this node
     */
    public Set<Character> getNextCharacters() {
        return children.keySet();
    }
}

