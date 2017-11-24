package com.travelsupermarket;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecordVoteTest {
    @Test
    public void handleRequestTest() throws Exception {
        RecordVote recordVoteEndpoint = new RecordVote();
        recordVoteEndpoint.handleRequest(1, null);
    }
}