package com.database.csce310project;

import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class processNewGameClass {

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

    public static int getNewGameID(Connection connection) throws SQLException
    {
        Statement statement = connection.createStatement();

        int gameID = -1;
        String query = "SELECT MAX(game_id) AS max_game_id FROM game";
        ResultSet results = statement.executeQuery(query);
        if(results.next())
        {
            gameID = results.getInt("max_game_id") + 1;
        }
        else
        {
            gameID = 0;
        }

        return gameID;
    }

    public static int getNewPlayerID(Connection connection) throws SQLException
    {
        Statement statement = connection.createStatement();

        int playerID = -1;
        String query = "SELECT MAX(player_id) AS max_player_id FROM player";
        ResultSet results = statement.executeQuery(query);

        if(results.next())
        {
            playerID = results.getInt("max_player_id") + 1;
        }
        else
        {
            playerID = 0;
        }

        return playerID;
    }

    public static int[] getUserID(Connection connection, String userName) throws SQLException
    {
        int[] userIDResult = new int[2];
        userIDResult[0] = 0;

        Statement statement = connection.createStatement();

        // check if the given user name is in the users table
        // if yes, then return the corresponding user ID
        String query = "SELECT user_name, user_id FROM users";
        ResultSet results = statement.executeQuery(query);
        while(results.next())
        {
            if(userName.equals(results.getString("user_name")))
            {
                userIDResult[0] = 1;
                userIDResult[1] = results.getInt("user_id");
                return userIDResult;
            }
        }

        // if the given user name is not in the users table, return a new user ID
        query = "SELECT MAX(user_id) AS max_user_id FROM users";
        results = statement.executeQuery(query);
        if(results.next())
        {
            userIDResult[1] = results.getInt("max_user_id") + 1;
        }
        else
        {
            userIDResult[1] = 0;
        }

        return userIDResult;
    }

    public static void insertToGameTable(Connection connection, int gameID, String gameName) throws SQLException
    {
        Statement statement = connection.createStatement();

        String query = "INSERT INTO game(game_id, game_name) VALUES (" + 
            String.valueOf(gameID) + ", \'" +
            gameName + "\')"; 
        statement.executeUpdate(query);
    }

    public static void insertToPlayerTable(Connection connection, int playerID, int userID, int gameID, int color) throws SQLException
    {
        Statement statement = connection.createStatement();

        String query = "INSERT INTO player(player_id, user_id, game_id, color) VALUES (" + 
            String.valueOf(playerID) + ", " +
            String.valueOf(userID) + ", " +
            String.valueOf(gameID) + ", " +
            String.valueOf(color) + ")";
        statement.executeUpdate(query);
    }

    public static void insertToUsersTable(Connection connection, int userID, String userName) throws SQLException
    {
        Statement statement = connection.createStatement();

        String query = "INSERT INTO users(user_id, user_name) VALUES (" + 
            String.valueOf(userID) + ", \'" +
            userName + "\')"; 
        statement.executeUpdate(query);
    }

    // given the user name of two players
	// return the game ID of the new game that is created
	// insert a (game_id, game_name) tuple into the game table (game name will be [player 1 name] vs [player 2 name])
    // insert two (player_id, user_id, game_id, color) tuples into the player table
    // insert two (user_id, user_name) tuples into the users table
	// the player with firstPlayerUserName will have their color set to white, 
	// and the player with secondPlayerUserName will have their color set to black
	// this should always be the first function called when creating a new game; 
	// new values of game_id that other functions reference are created by this function
    public static Game processNewGame(String firstPlayerUserName, String secondPlayerUserName, Connection connection)
    {
        Game g = null;

        try
        {
            // we need to insert tuples into player after inserting into both users and game

            int gameID = getNewGameID(connection);
            String gameName = firstPlayerUserName + " vs " + secondPlayerUserName;
            insertToGameTable(connection, gameID, gameName);

            //populating the Game entity with the new entry
            g = new Game();
            g.setId(gameID);
            g.setName(gameName);

            //populating the user and player entity
            for(int i = 0; i < 2; i++)
            {
                String userName = "";
                if(i == 0){ userName = firstPlayerUserName; }
                if(i == 1){ userName = secondPlayerUserName; }

                // check if a user with the given user name already exists in the users table
                // if yes, then get the user ID of that user
                // if no, then get a new value for user ID
                int[] userIDResult = getUserID(connection, userName);

                // userIDResult is an array of size 2
                // userIDResult[0] = 0 if the user name does not exist in the users table
                // userIDResult[0] = 1 if the user name does exist in the users table
                int userID = userIDResult[1];
                if(userIDResult[0] == 0){ insertToUsersTable(connection, userID, userName);}

                int playerID = getNewPlayerID(connection);
                int color = i; // color = 0 if white, color = 1 if black
                insertToPlayerTable(connection, playerID, userID, gameID, color);
            }

            Statement statement = connection.createStatement();
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

            String dafaultFenn= "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
            String defaultBoard = "insert into moves (move_id, game_id,fen_string,capture_piece) values (" + moveidtemp + ", " + gameID + ", '" + dafaultFenn + "','" + "" + "' )";
            statement.executeUpdate(defaultBoard);
        }
        catch(SQLException e)
        {
            printSQLException(e);
        }
        catch(Exception e)
        {
            out.println(e);
        }

        return g;
    }
}
