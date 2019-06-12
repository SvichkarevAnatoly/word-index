package ru.svichkarev.anatoly.wordindex;

import java.util.Set;

/**
 * An trie data structure
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Insert a word into the trie.
     * <p>
     * This method adds a word by creating and linking the necessary trie nodes
     * into the trie. It should appropriately use existing nodes in the trie, only creating new
     * nodes when necessary. E.g. If the word "no" is already in the trie,
     * then adding the word "now" would add only one additional node
     * (for the 'w').
     *
     * @return true if the word was successfully added or false if it already exists
     * in the dictionary.
     */
    public boolean addWord(String word, int wordIndex) {
        TrieNode currentNode = this.root;
        for (char c : word.toCharArray()) {
            if (currentNode.getValidNextCharacters().contains(c)) {
                currentNode = currentNode.getChild(c);
            } else {
                currentNode = currentNode.insert(c);
            }
        }
        if (!currentNode.endsWord()) {
            currentNode.setEndsWord(wordIndex);
            return true;
        }

        return false;
    }

    /**
     * Returns indexes in text if the string is a word in the trie.
     * If there is no given word in the file, it returns null.
     *
     * @return Set of positions of search word in the file.
     */
    public Set<Integer> isWord(String word) {
        TrieNode currentNode = this.root;
        for (char c : word.toCharArray()) {
            if (currentNode.getValidNextCharacters().contains(c)) {
                currentNode = currentNode.getChild(c);
            } else {
                return null;
            }
        }

        if (!currentNode.endsWord()) {
            return null;
        }
        return currentNode.getIndexes();
    }

    // For debugging
    public void printTree() {
        printNode(root);
    }

    /**
     * Do a pre-order traversal from this node down
     */
    public void printNode(TrieNode curr) {
        if (curr == null)
            return;

        System.out.println(curr.getCharacter());

        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }
}