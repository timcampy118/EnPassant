package com.database.csce310project;

import static java.lang.System.out;
import java.sql.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class processMoveClass 
{
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

    public static String reformatCapturedPiece(String capturedPiece)
    {
        capturedPiece = capturedPiece.trim();
        if (capturedPiece.length() == 0)
            return capturedPiece;

        char capturedPieceChar = capturedPiece.charAt(0);
        String color = "";

        if ((int)capturedPieceChar < 97)
        {
            color = "w";
        }
        else
        {
            color = "b";
        }
        
        return color + capturedPiece.toUpperCase();
    }

    public static String simplifyFenString(String fen)
    {
        fen = fen.replace("/", "");
        for(int i = 0; i < 9; i++)
        {
            fen = fen.replace(String.valueOf(i), "");
        }
        return fen;
    }

//-----------------------------------------------------------------

    public static String getCapturedPiece(String currentFen, String previousFen) throws SQLException
    {
        // remove '/' and numbers from the FENs so that only the piece letters remain
        previousFen = simplifyFenString(previousFen);
        currentFen = simplifyFenString(currentFen);

        // check if a piece was captured
        // a piece is only captured if the previous FEN has more piece characters than the current FEN

        // if a piece was not captured
        if(previousFen.length() <= currentFen.length()){ return ""; }

        // if a piece was captured
        else if(previousFen.length() == (currentFen.length() + 1))
        {
            // sort the two strings
            char previousFenCharArray[] = previousFen.toCharArray();
            Arrays.sort(previousFenCharArray);

            char currentFenCharArray[] = currentFen.toCharArray();
            Arrays.sort(currentFenCharArray);

            previousFen = String.valueOf(previousFenCharArray);
            currentFen = String.valueOf(currentFenCharArray);

            // compare currentFen to previousFen and store unique characters for each array
            // (this could definitely be rewritten to make more sense)
            ArrayList<String> previousFenOnlyArrayList = new ArrayList<String>();
            for(int i = 0; i < previousFen.length(); i++)
            {
                previousFenOnlyArrayList.add(previousFen.substring(i, i + 1));
            }

            String remainingCurrentFen = currentFen;
            String currentFenOnly = "";
            //int currentFenOnlyCount = 0;
            while(remainingCurrentFen.length() > 0)
            {
                String letter = remainingCurrentFen.substring(0, 1);

                int previousFenLetterIndex = previousFenOnlyArrayList.indexOf(letter);

                if(previousFenLetterIndex != -1)
                {
                    previousFenOnlyArrayList.remove(previousFenLetterIndex);
                }
                else
                {
                    currentFenOnly += letter;
                }

                remainingCurrentFen = remainingCurrentFen.substring(1);
            }

            String previousFenOnly = "";
            for(int i = 0; i < previousFenOnlyArrayList.size(); i++)
            {
                previousFenOnly += previousFenOnlyArrayList.get(i);
            }
            
            // eliminate differences between previousFen and currentFen caused by promotions
            // (this is O(n^2) but n is never greater than 3)
            String combinedUnique = previousFenOnly + currentFenOnly;

            for(int i = 0; i < combinedUnique.length(); i++)
            {
                for(int j = i + 1; j < combinedUnique.length(); j++)
                {
                    char charAtI = combinedUnique.charAt(i);
                    char charAtJ = combinedUnique.charAt(j);

                    // if combinedUnique characters at index i and j are both uppercase (i.e. white)
                    if(((int)charAtI < 97 && (int)charAtJ < 97)

                    // if combinedUnique characters at index i and j are both lowercase (i.e. black)
                    || ((int)charAtI >= 97 && (int)charAtJ >= 97))
                    {
                        combinedUnique = combinedUnique.replace(String.valueOf(charAtI), "");
                        combinedUnique = combinedUnique.replace(String.valueOf(charAtJ), "");
                    }
                }
            }

            // after eliminating differences from promotions, the only remaining character in combinedUnique
            // will be the captured piece
            return combinedUnique;
        }
        else
        {
            out.println("ERROR: invalid length of previousFen or currentFen");
            return "";
        }

    }
}
