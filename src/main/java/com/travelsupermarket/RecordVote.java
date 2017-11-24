package com.travelsupermarket;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.util.Calendar;

public class RecordVote implements RequestHandler<Integer, String>{

//    instance id : ratemymeetdb
//    db name : ratemymeet
//    port : 5439

    //final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String DB_URL = "";
    final static String USER = "";
    final static String PASS = "";

    @Override
    public String handleRequest(Integer employeeChoice, Context context) {
//        Integer employeeChoice = input;
        try {
            Connection connection = createConnection();
            long now = Calendar.getInstance().getTime().getTime();
            String insertTableSQL = "INSERT INTO rate_my_meet"
                    + "(question_id, source_id, time_stamp, answer_id) VALUES"
                    + "(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "mkyong");
            preparedStatement.setTimestamp(3, new Timestamp(now));
            preparedStatement.setInt(4, employeeChoice);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            connection.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        }

        return "Vote Recorded";
    }

    private Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public static void main(String[] args) {
        RecordVote recordVoteEndpoint = new RecordVote();
        recordVoteEndpoint.handleRequest(1, null);
    }
}