package com.creditsuisse.blockchain;

public class Block {

    //blockchain encryption fields
    private final Block previous;
    private String hash;
    private long nonce;

    //data field(s)
    private String data;

    public Block(String data, Block previous) {
        this.data = data;
        this.previous = previous;

        //TODO: uncomment this line to enable block encoding
        //HashGenerator.encode(this);
    }

    public void printChain() {
        if (previous != null){
            previous.printChain();
        } else {
            System.out.println("Chain starting from genesis node:");
        }
        System.out.printf("Block {nonce: %s} {hash: %s} {data: %s} %n", nonce, hash, data);
    }

    //
    // Getters and Setters
    //

    public Block getPrevious() {
        return previous;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }
}
