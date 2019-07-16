package com.creditsuisse.blockchain;

import org.junit.Test;

public class BlockTest {

    @Test(expected = BlockChainException.class)
    public void testDataChangeBreaksChain() throws Exception {
        //TODO: create a short chain and tamper with the data to ensure that the exception is thrown
    }

}
