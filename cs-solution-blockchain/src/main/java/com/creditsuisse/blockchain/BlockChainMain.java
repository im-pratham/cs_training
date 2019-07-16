package com.creditsuisse.blockchain;

public class BlockChainMain {
    public static void main(String[] args) throws Exception {
        //EX1
        System.out.println(HashGenerator.encode("My name is Steve"));
        System.out.println(HashGenerator.encode("My name is Bob"));

        //EX2
        Block genesisBlock = new Block("Hi im the first block", null);
        Block secondBlock = new Block("Yo im the second block", genesisBlock);
        Block thirdBlock = new Block("Hey im the third block", secondBlock);

        thirdBlock.printChain();
        thirdBlock.validateChain();

        System.out.println("--------");

        secondBlock.setData("forged data");
        HashGenerator.encode(secondBlock);

        thirdBlock.printChain();
        thirdBlock.validateChain();

    }
}
