package com.ransomware.blockchain;

import java.security.MessageDigest;
import java.util.Date;

public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce; // Added for Proof of Work

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    // Compute hash with nonce
    public String calculateHash() {
        try {
            String input = previousHash + timeStamp + nonce + data;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Proof of Work (Mining)
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); // Create target string (e.g., "0000")
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++; // Change nonce until hash meets difficulty
            hash = calculateHash();
        }
        System.out.println("Block Mined! -> " + hash);
    }

    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public String getData() { return data; }
}
