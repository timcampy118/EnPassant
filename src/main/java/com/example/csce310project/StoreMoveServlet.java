package com.example.csce310project;
import com.database.csce310project.*;

import com.database.csce310project.Game;
import com.database.csce310project.processNewGameClass;
import com.database.csce310project.queryGamesClass;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "StoreMoveServlet", urlPatterns = "/StoreMove")
public class StoreMoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StoreMove - doGet");

        Connection gconnect = null;

        try {
            //connect to database
            gconnect = queryGamesClass.getConnection();

            //placeholders
            String oldfenstring = request.getParameter("oldfen");
            String newfenstring = request.getParameter("newfen");
            int gameid = Integer.parseInt(request.getParameter("id"));

            //Identify which piece was captured and its color
            String capturepiece = processMoveClass.getCapturedPiece(newfenstring,oldfenstring);
            String colorCapture = processMoveClass.reformatCapturedPiece(capturepiece);

            //stores new moves into the database and returns the moveid
            queryGamesClass.storemoveDB(gconnect,gameid,newfenstring, colorCapture);

            //Update Material Advantage
            queryGamesClass.UpdateMatAdv(gconnect,gameid,colorCapture);

            //passing Captured Piece as reference.
            if (colorCapture.length() > 0)
            {
                response.setContentType("text/plain");
                response.getWriter().println(colorCapture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
