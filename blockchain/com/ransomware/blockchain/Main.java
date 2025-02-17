package com.ransomware.blockchain;

public class Main {
    public static void main(String[] args) {
        int difficulty = 4; // Adjust mining difficulty
        Blockchain blockchain = new Blockchain(difficulty);

        System.out.println("Mining block 1...");
        blockchain.addBlock("File 1: Secure document.pdf");

        System.out.println("Mining block 2...");
        blockchain.addBlock("File 2: Confidential report.docx");

        System.out.println("\nBlockchain Valid: " + blockchain.isChainValid());
        System.out.println("\nPrinting Blockchain:");
        // blockchain.printBlockchain();
    }
}
