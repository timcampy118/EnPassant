package com.example.csce310project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import com.database.csce310project.*;

@WebServlet(name = "MatchesServlet", urlPatterns = "/Matches")
public class MatchesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection gconnect = null;

        try {
            gconnect = queryGamesClass.getConnection();

            //Query up the Games recorded in the Game Table
            ArrayList<Game> temp = queryGamesClass.queryGame(gconnect);

            //Redirect to Match History Page
            request.setAttribute("gameIDs", temp);
            request.getRequestDispatcher("matches.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection gconnect2 = null;

        try {
            gconnect2 = queryGamesClass.getConnection();

            //Query up the Games recorded in the Game Table
            ArrayList<Game> temp = queryGamesClass.queryGame(gconnect2);

            //Redirect to Match History Page
            request.setAttribute("gameIDs", temp);
            request.getRequestDispatcher("matches.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
