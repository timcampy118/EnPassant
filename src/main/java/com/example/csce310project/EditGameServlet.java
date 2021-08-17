package com.example.csce310project;
import com.database.csce310project.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "EditGameServlet", urlPatterns = "/EditGame")
public class EditGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EditGame - doGet");
        Connection gconnect = null;

        try {
            gconnect = queryGamesClass.getConnection();

            //this is a temp variable for changing the game name
            String newgamename = "";

            //gets the game id from the JSP
            int gameIdnum = Integer.parseInt(request.getParameter("id"));

            //original game name based on id
            String gameName = queryGamesClass.getGameIDNames(gconnect,gameIdnum);

            GamePlayer gamePlayer = new GamePlayer();
            gamePlayer.setGameId(gameIdnum);
            gamePlayer.setGameName(gameName);

            //retrieve user input for player 1 and player 2 names
            ArrayList<String> PlayerNames = queryGamesClass.getPlayerName(gconnect, gameIdnum);
            if (PlayerNames != null && PlayerNames.size() == 2)
            {
                gamePlayer.setPlayer1(PlayerNames.get(0));
                gamePlayer.setPlayer2(PlayerNames.get(1));
            }

            //redirect to Edit Page
            request.setAttribute("gamePlayerData", gamePlayer);
            request.getRequestDispatcher("edit.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
