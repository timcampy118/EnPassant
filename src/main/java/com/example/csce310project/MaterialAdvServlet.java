package com.example.csce310project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import com.database.csce310project.*;

@WebServlet(name = "MaterialAdvServlet", urlPatterns = "/MaterialAdv")
public class MaterialAdvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MatAdv - doGet");
        Connection gconnect = null;

        try {
            //connect to database
            gconnect = queryGamesClass.getConnection();

            //placeholders
            String MaterialAdvValue = request.getParameter("matvalueSum");
            int gameid = Integer.parseInt(request.getParameter("id"));

            //updates the material advantage table
            queryGamesClass.UpdateMatAdv(gconnect,gameid,MaterialAdvValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MatAdv - doPost");

    }
}
