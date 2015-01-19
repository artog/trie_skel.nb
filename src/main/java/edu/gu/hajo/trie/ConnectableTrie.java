package edu.gu.hajo.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * A Trie implementation
 *
 */
public class ConnectableTrie implements IConnectableTrie {

    public final TrieNode root = new TrieNode(TrieNode.ROOT,null);
    
    private ConnectableTrie() {
    }

    // Factory method
    public static IConnectableTrie newInstance() {
        return new ConnectableTrie();
    }

    @Override
    public TrieNode insert(String key) {
        TrieNode pos = root;
        for (char c : key.toCharArray()) {
            pos = pos.addChild(new TrieNode(c,pos));
        }
        pos.end = true;
        return pos;
    }

    @Override
    public boolean contains(String key) {
        
        if (key.length() == 0) return false;
        
        TrieNode pos = root;
        for (char c : key.toCharArray()) {
            TrieNode tmp = pos.getChild(c);
            if (tmp != null ) {
                // Char found, check those child in next iteration
                pos = tmp;
            } else {
                // Char not found among childs
                return false;
            }
        }
        return pos.end;
    }

    @Override
    public List<String> getValues(String key) {
        List<String> values = new ArrayList<>();
        
        if (contains(key)) {
            
            TrieNode pos = root;
            for (char c : key.toCharArray()) {
                TrieNode tmp = pos.getChild(c);
                if (tmp != null ) {
                    // Char found, check those child in next iteration
                    pos = tmp;
                } else {
                    // Char not found among childs
                    return values;
                }
            }
            for (TrieNode peer : pos.peers) {
                values.add(peer.getWord());
            }
        }
        
        return values;
    }

    @Override
    public List<String> getKeys(String prefix) {
        TrieNode pos = root;
        List<String> keys = new ArrayList<>();
        
        for (char c : prefix.toCharArray()) {
            TrieNode tmp = pos.getChild(c);
            if (tmp != null ) {
                pos = tmp;
            } else {
                // Key not found, return empty list
                return keys;
            }
        }
        
        pos.getChains(keys, new StringBuilder(prefix));
        
        
        return keys;
    }

}
