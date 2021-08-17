package com.database.csce310project;

import java.sql.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;

public class queryGamesClass
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

    //connect to database
    public static Connection getConnection() throws Exception
    {
        Connection connection = null;
        Class.forName("org.postgresql.Driver");

        //Local Host Credentials
        String port = "5432";
        String database = "chess310EP";
        String username = "postgres";
        String password = "123456";
        String dburl = "jdbc:postgresql://localhost:";

        connection = DriverManager.getConnection(dburl + port + "/" + database, username, password);
        if(connection != null)
        {
            System.out.println("***Connection established");
        }
        else
        {
            System.out.println("***Connection not established");
        }

        return connection;
    }

    public static ArrayList<Game> queryGame(Connection connection) throws SQLException
    {
        ArrayList<Game> gameIDs = new ArrayList<Game>();

        //Query up the game data to be displayed
        Statement statement = connection.createStatement();
        String query = "SELECT game_id, game_name FROM game ORDER BY game_id";
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            Game g = new Game();
            g.setId(results.getInt("game_id"));
            g.setName(results.getString("game_name"));

            gameIDs.add(g);
        }

        return gameIDs;
    }

    public static ArrayList<LoadGame> queryGameMoves(Connection connection, int currentGameId) throws SQLException
    {
        ArrayList<LoadGame> gamemoves = new ArrayList<LoadGame>();

        //Load up stored Moves to be viewed in Load.jsp
        Statement statement = connection.createStatement();
        String query = "SELECT fen_string, capture_piece FROM moves where game_id = " + currentGameId + " ORDER BY move_id asc ";
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            LoadGame lg = new LoadGame();
            lg.setFenValue(results.getString("fen_string"));
            lg.setCapturePiece(results.getString("capture_piece"));

            gamemoves.add(lg);
        }

        return gamemoves;
    }

    public static ArrayList<Game> searchGameText(Connection connection, String searchInput) throws SQLException
    {
        ArrayList<Game> gameIDs = new ArrayList<Game>();

        //Search for Game Name and Player Name based on User Input
        Statement statement = connection.createStatement();
        String query = "select g.game_id, g.game_name from game g natural join player p natural join users u where lower(u.user_name) LIKE \'%" + searchInput.toLowerCase() + "%\' or lower(g.game_name) LIKE \'%" + searchInput.toLowerCase() + "%\' GROUP by game_id";
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            Game g = new Game();
            g.setId(results.getInt("game_id"));
            g.setName(results.getString("game_name"));

            gameIDs.add(g);
        }

        return gameIDs;
    }

    //get game name based on the game id
    public static String getGameIDNames(Connection connection, int gameid) throws SQLException
    {
        ArrayList<Game> gameNames = new ArrayList<Game>();

        Statement statement = connection.createStatement();

        String query = "SELECT game_name FROM game WHERE game_id = " + gameid;
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            return results.getString("game_name");
        }

        return null;
    }

    public static void storemoveDB(Connection connection, int gameid, String newfen, String capturePieces) throws SQLException
    {
        Statement statement = connection.createStatement();

        //check for move id
        int moveidtemp;
        String moveidcheck = "SELECT MAX(move_id) AS max_move_id FROM moves";
        ResultSet results = statement.executeQuery(moveidcheck);

        //initialize the moveidtemp variable
        if(results.next())
        {
            moveidtemp = results.getInt("max_move_id")+1;
        }
        else
        {
            moveidtemp = 0;
        }

        //Store Chess moves onto Database
        String insertmove = "insert into moves (move_id, game_id,fen_string,capture_piece) values (" + moveidtemp + ", " + gameid + ", '" + newfen + "','" + capturePieces + "' )";
        statement.executeUpdate(insertmove);
    }

    public static void UpdateMatAdv(Connection connection, int gameid, String ColorCapture) throws SQLException
    {
        Statement statement = connection.createStatement();

        //init variables
        int moveidtemp;
        int matidtemp;
        int matval;

        //checks for the most recent move in the given game
        String moveid = "SELECT MAX(move_id) AS max_move_id FROM moves WHERE game_id = " + gameid + ";";
        ResultSet results = statement.executeQuery(moveid);

        if(results.next()){
            moveidtemp = results.getInt("max_move_id");
        }else{
            moveidtemp = 0;
        }

        //Checks for the previous value of the previous move if exist. else 0
        String mval = "SELECT mvalue AS materialV FROM material WHERE move_id =" + (moveidtemp - 1);
        ResultSet mvalfind = statement.executeQuery(mval);


        if(mvalfind.next())
        {
            matval = Integer.parseInt(mvalfind.getString("materialV"));
        }
        else
        {
            matval = 0;
        }

        System.out.println("mvalue before case= " + matval);
        System.out.println(ColorCapture);

        //updates material advantage value
        if(ColorCapture == "wB"){
            matval -=3;
        }else if (ColorCapture.trim().equals("wP")){
            matval -=1;
        }else if (ColorCapture.trim().equals("wR")){
            matval -=5;
        }else if (ColorCapture.trim().equals("wK")){
            matval -=0;
        }else if (ColorCapture.trim().equals("wQ")){
            matval -=9;
        }else if (ColorCapture.trim().equals("wN")){
            matval -=3;
        }else if (ColorCapture.trim().equals("bB")){
            matval +=3;
        }else if (ColorCapture.trim().equals("bP")){
            matval +=1;
        }else if (ColorCapture.trim().equals("bR")){
            matval +=5;
        }else if (ColorCapture.trim().equals("bK")){
            matval +=0;
        }else if (ColorCapture.trim().equals("bQ")){
            matval +=9;
        }else if (ColorCapture.trim().equals("bN")){
            matval +=3;
        }
        System.out.println("mvalue after case= " + matval);

        //looking for a matid
        String matid = "SELECT MAX(mat_id) AS max_mat_id FROM material;";
        ResultSet results2 = statement.executeQuery(matid);

        //initialize the matidtemp variable
        if(results2.next())
        {
            matidtemp = results2.getInt("max_mat_id")+1;
        }
        else
        {
            matidtemp = 0;
        }

        //Store Chess moves onto Database
        String insertmaterial = "insert into material (mat_id, move_id, mvalue) values (" + matidtemp + ", " + moveidtemp + ", '" + matval + "' )";
        statement.executeUpdate(insertmaterial);
    }

    public static ArrayList<String> getPlayerName(Connection connection, int gameid) throws SQLException
    {
        ArrayList<String> playerNames = new ArrayList<String>();

        // Gets a User's Name and Color
        Statement statement = connection.createStatement();
        String query = "select user_name,color from users inner join player on player.user_id = users.user_id where game_id = " + gameid + " order by color asc ";
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            playerNames.add(results.getString("user_name"));
        }

        return playerNames;
    }

    public static void saveGameEdits(Connection connection, int gameid, String gamenameedit) throws SQLException
    {
        try{
            ArrayList<Game> gameNames = new ArrayList<Game>();
            Statement statement = connection.createStatement();

            //Saves Game Name Changes based on User Input
            String query = "UPDATE game SET game_name = '" + gamenameedit + "' WHERE game_id =" + gameid;
            statement.executeUpdate(query);
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

        public static ArrayList<Game> getReplayNextMove(Connection connection, int curGameID, int curMoveID) throws SQLException
    {
        ArrayList<Game> NextFen = new ArrayList<Game>();
        Statement statement = connection.createStatement();

        //Move Forward through the Move Table with the associated Game ID
        String query = "SELECT fen_string FROM moves WHERE game_id = " + curGameID + " AND move_id = (" + curMoveID + "+1)";
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            Game g = new Game();
            g.setFen(results.getString("fen_string"));

            NextFen.add(g);
        }

        return NextFen;
    }

    public static ArrayList<Game> getReplayPrevMove(Connection connection, int curGameID, int curMoveID) throws SQLException
    {
        ArrayList<Game> NextFen = new ArrayList<Game>();
        Statement statement = connection.createStatement();

        //Move Backwards through the Move Table with the associated Game ID
        String query = "SELECT fen_string FROM moves WHERE game_id = " + curGameID + " AND move_id = (" + curMoveID + "-1)";
        ResultSet results = statement.executeQuery(query);

        while(results.next())
        {
            Game g = new Game();
            g.setFen(results.getString("fen_string"));

            NextFen.add(g);
        }

        return NextFen;
    }
}
