package edu.gu.hajo.trie;

import java.util.ArrayList;
import java.util.List;

/*
 *  Nodes in Trie
 *  Note: Package private class
 */
public class TrieNode {

    // Used to mark the root node
    public static final char ROOT = '§';

    public final TrieNode parent;
    
    public List<TrieNode> children = new ArrayList<>();
    public List<TrieNode> peers = new ArrayList<>();
    
    public boolean end = false;
    
    public final char value;
    
    public TrieNode(char c,TrieNode parent) {
        this.value =  c;
        this.parent = parent;
        
    }
    
    // Connect one node in this Trie with node in other Trie
    public void addPeer(TrieNode peer) {
        for (TrieNode p : peers) {
            if (peer == p) {
                return;
            }
        }
        
        peers.add(peer);
        peer.addPeer(this);
    }
    
    public TrieNode addChild(TrieNode child) {
        for (TrieNode c : children) {
            if (c.value == child.value) {
                return c;
            }
        }
        children.add(child);
        return child;
    }
    
    public TrieNode getChild(TrieNode child) {
        for (TrieNode c : children) {
            if (c.value == child.value) {
                return c;
            }
        }
        return null;
    }
    
    public TrieNode getChild(char c) {
        return getChild(
                new TrieNode(c,this)
        );
    }
    
    
    public void getChains(List<String> chains, StringBuilder chainStart) {
        chainStart.append(this.value);
        
        if (this.end) {
            chains.add(chainStart.toString());
        }
        
        for (TrieNode child : children) {
            child.getChains(chains, chainStart);
        }
    }
    
    public String getWord() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
            
        if (parent == null) {
            return "";
        }
        return sb.insert(0,parent.getWord()).toString();
    }
    // Possible good for debug
    @Override
    public String toString() {
        return "Replace with better data";  // TODO 
    }

}
