package com.database.csce310project;

import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class editGameInfoClass {

    public static void printSQLException(SQLException ex) 
    {
        // the printSQLException() function was from the sample code that can be found on this page (in JDBCTutorialUtilities.java):
        // https://docs.oracle.com/javase/tutorial/jdbc/basics/gettingstarted.html

        for (Throwable e : ex) 
        {
            if (e instanceof SQLException) 
            {
                if (true) 
                {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException)e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();

                    while (t != null) 
                    {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }   
                }
            }
        }
    }

    public static Connection getConnection(String port, String database, String username, String password) throws Exception
    {
        //5432. csce310proj, postgres, 123456
        Connection connection = null;
        Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection("jdbc:postgresql://localhost:" + port + "/" + database, username, password);
        if(connection != null)
        {
            System.out.println("Connection established");
        }
        else
        {
            System.out.println("Connection not established");
        }     

        return connection;
    }

    public static void editPlayerName(Connection connection, int gameID, int color, String newUserName) throws SQLException
    {
        Statement statement = connection.createStatement();

        // update the player's user name
        String query = "UPDATE users SET user_name = \'" + newUserName + "\' WHERE user_id = (" + 
            "SELECT user_id FROM player " + 
            "WHERE game_id = " + String.valueOf(gameID) + " AND color = " + String.valueOf(color) + 
        ")";
        statement.executeUpdate(query);
    }

    public static void editGameName(Connection connection, int gameID, String newGameName) throws SQLException
    {
        Statement statement = connection.createStatement();

        // update the game's name
        String query = "UPDATE game SET game_name = \'" + newGameName + "\'" +
        " WHERE game_id = " + String.valueOf(gameID);
        statement.executeUpdate(query);
    }
}