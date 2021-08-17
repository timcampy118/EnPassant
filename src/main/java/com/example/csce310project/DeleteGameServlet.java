package com.example.csce310project;

import com.database.csce310project.Game;
import com.database.csce310project.queryGamesClass;
import com.database.csce310project.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "DeleteGameServlet", urlPatterns = "/DeleteGame")
public class DeleteGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DeleteGame - doGet");
        Connection gconnect = null;

        try {
            gconnect = queryGamesClass.getConnection();

            // gets the game id from the jsp
            int gameIdnum = Integer.parseInt(request.getParameter("id"));

            //delete all instances related to the Game Id
            deleteGameClass.deleteGame(gameIdnum,gconnect);

            //retrieve the remaining games that were NOT deleted, and display them
            ArrayList<Game> temp = queryGamesClass.queryGame(gconnect);

            //redirect to Match History Page
            request.setAttribute("gameIDs", temp);
            request.getRequestDispatcher("matches.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
