package com.ransomware.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private int difficulty; // Mining difficulty

    public Blockchain(int difficulty) {
        this.difficulty = difficulty;
        chain = new ArrayList<>();
        // Genesis block (first block)
        Block genesisBlock = new Block("Genesis Block", "0");
        genesisBlock.mineBlock(difficulty); // Mine first block
        chain.add(genesisBlock);
    }

    public void addBlock(String data) {
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(data, previousBlock.getHash());
        newBlock.mineBlock(difficulty); // Mining required before adding
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Block " + i + " has been tampered!");
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("Block " + i + " previous hash mismatch!");
                return false;
            }
        }
        return true;
    }

    // public void printBlockchain() {
    //     for (Block block : chain) {
    //         System.out.println("Block:");
    //         System.out.println("Data: " + block.getData());
    //         System.out.println("Hash: " + block.getHash());
    //         System.out.println("Previous Hash: " + block.getPreviousHash());
    //         System.out.println("----------------------");
    //     }
    // }
}
