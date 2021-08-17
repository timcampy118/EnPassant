package com.example.csce310project;
import com.database.csce310project.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "SearchServlet", urlPatterns = "/Search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SearchServlet - doGet");
        Connection gconnect = null;

        try {
            gconnect = queryGamesClass.getConnection();
            String searchtext = request.getParameter("search");

            //Search through DB for a Game Name or Player's Name with the associated User Input
            ArrayList<Game> temp = queryGamesClass.searchGameText(gconnect,searchtext);

            //Redirect to Match History Page
            request.setAttribute("gameIDs", temp);
            request.getRequestDispatcher("matches.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SearchServlet - doGet");

    }
}
