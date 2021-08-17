<!-- Set up  -->
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.database.csce310project.LoadGame" %>
<%@ page import="com.database.csce310project.Game" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<!-- Initialize Variable -->
<% ArrayList<LoadGame> gameMovesList = (ArrayList<LoadGame>) request.getAttribute("gameMoves"); %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/load.css"/>
    <title>Match History</title>

    <!-- JavaScripts -->
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

</head>

<!-- Background  -->
<body id="bg">
    <div id="wrapper">
        <span id="audio"></span>
        <header>
            <h1>EnPassant</h1>
        </header>
        <div id="id01" class="modal">
            <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
            <h2 id=resultMsg>Result msg here</h2>
        </div>

        <div id="main">
            <article>
                <h3>Material Advantage</h3>
                    <h1 id="matAdv">0</h1>
                <div class="center" style="width: 65%" id="myBoard"></div>
            </article>
            <!-- Two side bar to list piece taken -->
            <nav>
                <h3>White Piece Taken</h3>
                <p id="piece_white"></p>
            </nav>
            <aside>
                <h3>Black Piece Taken</h3>
                <p id="piece_black"></p>
            </aside>
        </div>  <!-- main -->

        <!-- This kinda makes me question why they made me bother with a flexbox in the first place -->
        <div class="center" style="text-align: center">
            <button id="prevBtn" class="uiButton">Previous</button>
            <button id="nextBtn" class="uiButton">Next</button>
        </div>
        <div class="center" style="text-align: center">
            <button type="button" class="uiButton" onclick="window.location.href='index.jsp'">Main Menu</button>
        </div>

        <footer id="footer">
            <p class="copyright">&copy; EN PASSANT</p>
        </footer>
    </div>  <!-- wrapper -->

    <!-- <script src="assets/js/main2.js"></script> -->

    <script src="assets/js/code.js"></script>
    <script src="assets/js/board.js"></script>
    <script language="JavaScript1.5">

    //Initial Variables
    var board = null;
    var sampleMatchHistory=null;
    var pos = -1;
    var captured=[];

    class jsLoadGame {
        constructor(fenString, capturePiece) {
            this.FenString = fenString;
            this.CapturePiece = capturePiece;
        }
    }

    var x = [];

    //Pushes Recorded Game Move into Array
    <% for (int k = 0; k < gameMovesList.size(); ++k) { %>
        x.push(new jsLoadGame('<%= gameMovesList.get(k).getFenValue() %>', '<%= gameMovesList.get(k).getCapturePiece() %>'));
    <% } %>



    //Array to get the gameId from the URL and recieve the game's FENN string array.
    function generateArrays() {
        let params = new URLSearchParams(document.location.search.substring(1));
        let searchName = params.get("game");
        console.log(searchName);
    }

    //NEXT BUTTON
    //Get next move if we're still under array length
    $('#nextBtn').on('click', function () {
      if (pos < x.length )
      {
        pos = pos + 1;

        //Audio when moving pieces
        $('#audio').html('<audio autoplay><source src="assets/js/click.wav"></audio>');
        board.position(x[pos].FenString,false);

        //display captured piece if any
        if(captured.length < pos)
        {
            captured.push(x[pos].CapturePiece);
        }
        displayTakenPiece(x[pos].CapturePiece);
      }
    })

    //PREVIOUS BUTTON
    //Get prev move if we're still under array length
    $('#prevBtn').on('click', function () {
      if (pos > 0)
      {
        pos = pos-1;

        //Remove Captured Piece from Display
        if(pos >= 0 && captured[pos]!=""){
           removeTakenPiece(captured[pos]);
        }

        //Audio for moving chess piece
        $('#audio').html('<audio autoplay><source src="assets/js/click.wav"></audio>');
        board.position(x[pos].FenString,false);
      }
    })


    //board setup
    var config = {
      position: 'start',
      draggable: false,
    }

    board = Chessboard('myBoard', config)
    $(window).resize(board.resize)
    //get the list of moves
    generateArrays()

    </script>
</body>

</html>
