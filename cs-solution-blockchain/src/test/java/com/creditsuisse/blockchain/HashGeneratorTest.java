package com.creditsuisse.blockchain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HashGeneratorTest {

    @Test
    public void checkSameHashReturnedForSameInput() {
        String hash1 = HashGenerator.encode("My name is Fred");
        String hash2 = HashGenerator.encode("My name is Fred");
        assertEquals(hash1, hash2);
    }

    @Test
    public void checkDifferentHashReturnedForDifferentInput() {
        String hash1 = HashGenerator.encode("My name is Fred");
        String hash2 = HashGenerator.encode("My name is Bob");
        assertNotEquals(hash1, hash2);
    }

}
