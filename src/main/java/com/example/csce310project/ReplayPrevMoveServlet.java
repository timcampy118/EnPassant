package com.example.csce310project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import com.database.csce310project.*;

@WebServlet(name = "ReplayPrevMoveServlet", urlPatterns = "/ReplayPrevMove")
public class ReplayPrevMoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReplyPrevMove - doGet");
        Connection gconnect = null;
        try {
            //connect to database
            gconnect = queryGamesClass.getConnection();

            int current_gameid = 0;
            int current_moveid = 0;

            //Gets the previous move from the Move Query
            ArrayList<Game> temp = queryGamesClass.getReplayPrevMove(gconnect,current_gameid,current_moveid);

            //Redirect back to play
            request.setAttribute("gameIDs", temp);
            request.getRequestDispatcher("play.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
