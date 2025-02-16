package com.ransomware.blockchain;

import java.util.ArrayList;
import java.util.List;
public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(new Block("Genesis Block", "0"));
    }

    public void addBlock(String data) {
        Block previousBlock = chain.get(chain.size()-1);
        Block newBlock = new Block(data, previousBlock.getHash());
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false; // Data tampered
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false; // Chain broken
            }
        }
        return true;
    }
}
