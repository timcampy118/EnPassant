<!-- Set up  -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>En Passant</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />

    <!-- Scripts -->
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">
    <noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
</head>

<body class="is-preload">
<!-- Wrapper -->
    <div id="wrapper">

        <!-- Header -->
        <header id="header">
            <div class="logo">
                <span class="fas fa-chess fa-2x"></span>
            </div>
            <div class="content">
                <div class="inner">
                    <h1>En Passant</h1>
                    <p>CSCE 310 : Summer 2021 Edition</p>
                </div>
            </div>

            <!-- Buttons -->
            <form action="PlayGame" method="post">
                <nav>
                    <ul>
                        <li><button type="button" onclick="window.location.href='#history'">History</button></li>
                        <li><button type="button" onclick="window.location.href='#Learn'">Learn</button></li>
                        <li><button type="submit" id="playButton" disabled>Play</button></li>
                        <li><button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Matches'">Matches</button></li>
                    </ul>
                </nav>
                <br><label>White:</label>
                <textarea class="input" id="player1" name="player1" onkeyup="success()" placeholder="Enter White's Name"></textarea>
                <br />
                <label>Black:</label>
                <textarea class="input" id="player2" name="player2" onkeyup="success()" placeholder="Enter Black's Name"></textarea>
            </form>
        </header>

        <!-- Main -->
        <div id="main">
            <!-- History Page -->
            <article id="history">
                <h2 class="major">History</h2>
                <span class="image main"><img src="images/pic01.jpeg" alt="" /></span>
                <p>Chess, as we know it today, was born out of the Indian game chaturanga before the 600s AD. The game spread throughout Asia and Europe over the coming centuries, and eventually evolved into what we know as chess around the 16th century. One of the first masters of the game was a Spanish priest named Ruy Lopez. Although he didn't invent the opening named after him, he analyzed it in a book he published in 1561. Chess theory was so primitive back then that Lopez advocated the strategy of playing with the sun in your opponent's eyes!</p>
            </article>

            <!-- Rules Page -->
            <article id="Learn">
                <h2 class="major">Rules</h2>
                <span class="image main"><img src="images/rule.jpg" alt="" /></span>
                <h3>Point Value System</h3>
                <p>
                    Pawn = 1 point<br><br>
                    Bishop = 3 points<br><br>
                    Knight = 3 points<br><br>
                    Rook = 5 points<br><br>
                    Queen = 9 points<br><br>
                    Bishop = 3 points<br><br>
                    *King does not have a point value*<br>
                </p>
                <div class="article_body_content">
                    <!-- How to play Chess -->
                    <h2>How to play chess for beginners</h2>
                    <ul>
                        <li><strong><a href="#setup">Setup:</a></strong> Which square does the queen go on? Find out as we get ready to play by placing your pieces.</li>
                        <li><strong><a href="#basic-rules">Basic rules:</a></strong> Learn how each chess piece moves and captures.</li>
                        <li><strong><a href="#check-checkmate">Check and checkmate:</a></strong> Surround your opponent’s king to win.</li>
                        <li><strong><a href="#advanced-rules">Advanced rules:</a></strong> Promotion, en passant and castling explained.</li>
                    </ul>
                    <p>
                        Whether you’ve just rediscovered chess or are sitting down to play for the very first time, we’ve put together this beginner-friendly guide on how to play. This guide includes all of the basics you need to know when learning chess, from which square to place the queen on during setup to when to call “checkmate” and claim victory.
                    </p>
                    <p>
                        While you’ll find everything you need to play a casual game of chess below, we haven’t gone into as much depth on advanced elements such as specific openings, board positions or tournament rules. These are the pure basics to get you playing as quickly as possible, without worrying about timing your turns or defending against specific openings. Once you know the basics of how to play, you can learn new strategies, tactics and deepen your understanding of the game as you play, gradually refining your skill and experience. Who knows, perhaps this might be the first step on your path to becoming the next grandmaster.
                    </p>
                    <iframe allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" data-src="https://www.youtube.com/embed/6Pqd7UFWr7M" frameborder="0" height="315" width="560" data-lazyload="loaded" id="yt-6Pqd7UFWr7M" data-youtubeid="6Pqd7UFWr7M" src="https://www.youtube.com/embed/6Pqd7UFWr7M"></iframe><div class="embed_placeholder" data-type="targeting" style="display: none;">
                    <span class="message">To see this content please enable targeting cookies.</span>
                    <button class="ot-sdk-show-settings" id="ot-sdk-btn">Cookies Settings</button>
                </div>
                    <h3 id="setup">Setup</h3>
                    <p>
                        Chess is played by two players on a chess board measuring eight-by-eight squares. The 64 squares alternate between light and dark colours - traditionally, black and white. When properly set up, a white square should be the rightmost square along the edge closest to each player.
                    </p>
                    <p>
                        Players’ pieces are set up in the two horizontal rows (known as ranks) closest to each player. The second rank - ie. the second row from the player’s perspective - consists of a line of eight pawns, each placed on a single square.
                    </p>
                    <p>
                        The closer rank is nearly symmetrical, with rooks (also known as castles) placed on the two leftmost and rightmost corner squares, followed by knights on the inside space next to them, then bishops.
                    </p>
                    <p>
                        The two central squares of the rank are occupied by the king and queen. The queen is placed on the square matching her colour (for example, the black queen on the black square), with the king occupying the remaining square of the opposite colour. This means that the king and queen of each colour face each other, making the correct setup symmetrical between the two players.
                    </p>
                    <p>
                        The white player takes the first move, with players alternating single turns until a player is defeated via checkmate or resigns. A draw can also be agreed. If playing with an optional timer, as in tournaments, the first player to run out of time forfeits the game.
                    </p>
                    <figure>
                        <a data-lightbox="true" href="https://assets.dicebreaker.com/chess-setup-layout.png/BROK/resize/1920x1920%3E/format/jpg/quality/80/chess-setup-layout.png" target="_blank">
                            <img alt="Chess starting layout" class="content_image" data-aspect-ratio="1.0" data-lazyload="loaded" src="https://assets.dicebreaker.com/chess-setup-layout.png/BROK/resize/660%3E/format/jpg/quality/80/chess-setup-layout.png" srcset="https://assets.dicebreaker.com/chess-setup-layout.png/BROK/resize/660%3E/format/jpg/quality/80/chess-setup-layout.png 1x, https://assets.dicebreaker.com/chess-setup-layout.png/BROK/resize/1320%3E/format/jpg/quality/80/chess-setup-layout.png 2x" data-src="https://assets.dicebreaker.com/chess-setup-layout.png/BROK/resize/660%3E/format/jpg/quality/80/chess-setup-layout.png" data-srcset="https://assets.dicebreaker.com/chess-setup-layout.png/BROK/resize/660%3E/format/jpg/quality/80/chess-setup-layout.png 1x, https://assets.dicebreaker.com/chess-setup-layout.png/BROK/resize/1320%3E/format/jpg/quality/80/chess-setup-layout.png 2x" data-background-image="https://www.dicebreaker.com/static/img/placeholder.png" style="background-image: url(&quot;https://www.dicebreaker.com/static/img/placeholder.png&quot;);"></a>
                        <figcaption>The starting positions of the pieces in chess, showing each queen on the square matching her colour. <em>Image: sudowoodo/stock.adobe.com</em></figcaption>
                    </figure>
                    <h3 id="basic-rules">Basic rules</h3>
                    <p>
                        In chess, each player takes turns to make a single move. Players cannot choose to skip a turn - they must move a piece. Each chess piece moves in a specific way, and must be moved according to its legal movement.
                    </p>
                    <p>
                        Except for the knight, which may jump over pieces, pieces cannot move through pieces of either colour without either stopping (in the same of a piece of the same colour) or capturing them (in the case of a piece of the opposite colour).
                    </p>
                    <h4>Capturing pieces</h4>
                    <p>
                        If a piece lands on a space with an opponent’s piece, that piece is captured and removed from the board. Pieces cannot be placed on the same square as a piece of the same colour. When a piece captures an opponent’s piece, it must finish its current move action and end the player’s turn.
                    </p>
                    <figure>
                        <a data-lightbox="true" href="https://assets.dicebreaker.com/chess-pieces.png/BROK/resize/1920x1920%3E/format/jpg/quality/80/chess-pieces.png" target="_blank">
                            <img alt="Outlines of chess pieces" class="content_image" data-aspect-ratio="0.5625" data-lazyload="loaded" src="https://assets.dicebreaker.com/chess-pieces.png/BROK/resize/660%3E/format/jpg/quality/80/chess-pieces.png" srcset="https://assets.dicebreaker.com/chess-pieces.png/BROK/resize/660%3E/format/jpg/quality/80/chess-pieces.png 1x, https://assets.dicebreaker.com/chess-pieces.png/BROK/resize/1320%3E/format/jpg/quality/80/chess-pieces.png 2x" data-src="https://assets.dicebreaker.com/chess-pieces.png/BROK/resize/660%3E/format/jpg/quality/80/chess-pieces.png" data-srcset="https://assets.dicebreaker.com/chess-pieces.png/BROK/resize/660%3E/format/jpg/quality/80/chess-pieces.png 1x, https://assets.dicebreaker.com/chess-pieces.png/BROK/resize/1320%3E/format/jpg/quality/80/chess-pieces.png 2x" data-background-image="https://www.dicebreaker.com/static/img/placeholder.png" style="background-image: url(&quot;https://www.dicebreaker.com/static/img/placeholder.png&quot;);"></a>
                        <figcaption>The different chess pieces, from left to right: pawn, rook, knight, bishop, queen, king. <em>Image: Agzam/Pixabay</em></figcaption>
                    </figure>
                    <h4>How to move chess pieces</h4>
                    <p>
                        <strong>Pawn</strong>
                    </p>
                    <p>
                        Pawns move one square forward in a straight line. They cannot move horizontally, diagonally or backwards.
                    </p>
                    <p>
                        An exception to this is if a pawn is yet to be moved during the game. If a pawn has not yet moved, it may be moved two squares forward as a single move. Both squares must be empty. The player can also choose to move the piece a single square.
                    </p>
                    <p>
                        The only time a pawn may move diagonally is when capturing an opponent’s piece. Pawns may capture an opponent’s piece on either of the diagonal spaces to the left or right ahead of the piece. As part of capturing the piece, the pawn will move diagonally to replace the captured piece. A pawn cannot capture an adjacent piece on any other square, or move diagonally without capturing.
                    </p>
                    <p>
                        <strong>Rook (Castle)</strong>
                    </p>
                    <p>
                        The rook, sometimes called the castle, can move any number of squares horizontally along its current row (rank) or column (file). It cannot pass through pieces of the same colour, and can capture pieces of the opposite colour by moving onto an occupied space. It cannot move diagonally for any reason.
                    <figure>
                        <a data-lightbox="true" href="https://assets.dicebreaker.com/tabletop-playground-open-beta-chess.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/tabletop-playground-open-beta-chess.jpg" target="_blank">
                            <img alt="tabletop-playground-open-beta-chess.jpg" class="content_image" data-aspect-ratio="0.5625" data-lazyload="loaded" src="https://assets.dicebreaker.com/tabletop-playground-open-beta-chess.jpg/BROK/resize/660%3E/format/jpg/quality/80/tabletop-playground-open-beta-chess.jpg" srcset="https://assets.dicebreaker.com/tabletop-playground-open-beta-chess.jpg/BROK/resize/660%3E/format/jpg/quality/80/tabletop-playground-open-beta-chess.jpg 1x, https://assets.dicebreaker.com/tabletop-playground-open-beta-chess.jpg/BROK/resize/1320%3E/format/jpg/quality/80/tabletop-playground-open-beta-chess.jpg 2x" data-src="https://assets.dicebreaker.com/tabletop-playground-open-beta-chess.jpg/BROK/resize/660%3E/format/jpg/quality/80/tabletop-playground-open-beta-chess.jpg" data-srcset="https://assets.dicebreaker.com/tabletop-playground-open-beta-chess.jpg/BROK/resize/660%3E/format/jpg/quality/80/tabletop-playground-open-beta-chess.jpg 1x, https://assets.dicebreaker.com/tabletop-playground-open-beta-chess.jpg/BROK/resize/1320%3E/format/jpg/quality/80/tabletop-playground-open-beta-chess.jpg 2x" data-background-image="https://www.dicebreaker.com/static/img/placeholder.png" style="background-image: url(&quot;https://www.dicebreaker.com/static/img/placeholder.png&quot;);"></a>
                        <figcaption>A game of chess in Tabletop Simulator.</figcaption>
                    </figure>
                    <strong>Knight</strong>
                    </p>
                    <p>
                        Knights are the only chess piece that may be moved ‘through’ other pieces by ‘jumping’ over them. It captures pieces as normal by landing on a space occupied by a piece of the opposite colour and cannot move to a square occupied by a piece of the same colour, but may move over pieces of either colour during its move.
                    </p>
                    <p>
                        Knights move in a fixed ‘L’ pattern: two squares forward, backward, left or right, then one square horizontally or vertically, or vice versa - one square forward, backward, left or right, followed by two squares horizontally or vertically to complete the ‘L’ shape.
                    </p>
                    <p>
                        This means that the knight can always move to the closest square that is not on its current row (rank), column (file) or directly adjacent diagonally.
                    </p>
                    <p>
                        The knight must move the full distance - it cannot move just two squares in a straight line without also moving one to the side, for instance.
                    </p>
                    <figure>
                        <a data-lightbox="true" href="https://assets.dicebreaker.com/chess-knight-moves.png/BROK/resize/1920x1920%3E/format/jpg/quality/80/chess-knight-moves.png" target="_blank">
                            <img class="content_image" data-aspect-ratio="1.0" data-lazyload="loaded" src="https://assets.dicebreaker.com/chess-knight-moves.png/BROK/resize/660%3E/format/jpg/quality/80/chess-knight-moves.png" srcset="https://assets.dicebreaker.com/chess-knight-moves.png/BROK/resize/660%3E/format/jpg/quality/80/chess-knight-moves.png 1x, https://assets.dicebreaker.com/chess-knight-moves.png/BROK/resize/1320%3E/format/jpg/quality/80/chess-knight-moves.png 2x" data-src="https://assets.dicebreaker.com/chess-knight-moves.png/BROK/resize/660%3E/format/jpg/quality/80/chess-knight-moves.png" data-srcset="https://assets.dicebreaker.com/chess-knight-moves.png/BROK/resize/660%3E/format/jpg/quality/80/chess-knight-moves.png 1x, https://assets.dicebreaker.com/chess-knight-moves.png/BROK/resize/1320%3E/format/jpg/quality/80/chess-knight-moves.png 2x" data-background-image="https://www.dicebreaker.com/static/img/placeholder.png" style="background-image: url(&quot;https://www.dicebreaker.com/static/img/placeholder.png&quot;);"></a>
                        <figcaption>The available moves of a knight, showing the 'L' shape.</figcaption>
                    </figure>
                    <p>
                        <strong>Bishop</strong>
                    </p>
                    <p>
                        The bishop can move any number of squares diagonally - this means it always moves along the diagonal line of squares matching the current colour of its square. This means that each player begins the game with one bishop that can move on each colour. A bishop cannot move horizontally or vertically for any reason. It cannot move through pieces of the same colour, and captures a piece of the opposite colour by moving onto its square.
                    </p>
                    <p>
                        <strong>Queen</strong>
                    </p>
                    <p>
                        The queen may move any number of squares horizontally, vertically or diagonally. These movements must be made in a single straight line during a single turn. (In other words, you can’t move three squares diagonally, followed by three spaces vertically.) The queen cannot move through pieces of the same colour, and captures a piece of the opposite colour by moving onto its square.
                    </p>
                    <p>
                        <strong>King</strong>
                    </p>
                    <p>
                        The king moves a single space horizontally, vertically or diagonally. The king cannot move into a space that would grant a check or checkmate to the opponent player.
                    </p>
                    <p>
                        As an exception to all other chess pieces, the king is never captured - a player loses the match when the king is placed into checkmate, which would lead to an unavoidable capture on the opponent’s next turn.
                    </p>
                    <figure>
                        <a data-lightbox="true" href="https://assets.dicebreaker.com/chess-king-pawns.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/chess-king-pawns.jpg" target="_blank">
                            <img alt="A chess king and pawn pieces" class="content_image" data-aspect-ratio="0.5625" data-lazyload="loaded" src="https://assets.dicebreaker.com/chess-king-pawns.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-king-pawns.jpg" srcset="https://assets.dicebreaker.com/chess-king-pawns.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-king-pawns.jpg 1x, https://assets.dicebreaker.com/chess-king-pawns.jpg/BROK/resize/1320%3E/format/jpg/quality/80/chess-king-pawns.jpg 2x" data-src="https://assets.dicebreaker.com/chess-king-pawns.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-king-pawns.jpg" data-srcset="https://assets.dicebreaker.com/chess-king-pawns.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-king-pawns.jpg 1x, https://assets.dicebreaker.com/chess-king-pawns.jpg/BROK/resize/1320%3E/format/jpg/quality/80/chess-king-pawns.jpg 2x" data-background-image="https://www.dicebreaker.com/static/img/placeholder.png" style="background-image: url(&quot;https://www.dicebreaker.com/static/img/placeholder.png&quot;);"></a>
                        <figcaption>The king is never captured during a chess match - being placed into checkmate ends the game. <em>Image: Angela Bedürftig/Pixabay</em></figcaption>
                    </figure>
                    <h3 id="check-checkmate">Check and checkmate</h3>
                    <p>
                        When a piece moves in a way that would allow a player to capture the opponent’s king on their next turn, the attacking player typically announces “check”.
                    </p>
                    <p>
                        The player placed into check must move their king or move another piece to stop the attack on their next turn - either by blocking the move or capturing the attacking piece.
                    </p>
                    <p>
                        If a player creates a situation where their opponent cannot stop their king from being captured on the next turn, the attacking player announces “checkmate” and immediately wins the game. The king is never captured - a game of chess is won when a successful checkmate is announced.
                    </p>
                    <figure>
                        <a data-lightbox="true" href="https://assets.dicebreaker.com/chess-checkmate.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/chess-checkmate.jpg" target="_blank">
                            <img alt="A checkmate in chess" class="content_image" data-aspect-ratio="0.6667" data-lazyload="loaded" src="https://assets.dicebreaker.com/chess-checkmate.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-checkmate.jpg" srcset="https://assets.dicebreaker.com/chess-checkmate.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-checkmate.jpg 1x, https://assets.dicebreaker.com/chess-checkmate.jpg/BROK/resize/1320%3E/format/jpg/quality/80/chess-checkmate.jpg 2x" data-src="https://assets.dicebreaker.com/chess-checkmate.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-checkmate.jpg" data-srcset="https://assets.dicebreaker.com/chess-checkmate.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-checkmate.jpg 1x, https://assets.dicebreaker.com/chess-checkmate.jpg/BROK/resize/1320%3E/format/jpg/quality/80/chess-checkmate.jpg 2x" data-background-image="https://www.dicebreaker.com/static/img/placeholder.png" style="background-image: url(&quot;https://www.dicebreaker.com/static/img/placeholder.png&quot;);"></a>
                        <figcaption>Checkmate occurs when a player cannot escape check, meaning their king would be captured on its next turn. <em>Image: Steve Buissinne/Pixabay</em></figcaption>
                    </figure>
                    <p>
                        A player can also choose to resign, granting their opponent the victory. Matches can also end in an agreed draw - for example, as the result of stalemate leaving a player without any legal moves, or if no player can win using available legal moves, a situation known as a “dead position”. One example of a dead position is when both players are left with their king as their only remaining piece on the board.
                    </p>
                    <p>
                        Draws can also occur as the result of advanced rules typically used in professional tournaments, including identical board positions occurring three or five times - rules known respectively as threefold repetition and fivefold repetition - or no captures or pawn moves taking place within the last 50 or 75 moves. The exact rules used can depend on the tournament and agreement between the players.
                    </p>
                    <figure>
                        <a data-lightbox="true" href="https://assets.dicebreaker.com/old-men-playing-chess.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/old-men-playing-chess.jpg" target="_blank">
                            <img alt="Two elderly men playing chess" class="content_image" data-aspect-ratio="0.5625" data-lazyload="loaded" src="https://assets.dicebreaker.com/old-men-playing-chess.jpg/BROK/resize/660%3E/format/jpg/quality/80/old-men-playing-chess.jpg" srcset="https://assets.dicebreaker.com/old-men-playing-chess.jpg/BROK/resize/660%3E/format/jpg/quality/80/old-men-playing-chess.jpg 1x, https://assets.dicebreaker.com/old-men-playing-chess.jpg/BROK/resize/1320%3E/format/jpg/quality/80/old-men-playing-chess.jpg 2x" data-src="https://assets.dicebreaker.com/old-men-playing-chess.jpg/BROK/resize/660%3E/format/jpg/quality/80/old-men-playing-chess.jpg" data-srcset="https://assets.dicebreaker.com/old-men-playing-chess.jpg/BROK/resize/660%3E/format/jpg/quality/80/old-men-playing-chess.jpg 1x, https://assets.dicebreaker.com/old-men-playing-chess.jpg/BROK/resize/1320%3E/format/jpg/quality/80/old-men-playing-chess.jpg 2x" data-background-image="https://www.dicebreaker.com/static/img/placeholder.png" style="background-image: url(&quot;https://www.dicebreaker.com/static/img/placeholder.png&quot;);"></a>
                        <figcaption>Chess can end as the result of checkmate, resignation, a draw or - in tournament settings - a player running out of time.</figcaption>
                    </figure>
                    <h3 id="advanced-rules">Advanced rules</h3>
                    <p>
                        There are a number of advanced rules that can be used in chess, along with specific openings and board positions known by a variety of names, from the Double King's Pawn Opening to the famous King’s Gambit and Queen’s Gambit.
                    </p>
                    <p>
                        Advanced rules can include specific variants that alter the core rules of the game, along with surrounding requirements often used in tournament settings, such as timing and the touch-move rule - which states that once a piece is touched by a player, it must make a legal move.
                    </p>
                    <p>
                        As this is a beginner’s guide to learning chess, we’ll only be covering some of the essential advanced rules here - rules that should always be used in conjunction with the basic rules for moving and capturing pieces, as well as the standard setup and rules for declaring checkmate.
                    </p>
                    <p>
                        Once you know the basics of how to play chess, there are hundreds of books and other resources out there to help you discover the deep strategy and near-endless variations possible during games - as well as local tournaments that can help you refine your play and tactics.
                    </p>
                    <figure>
                        <a data-lightbox="true" href="https://assets.dicebreaker.com/chess-board-pieces.jpg/BROK/resize/1920x1920%3E/format/jpg/quality/80/chess-board-pieces.jpg" target="_blank">
                            <img alt="Chess pieces" class="content_image" data-aspect-ratio="0.5625" data-lazyload="loaded" src="https://assets.dicebreaker.com/chess-board-pieces.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-board-pieces.jpg" srcset="https://assets.dicebreaker.com/chess-board-pieces.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-board-pieces.jpg 1x, https://assets.dicebreaker.com/chess-board-pieces.jpg/BROK/resize/1320%3E/format/jpg/quality/80/chess-board-pieces.jpg 2x" data-src="https://assets.dicebreaker.com/chess-board-pieces.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-board-pieces.jpg" data-srcset="https://assets.dicebreaker.com/chess-board-pieces.jpg/BROK/resize/660%3E/format/jpg/quality/80/chess-board-pieces.jpg 1x, https://assets.dicebreaker.com/chess-board-pieces.jpg/BROK/resize/1320%3E/format/jpg/quality/80/chess-board-pieces.jpg 2x" data-background-image="https://www.dicebreaker.com/static/img/placeholder.png" style="background-image: url(&quot;https://www.dicebreaker.com/static/img/placeholder.png&quot;);"></a>
                        <figcaption>There are thousands of potential board layouts from hundreds of openings and variants. <em>Image: Steve Buissinne/Pixabay</em></figcaption>
                    </figure>
                    <h4>Promotion</h4>
                    <p>
                        If a pawn reaches the opposite edge of the board - the farthest row (rank) from the controlling player - it is promoted to another piece: a rook, knight, bishop or queen. The new piece replaces the pawn on its current square, and follows the movement rules for the respective piece.
                    </p>
                    <p>
                        While most casual players use captured pieces to represent promoted pieces, a pawn can legally be promoted to any piece regardless of whether it has been captured. For example, a player may have multiple queens as the result of promoting pawns, or multiple bishops able to move along diagonal lines of the same colour depending on the square on which the pawn was promoted.
                    </p>
                    <p>
                        There is no limit to the number of pawns that can be promoted.
                    </p>

                    <h4>En passant</h4>
                    <p>
                        En passant - French for ‘in passing’ - is one of the most famous moves in chess. En passant occurs when a pawn moves two squares forward as the result of its optional starting move.
                    </p>
                    <p>
                        If an opponent’s pawn would have been able to legally capture the moving pawn had it only moved one square instead of two, the opponent can declare en passant on their next turn and move their pawn diagonally onto the square that the pawn passed through - capturing the pawn as if it had only moved one square.
                    </p>
                    <p>
                        En passant must be declared and made as the opponent’s next turn to be legal - otherwise, the player with the chance to capture the pawn loses the opportunity.
                    </p>
                    <iframe allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" data-src="https://www.youtube.com/embed/c_KRIH0wnhE" frameborder="0" height="315" width="560" data-lazyload="loaded" id="yt-c_KRIH0wnhE" data-youtubeid="c_KRIH0wnhE" src="https://www.youtube.com/embed/c_KRIH0wnhE"></iframe><div class="embed_placeholder" data-type="targeting" style="display: none;">
                    <span class="message">To see this content please enable targeting cookies.</span>
                    <button class="ot-sdk-show-settings" id="ot-sdk-btn">Cookies Settings</button>
                </div>
                    <h4>Castling</h4>
                    <p>
                        Castling is perhaps the most complicated basic rule in chess, and a rule that many beginners often overlook as a result.
                    </p>
                    <p>
                        Castling is permitted when a player’s king piece and a rook have not yet moved during the game. Castling can be performed with either rook, as long as they haven’t moved - in other words, they are still in their starting corners on the edge closest to the controlling player.
                    </p>
                    <p>
                        Castling involves a player moving the king piece two squares towards the rook with which they are castling, before moving the rook to the square that the king moved ‘through’. This effectively puts the rook adjacent on the other side of the king, while the king moves two squares towards the space in which the rook started the game. Regardless of whether castling is performed with the rook closer to the king (kingside) or one square further away (queenside), the king only ever moves two spaces.
                    </p>
                    <p>
                        The king cannot be used in a castling manoeuvre if it is currently in check, but a rook can be used in castling even if it is under threat from an opponent’s piece - in other words, if it could be captured on the opponent’s next turn, or on any of the squares it passes through while performing the move.
                    </p>
                    <p>
                        As usual, castling cannot be used to move the king if it would put the king into check. Castling also cannot be used if there are any pieces between the king and the rook - the squares between must be clear.
                    </p>
                    <h3>Source from: https://www.dicebreaker.com/games/chess/how-to/how-to-play-chess</h3>
            </article>
        </div>

        <!-- Footer -->
        <footer id="footer">
            <p class="copyright">&copy; EN PASSANT</p>
        </footer>
    </div>
    <!-- BG -->
    <div id="bg"></div>
</body>
</html>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/browser.min.js"></script>
<script src="assets/js/breakpoints.min.js"></script>
<script src="assets/js/main.js"></script>
<script>
    //Player 1 and Player 2 Name fields must be filled before allowing to click on Play Button
    function success() {
        if((document.getElementById("player1").value=="") && (document.getElementById("player2").value=="")) {
            document.getElementById('playButton').disabled = true;
        }
        else if((document.getElementById("player1").value!="") && (document.getElementById("player2").value=="")) {
            document.getElementById('playButton').disabled = true;
        }
        else if((document.getElementById("player1").value=="") && (document.getElementById("player2").value!="")) {
            document.getElementById('playButton').disabled = true;
        }
        else {
            document.getElementById('playButton').disabled = false;
        }

        document.getElementById("playButton").addEventListener("click", function() {
            console.log(document.getElementById("player1").value);
            console.log(document.getElementById("player2").value );

            //Goes to PlayGame Servlet
            window.location.href='${pageContext.request.contextPath}/PlayGame';
        });
    }
</script>