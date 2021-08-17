package com.database.csce310project;

import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class deleteGameClass {

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

    public static ArrayList<Integer> getDeletedMoveIDs(Connection connection, int gameID) throws SQLException
    {
        ArrayList<Integer> deletedMoveIDs = new ArrayList<Integer>();

        Statement statement = connection.createStatement();

        String query = "SELECT move_id FROM moves WHERE game_id = " + String.valueOf(gameID);
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            deletedMoveIDs.add(results.getInt("move_id"));
        }

        return deletedMoveIDs;
    }

    public static void deleteFromTables(Connection connection, int gameID, ArrayList<Integer> deletedMoveIDs) throws SQLException
    {
        Statement statement = connection.createStatement();
        String query = "";
        
        for(int i = 0; i < deletedMoveIDs.size(); i++)
        {
            query = "DELETE FROM material WHERE move_id = " + String.valueOf(deletedMoveIDs.get(i));
            statement.executeUpdate(query);            
        }

        query = "DELETE FROM moves WHERE game_id = " + String.valueOf(gameID);
        statement.executeUpdate(query);
        
        query = "DELETE FROM player WHERE game_id = " + String.valueOf(gameID);
        statement.executeUpdate(query);

        query = "DELETE FROM results WHERE game_id = " + String.valueOf(gameID);
        statement.executeUpdate(query);

        query = "DELETE FROM game WHERE game_id = " + String.valueOf(gameID);
        statement.executeUpdate(query);

    }

    public static void removeDeadUsers(Connection connection) throws SQLException
    {
        ArrayList<Integer> users = new ArrayList<Integer>();

        Statement statement = connection.createStatement();

        String query = "SELECT user_id FROM users";
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            users.add(results.getInt("user_id"));
        }

        for(int i = 0; i < users.size(); i++)
        {
            boolean userHasGame = false;
            query = "SELECT player_id FROM player WHERE user_id = " + users.get(i);
            results = statement.executeQuery(query);
            if(results.next()){ userHasGame = true; }

            if(!userHasGame)
            {
                query = "DELETE FROM users WHERE user_id = " + users.get(i);
                statement.executeUpdate(query);
            }
        }

    }

    // given a game number
    // delete all tuples from the game, player, moves, material, and results tables relating to that gameID (game number)
    // delete all tuples from the users table for users that are not part of any stored games after a game deletion
    // update the game_id of all affected tuples in the game table so that their game_id is the game number
    public static void deleteGame(int gameID, Connection connection)
    {
        try
        {
            ArrayList<Integer> deletedMoveIDs = getDeletedMoveIDs(connection, gameID);
            deleteFromTables(connection, gameID, deletedMoveIDs);

            // iterate through the users table and remove any users who don't have any games in the database
            removeDeadUsers(connection);
        }
        catch(SQLException e)
        {
            printSQLException(e);
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }

    public static void main(String[]args)
    {
        System.out.println("Hello");
    }
}
