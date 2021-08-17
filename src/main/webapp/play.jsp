<!-- Set up  -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.database.csce310project.queryGamesClass" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.database.csce310project.Game" %>
<!DOCTYPE html>

<!-- Initialize Variable -->
<% Game playGame = (Game) request.getAttribute("playGame"); %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/load.css"/>
    <title>Play</title>
</head>

<!-- Styling -->
<link rel="stylesheet" href="https://unpkg.com/@chrisoakman/chessboardjs@1.0.0/dist/chessboard-1.0.0.min.css"
      integrity="sha384-q94+BZtLrkL1/ohfjR8c6L+A6qzNH9R2hBLwyoAfu3i/WCvQjzL2RQJ3uNHDISdU"
      crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2"
        crossorigin="anonymous"></script>

<script src="https://unpkg.com/@chrisoakman/chessboardjs@1.0.0/dist/chessboard-1.0.0.min.js"
        integrity="sha384-8Vi8VHwn3vjQ9eUHUxex3JSN/NFqUg3QbPyX8kWyb93+8AC/pPWTzj+nHtbC5bxD"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/chess.js@0.12.0/chess.min.js"></script>

<script src="js/main.js" type="module"></script>
<body id="bg">
<div id="id01" class="modal">
    <div class="splash">
        <h1> Game Results </h1>
        <h2 id="result"> White loses </h2>
    </div>
</div>
<span id="audio"></span>
<div id="wrapper">
    <header>
        <h1>EnPassant</h1>
    </header>
    <div id="main">
        <article>
            <h3>Material Advantage</h3>
            <h1 id="matAdv">0</h1>
            <div class="center" style="width: 65%" id="myBoard"></div>
        </article>
        <nav>
            <h3>White Piece Taken</h3>
            <p id="piece_white"></p>
        </nav>
        <aside>
            <h3>Black Piece Taken</h3>
            <p id="piece_black"></p>
        </aside>
    </div>  <!-- main -->
    <div class="center" style="align-content: center; text-align: center">
        <button type="button" class="uiButton" onclick="window.location.href='index.jsp'">Main Menu</button>
    </div>
    <footer id="footer">
    <p class="copyright">&copy; EN PASSANT</p>
    </footer>
</div>  <!-- wrapper -->

<script language="JavaScript1.5">
    var newGameID = <%= playGame.getId() %>;
    var servletPath = 'http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}';
    console.log("servletPath=" + servletPath);
</script>

<script src="assets/js/code.js"></script>
<script src="assets/js/board.js"></script>

</body>
</html>


