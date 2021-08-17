package com.example.csce310project;
import com.database.csce310project.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ResultsServlet", urlPatterns = "/Results")
public class ResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Results - doGet");
        Connection gconnect = null;

        try {
            //connect to database
            gconnect = queryGamesClass.getConnection();

            //initialize variables and winner's turn color
            int gameid = Integer.parseInt(request.getParameter("id"));
            String tcolor = request.getParameter("winner"); //takes into account for ties

            //update Results Table with who won the game
            queryGameMovesClass.updateResults(gconnect,gameid,tcolor.trim());

            //passing the color of the winner to the javascript to be referenced
            if (tcolor.length() > 0)
            {
                response.setContentType("text/plain");
                response.getWriter().println(tcolor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
