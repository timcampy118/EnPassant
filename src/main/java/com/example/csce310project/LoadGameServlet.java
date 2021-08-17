package com.example.csce310project;

import com.database.csce310project.Game;
import com.database.csce310project.LoadGame;
import com.database.csce310project.queryGamesClass;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "LoadGameServlet", urlPatterns = "/LoadGame")
public class LoadGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoadGame - doGet");

        Connection gconnect = null;

        try
        {
            //connect to database
            gconnect = queryGamesClass.getConnection();

            //gets the game id from the JSP
            int gameIdnum = Integer.parseInt(request.getParameter("id"));

            //Query up the moves associated with the Game Id
            ArrayList<LoadGame> temp = queryGamesClass.queryGameMoves(gconnect, gameIdnum);

            //redirect to Match Replay Page
            request.setAttribute("gameMoves", temp);
            request.getRequestDispatcher("load.jsp").forward(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
