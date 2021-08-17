// NOTE: this example uses the chess.js library:
// https://github.com/jhlywa/chess.js

//Initiating variable
var board = null
var game = new Chess()
var whiteSquareGrey = '#a9a9a9'
var blackSquareGrey = '#696969'
var gameID = -1;
var modal = document.getElementById('id01');

//On start we pull the Game ID from the URL
let params = new URLSearchParams(document.location.search.substring(1));
let searchName = params.get("game");

//update GameID, set ID to -1 is nothing is given to prevent breaking database.
gameID=searchName;
console.log(gameID);
if(gameID==null)
  gameID=-1;

//remove the grey square the showed possible moves
function removeGreySquares () {
  $('#myBoard .square-55d63').css('background', '')
}

//add grey square that showed possible moves
function greySquare (square) {
  var $square = $('#myBoard .square-' + square)

  var background = whiteSquareGrey
  if ($square.hasClass('black-3c85d')) {
    background = blackSquareGrey
  }

  $square.css('background', background)
}


//Lets player drag piece if not game over and piece turn
function onDragStart (source, piece) {
  // do not pick up pieces if the game is over
  if (game.game_over()) 
    {
      return false
    }   
  // or if it's not that side's turn
  else if ((game.turn() === 'w' && piece.search(/^b/) !== -1) || (game.turn() === 'b' && piece.search(/^w/) !== -1)) 
    {
    return false
    }
}

//When we drop piece, check if legal then send update to front and back end
function onDrop (source, target,piece,newPos, oldPos) {
  //hide possible move for piece
  removeGreySquares()


  // see if the move is legal
  var move = game.move({
    from: source,
    to: target,
    promotion: 'q' // NOTE: always promote to a queen for example simplicity
  })

  // illegal move
  if (move === null) return 'snapback'
  else //legal move
  {
      $('#audio').html('<audio autoplay><source src="assets/js/click.wav"></audio>');
      var oldMove = Chessboard.objToFen(oldPos);
      var newMove = Chessboard.objToFen(newPos);

      console.log('MOVED TO');
      console.log('New FEN2: ' + newMove);
      console.log('Old FEN2: ' + oldMove);
      console.log('~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~');
      //CODE TO PASS GAMEID AND OLD AND NEW FENN TO FUNCTION AND RECIEVE PIECE TAKEN
      //displayTakenPiece("test");

      //CALL FUNCTION TO UPDATE PIECE TAKEN FRONT END PASSING IT STRING OF PIECE TAKEN.
      //CALL FUNCTION TO UPDATE MATERIAL ADVANTAGE
      updateStatus();

      updateDatabase(oldMove, newMove);
    }
}

function updateDatabase(oldMove, newMove)
{
  console.log("newGameID=" + newGameID);
  console.log("servletPath=" + servletPath);

  $.get(servletPath + '/StoreMove?id=' + newGameID + '&oldfen=' + oldMove + '&newfen=' + newMove, function(data) {
    if (data != null && data.toString().length > 0){
      displayTakenPiece(data.toString().trim());
    }
  });
}

//After dropping, we redraw the board   
function onSnapEnd () {
  board.position(game.fen())
}




// get list of possible moves for this square
function onMouseoverSquare (square, piece) 
{
  var moves = game.moves({
    square: square,
    verbose: true
  })

  // exit if there are no moves available for this square
  if (moves.length === 0) return

  // highlight the square they moused over
  greySquare(square)

  // highlight the possible squares for this piece
  for (var i = 0; i < moves.length; i++) {
    greySquare(moves[i].to)
  }
}

//when we leave square remove highlighted possible moves
function onMouseoutSquare (square, piece) {
  removeGreySquares()
}

//board config and setup
var config = {
  position: 'start',
  draggable: true,
  dropOffBoard: 'snapback',
  onDragStart: onDragStart,
  onDrop: onDrop,
  onMouseoutSquare: onMouseoutSquare,
  onMouseoverSquare: onMouseoverSquare,
  onSnapEnd: onSnapEnd
}
board = Chessboard('myBoard', config)
$(window).resize(board.resize)


//Check for checkmate or draw and update backend
function updateStatus () {      
  // in checkmate
  if (game.in_checkmate()) {
    //FUNTION HERE TO ADD GAME RESULTS
    var winner = game.turn(); // b or w
    $.get(servletPath + '/Results?id=' + newGameID + '&winner=' + winner, function(data) {
      //if (data != null && data.toString().length > 0)
      //  alert(data.toString().trim());
    });

    //Calculate Winner
    var tmpWinner="";
    if(game.turn()=='b')
      tmpWinner="White"
    else
      tmpWinner="Black"
    //Display Winner
    document.getElementById('result').innerHTML = tmpWinner + " Wins!";
    document.getElementById("id01").style.display = "block";
    console.log (game.turn() + ' Loses')
  }
  // draw?
  else if (game.in_draw()) {
    //FUNTION HERE TO ADD GAME RESULTS
    $.get(servletPath + '/Results?id=' + newGameID+ '&winner=t', function(data) {
      //if (data != null && data.toString().length > 0)
      //  alert(data.toString().trim());
    });
    document.getElementById('result').innerHTML = "Draw!";
    document.getElementById("id01").style.display = "block";
    console.log(status = 'Draw Game')
  }  
}
//Hide result splash screen when clicking off
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
        

