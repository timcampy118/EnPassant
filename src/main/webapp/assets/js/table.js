var selectedGameId=-1;

//function autogenerated the game table and filters if needed
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

  //we search the url if there is any filter applied
  let params = new URLSearchParams(document.location.search.substring(1));
  let searchName = params.get("search");
  console.log(searchName);


  //FUNCTION HERE TO CALL JAVA FOR FULL LIST OF GAMES WITH GAME DETAILS (ID,PLAYER1Name,Player2Name). RETURNS An ARRAY


  //looping through the list of games and create a row for each one
  //This will need to be change to reflect the format we recieve data.

//    let myCounter = '${gameIDs}';
//    alert(myCounter.length);

    for (var i = 0; i < myArr.length; ++i)
    {
        var tr = document.createElement('tr');
        var td = document.createElement('td');
        //game name
//        td.innerHTML="Game "+i;
        td.innerHTML = myArr[i];
        tr.appendChild(td)
        var drop = document.createElement('td');
        var dots = document.createElement('div');
        dots.className="dropdown";
        //var blank = document.createElement('i');
        //blank.className="fas fa-ellipsis-h";
        var content = document.createElement('div');
        content.className="dropdown-content";

        //Load Button
        var load = document.createElement('button');
        load.id=i;
        load.innerHTML="Load Game";
        load.type = "submit";
        load.onclick= function () {
          selectedGameId=this.id;
          location.href = "load.jsp?game="+selectedGameId;
        };

        //Delete Button
        var deleted = document.createElement('button');
        deleted.id=i;
        deleted.innerHTML="Delete Game";
        deleted.type = "submit";
        deleted.onclick= function () {
           selectedGameId=this.id;
           //CALL FUNCTION TO DELETE GAME HERE PASSING SELECTEDGAMEID
           location.href = "matches.jsp";
        };

        //Edit Button
        var edit = document.createElement('button');
        edit.id=i;
        edit.className="Tim";
        edit.name="James";
        edit.tagname="Game Name "+i
        edit.innerHTML="Edit Game";
        edit.type = "submit";
        edit.onclick= function () {
            selectedGameId=this.id;
            document.getElementById("inputField").placeholder = this.tagname;
            document.getElementById("gameField1").placeholder = this.className;
            document.getElementById("gameField2").placeholder = this.name;
            document.getElementById("id01").style.display = "block";
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
      }
    
    //attaching the row to the table
    tbl.appendChild(tbdy);
    body.appendChild(tbl)
}

//call function to generate table on start
tableCreate();

//function for the edit button.
document.getElementById("submit").addEventListener("click", function() {
  //console.log(selectedGameId);
  //console.log(document.getElementById("inputField").value);
  //console.log(document.getElementById("gameField1").value );
  //console.log(document.getElementById("gameField2").value);
  //CALL EDIT FUNCTION HERE AND PASS IT ID, GAMENAME, NAME1, NAME2
  location.href = "matches.html";
});