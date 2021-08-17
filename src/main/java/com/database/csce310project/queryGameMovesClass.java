package com.database.csce310project;

import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;

public class queryGameMovesClass
{

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

    public static ArrayList<Integer> getMoveIDs(Connection connection, int gameID) throws SQLException
    {
        Statement statement = connection.createStatement();

        ArrayList<Integer> moveIDs = new ArrayList<Integer>();
        String query = "SELECT move_id FROM moves WHERE game_id = " + String.valueOf(gameID);
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            moveIDs.add(results.getInt("move_id"));
        }
        if(moveIDs.size() == 0)
        {
            out.println("ERROR: game ID " + gameID + " has no moves");
        }

        return moveIDs;
    }

    public static void updateResults(Connection connection, int gameID, String turncolor) throws SQLException
    {
        Statement statement = connection.createStatement();

        String query = "select result_id from results order by result_id desc limit 1";
        ResultSet results = statement.executeQuery(query);

        String winnerC ="";
        int resultid;

        if(results.next()){
            resultid = results.getInt("result_id") + 1;
        }else{
            resultid=0;
        }

        //chess.turn() returns the user who got checkmated
        if (turncolor.trim().equals("w"))
            winnerC = "black";
        else if (turncolor.trim().equals("b"))
            winnerC = "white";
        else
            winnerC = "tie";


        //insert values into the database table
        String query2 = "insert into results (result_id, game_id, winner) values (" + resultid + ", " + gameID + ", '" + winnerC + "')";
        statement.executeUpdate(query2);
    }

    public static String getFenString(Connection connection, int moveID) throws SQLException
    {
        Statement statement = connection.createStatement();

        // get the fen string for the given moveID from the move table
        String fen = "";
        String query = "SELECT fen_string FROM moves WHERE move_id = " + String.valueOf(moveID);
        ResultSet results = statement.executeQuery(query);

        if(results.next())
        {
            fen = results.getString("fen_string");
        }
        else
        {
            out.println("ERROR: no FEN string for move id: " + moveID);
        }

        return fen;
    }

    public static String getCapturedPiece(Connection connection, int moveID) throws SQLException
    {
        Statement statement = connection.createStatement();

        // get the captured piece for the given moveID from the move table
        // if there is no captured piece, then return an empty string
        String capturedPiece = "";
        String query = "SELECT capture_piece FROM moves WHERE move_id = " + String.valueOf(moveID);
        ResultSet results = statement.executeQuery(query);

        if(results.next())
        {
            capturedPiece = results.getString("capture_piece");
        }

        return capturedPiece;        
    }

    public static String getMaterialAdvantage(Connection connection, int moveID) throws SQLException
    {
        Statement statement = connection.createStatement();

        // get the material advantage for the given moveID from the move table
        String materialAdvantage = "";
        String query = "SELECT mvalue FROM material WHERE move_id = " + String.valueOf(moveID);
        ResultSet results = statement.executeQuery(query);

        if(results.next())
        {
            materialAdvantage = String.valueOf(results.getInt("mvalue"));
        }

        // if white is up X material, then their material advantage should read +X
        // (black's material advantage already reads -X)
        if(materialAdvantage.length() == 1)
        {
            materialAdvantage = "+" + materialAdvantage;
        }

        return materialAdvantage;     
    }

    public static void writeToFrontEnd(int gameID, ArrayList<String> moveInfo) throws Exception
    {
        // print piece info to file
        // the file is erased and rewritten after each move
        PrintWriter writer = new PrintWriter("databaseOutput.txt");
        writer.print("For Game ID " + gameID + ":\n");

        for(int i = 0; i < moveInfo.size(); i += 4)
        {
            writer.print("\nMove " + moveInfo.get(i));
            writer.print("\nFEN string: " + moveInfo.get(i + 1));
            writer.print("\nCaptured piece: " + moveInfo.get(i + 2));
            writer.print("\nMaterial advantage: " + moveInfo.get(i + 3));

            if(i < moveInfo.size() - 4){ writer.print("\n"); }
        }
        writer.close();
    }
}
