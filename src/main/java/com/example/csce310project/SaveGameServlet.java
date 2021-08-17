package com.example.csce310project;

import com.database.csce310project.Game;
import com.database.csce310project.processNewGameClass;
import com.database.csce310project.queryGamesClass;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "SaveGameServlet", urlPatterns = "/SaveGame")
public class SaveGameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SaveGame - doPost");

        Connection gconnect = null;
        try {
            //connect to database
            gconnect = queryGamesClass.getConnection();

            //placeholders
            Integer gameId = Integer.parseInt(request.getParameter("gameId"));
            String gameName = request.getParameter("gameName");

            //save the store the changes back on to the database
            queryGamesClass.saveGameEdits(gconnect,gameId,gameName);

            //refresh the Match History data after a new game has been saved
            ArrayList<Game> temp = queryGamesClass.queryGame(gconnect);

            //redirect to Match History Page
            request.setAttribute("gameIDs", temp);
            request.getRequestDispatcher("matches.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
