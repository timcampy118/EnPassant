<!-- Set up  -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.database.csce310project.queryGamesClass" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.database.csce310project.Game" %>
<%@ page import="com.database.csce310project.GamePlayer" %>
<!DOCTYPE HTML>

<!-- Initialize Variable -->
<% ArrayList<Game> gameIDList = (ArrayList<Game>) request.getAttribute("gameIDs"); %>

<html>
<head>
    <title>Match History</title>
    <meta charset="utf-8" />

    <!-- Styling -->
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/matches.css" />
</head>

<body class="is-preload">
    <div id="wrapper">

        <h1> Match History</h1>
        <div class="menu">
            <button type="button" id="button" onclick="window.location.href='index.jsp'">Main Menu</button>
        </div>

        <div style="display: flex; flex-direction: row;">

            <div style="text-overflow: ellipsis; overflow: hidden; word-break: break-all;" id="table">
                <form name="myForm" action="Search" method="get">
                    <label for="search">Search Games</label>
                    <input type="text" id="search" name="search" placeholder="Search Field"><br>
                </form>
            </div>
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

    <!-- JavaScript Code -->
    <script language="JavaScript1.5">

        function tableCreate() {

          //creating the initial template variables
          var body = document.getElementById("table");
          var tbl = document.createElement('table');
          var tbdy = document.createElement('tbody');
          var title = document.createElement('tr');
          var name = document.createElement('th');
          name.innerHTML = "Game Name";
          var action = document.createElement('th');
          action.innerHTML = "Actions";
          title.appendChild(name);
          title.appendChild(action);
          tbdy.appendChild(title);

          //Search the url if there is any filter applied
          let params = new URLSearchParams(document.location.search.substring(1));
          let searchName = params.get("search");
          console.log(searchName);


          // Assigning Load, Delete, Edit Buttons to Each Game Recorded
           <%  for (int k = 0; k < gameIDList.size(); ++k) { %>

                var tr = document.createElement('tr');
                var td = document.createElement('td');

                td.innerHTML = "<%= gameIDList.get(k).getName() %>";
                tr.appendChild(td)

                var drop = document.createElement('td');
                var dots = document.createElement('div');
                dots.className="dropdown";

                var content = document.createElement('div');
                content.className="dropdown-content";

                //Load Button
                var load = document.createElement('button');
                load.id = <%= k %>;
                load.innerHTML="Load Game";
                load.type = "submit";
                load.onclick= function () {
                    location.href = '${pageContext.request.contextPath}/LoadGame?id=' + <%= gameIDList.get(k).getId() %>;
                };

                //Delete Button
                var deleted = document.createElement('button');
                deleted.id = <%= k %>;
                deleted.innerHTML = "Delete Game";
                deleted.type = "submit";
                deleted.onclick= function () {
                   location.href = '${pageContext.request.contextPath}/DeleteGame?id=' + <%= gameIDList.get(k).getId() %>;
                };

                //Edit Button
                var edit = document.createElement('button');
                deleted.id = <%= k %>;
                edit.innerHTML = "Edit Game";
                edit.type = "submit";
                edit.onclick = function () {
                   location.href = '${pageContext.request.contextPath}/EditGame?id=' + <%= gameIDList.get(k).getId() %>;
                };

                //attach all of the buttons to the row
                content.appendChild(load);
                content.appendChild(deleted);
                content.appendChild(edit);
                //dots.appendChild(blank);
                dots.appendChild(content);
                drop.appendChild(dots);
                tr.appendChild(drop);
                tbdy.appendChild(tr);

            <% } %>

            //attaching the row to the table
            tbl.appendChild(tbdy);
            body.appendChild(tbl)
        }

        tableCreate();

        //function for the edit button.
        document.getElementById("submit").addEventListener("click", function() {
          location.href = "matches.html";
        });

    </script>
</body>
</html>
