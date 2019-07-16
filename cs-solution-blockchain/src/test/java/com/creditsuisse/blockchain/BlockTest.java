package com.creditsuisse.blockchain;

import org.junit.Test;

public class BlockTest {

    @Test(expected = BlockChainException.class)
    public void testDataChangeBreaksChain() throws Exception {
        Block a = new Block("original data in a", null);
        Block b = new Block("original data in b", a);

        //tamper with data in the chain
        b.setData("hacked!");

        //should throw BlockChainException
        b.validateChain();
    }

}
