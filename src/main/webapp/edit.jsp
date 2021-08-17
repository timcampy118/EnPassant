<!-- Set up  -->
<%@ page import="com.database.csce310project.GamePlayer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<!-- Initialize Variable -->
<% GamePlayer gamePlayer = (GamePlayer) request.getAttribute("gamePlayerData"); %>

<html>
<head>
    <title>Edit Game</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/matches.css" />

</head>

<body class="is-preload">
    <div id="wrapper">
      <!-- Edit Game Name Inputs -->
      <div id="id01" class="modal" style="display: block">
        <span class="close" title="Close Modal" onclick="window.location.href='${pageContext.request.contextPath}/Matches'">&times;</span>
        <form class="modal-content" action="SaveGame" method="post">
          <div class="container">
            <h1>Edit Game Name</h1>
            <hr>
            <input type="hidden" id="gameId" name="gameId" value="<%= gamePlayer.getGameId() %>">
            <label><b>Game Name</b></label>
            <input type="text" placeholder="Enter Name" name="gameName" required id="inputField" value="<%= gamePlayer.getGameName() %>">
            <label><b>White's Name</b></label>
            <input type="text" readonly placeholder="Enter White's Name" name="player1" required id="gameField1" value="<%= gamePlayer.getPlayer1() %>">
            <label><b>Black's Name</b></label>
            <input type="text" readonly placeholder="Enter Black's Name" name="player2" required id="gameField2" value="<%= gamePlayer.getPlayer2() %>">
            <div class="clearfix">
              <button id="submit" type="submit" class="signupbtn">Submit</button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <footer id="footer">
        <p class="copyright">&copy; EN PASSANT</p>
    </footer>
    <!-- BG -->
    <div id="bg"></div>

    <!-- Scripts -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/breakpoints.min.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>
