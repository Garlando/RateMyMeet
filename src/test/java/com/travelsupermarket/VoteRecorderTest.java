package com.travelsupermarket;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import java.sql.SQLException;

public class VoteRecorderTest {

    VoteRecorder voteRecorder;

    @Rule
    public final EnvironmentVariables environmentVariables
            = new EnvironmentVariables();

    @Before
    public void setup() {
        //TODO : Mock DB
        try {
            environmentVariables.set("HOST", "");
            environmentVariables.set("DBNAME", "");
            environmentVariables.set("USER", "");
            environmentVariables.set("PASS", "");
            voteRecorder = new VoteRecorder();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void handleRequestTest() throws Exception {
        voteRecorder.handleRequest(1, null);
    }
}