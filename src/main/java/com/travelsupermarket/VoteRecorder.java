package com.travelsupermarket;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.*;
import java.util.Calendar;

public class VoteRecorder implements RequestHandler<Integer, String> {

    final String HOST;
    final int PORT;
    final String DBNAME;
    final String USER;
    final String PASS;
    final Connection conn;

    public VoteRecorder() throws SQLException, ClassNotFoundException {
        this.HOST = System.getenv("HOST");
        this.PORT = 5439;
        this.DBNAME = System.getenv("DBNAME");
        this.USER = System.getenv("USER");
        this.PASS = System.getenv("PASS");

        conn = createConnection();
    }

    @Override
    public String handleRequest(Integer employeeChoice, Context context) {
        try {
            long now = Calendar.getInstance().getTime().getTime();
            String insertTableSQL = "INSERT INTO rate_my_meet"
                    + "(question_id, source_id, time_stamp, answer_id) VALUES"
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "prototype1");
            preparedStatement.setTimestamp(3, new Timestamp(now));
            preparedStatement.setInt(4, employeeChoice);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return "Vote Recorded";
    }

    private Connection createConnection() throws SQLException, ClassNotFoundException {
        System.out.println(this.HOST);
        String dbURL = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DBNAME);

        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(dbURL, USER, PASS);
    }

}