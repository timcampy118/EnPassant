package com.example.csce310project;
import com.database.csce310project.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;


@WebServlet(name = "PlayGameServlet", urlPatterns = "/PlayGame")
public class PlayGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("PlayGameServlet - doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("PlayGameServlet - doPost");

        Connection gconnect = null;
        try {
            //connect to database
            gconnect = queryGamesClass.getConnection();

            //Retrieve the player names
            String player1name = request.getParameter("player1");
            String player2name = request.getParameter("player2");

            //Creates a new Game Entry
            Game g = processNewGameClass.processNewGame(player1name,player2name,gconnect);

            //redirect to Play page
            request.setAttribute("playGame", g);
            request.getRequestDispatcher("play.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
