//Initial Variables
var board = null
var sampleMatchHistory=null
var pos=0
var captured=[];

//Array to get the gameId from the URL and recieve the game's FENN string array.        
function generateArrays() {
let params = new URLSearchParams(document.location.search.substring(1));
let searchName = params.get("game");
console.log(searchName);
//FUNCTION HERE TO PASS IT THE GAME ID AND RECIEVED THE ARRAY OF FENN STRONG FOR THE MATCH
}

//sample data, delete later
sampleMatchHistory = ['rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR','rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR', 'rnbqkbnr/pppp1ppp/8/4p3/3P4/8/PPP1PPPP/RNBQKBNR','rnbqkbnr/pppp1ppp/8/4p3/3P4/3Q4/PPP1PPPP/RNB1KBNR','rnbqkbnr/pppp1ppp/8/8/3p4/3Q4/PPP1PPPP/RNB1KBNR','rnbqkbnr/pppp1ppp/8/8/3Q4/8/PPP1PPPP/RNB1KBNR']


//NEXT BUTTON
//Get next move if we're still under array length
$('#nextBtn').on('click', function () {
  if(pos<sampleMatchHistory.length-1)
  {
    pos=pos+1;
    console.log(sampleMatchHistory[pos])
    board.position(sampleMatchHistory[pos],false);
  }
})

//PREVIOUS BUTTON
//Get prev move if we're still under array length
$('#prevBtn').on('click', function () {
  if(pos>0)
  {
    pos=pos-1;
    board.position(sampleMatchHistory[pos],false);
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


