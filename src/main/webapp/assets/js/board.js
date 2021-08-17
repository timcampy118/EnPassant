//Update Piece Taken and Material Advantage when piece is taken
var counter;
function displayTakenPiece(target){

  counter = parseInt(document.getElementById("matAdv").innerHTML);
  var elem = document.createElement("img");
  switch (target){
    case "wB":
      elem.setAttribute("src", "images/wB.png");
      elem.alt=target;
      document.getElementById("piece_white").appendChild(elem);
      counter -= 3;
      break;
    case "wP":
      elem.setAttribute("src", "images/wP.png");
      elem.alt=target;
      document.getElementById("piece_white").appendChild(elem);
      counter -= 1;
      break;
    case "wR":
      elem.setAttribute("src", "images/wR.png");
      elem.alt=target;
      document.getElementById("piece_white").appendChild(elem);
      counter -= 5;
      break;
    case "wK":
      elem.setAttribute("src", "images/wK.png");
      elem.alt=target;
      document.getElementById("piece_white").appendChild(elem);
      break;
    case "wQ":
      elem.setAttribute("src", "images/wQ.png");
      elem.alt=target;
      document.getElementById("piece_white").appendChild(elem);
      counter -= 9;
      break;
    case "wN":
      elem.setAttribute("src", "images/wN.png");
      elem.alt=target;
      document.getElementById("piece_white").appendChild(elem);
      counter -= 3;
      break;

    case "bB":
      elem.setAttribute("src", "images/bB.png");
      elem.alt=target;
      document.getElementById("piece_black").appendChild(elem);
      counter += 3;
      break;
    case "bP":
      elem.setAttribute("src", "images/bP.png");
      elem.alt=target;
      document.getElementById("piece_black").appendChild(elem);
      counter += 1;
      break;
    case "bR":
      elem.setAttribute("src", "images/bR.png");
      elem.alt=target;
      document.getElementById("piece_black").appendChild(elem);
      counter += 5;
      break;
    case "bK":
      elem.setAttribute("src", "images/bK.png");
      elem.alt=target;
      document.getElementById("piece_black").appendChild(elem);
      break;
    case "bQ":
      elem.setAttribute("src", "images/bQ.png");
      elem.alt=target;
      document.getElementById("piece_black").appendChild(elem);
      counter += 9;
      break;
    case "bN":
      elem.setAttribute("src", "images/bN.png");
      elem.alt=target;
      document.getElementById("piece_black").appendChild(elem);
      counter += 3;
      break;
    default:
      break;
  }

  document.getElementById("matAdv").innerHTML = counter;

}

//Update Piece Taken and Material Advantage when piece is taken
function removeTakenPiece(target){
  counter = parseInt(document.getElementById("matAdv").innerHTML);
  //console.log(target)
  var tmp='img[alt="'+target+'"]'
  var imgElement = document.querySelector(tmp);
  if(imgElement==null)
    console.log('imgElement is NULL')
  else
    imgElement.remove();

  //reverse mat adv
  switch (target){
    case "wB":
      counter += 3;
      break;
    case "wP":
      counter += 1;
      break;
    case "wR":
      counter += 5;
      break;
    case "wQ":
      counter += 9;
      break;
    case "wN":
      counter += 3;
      break;
    case "bB":
      counter -= 3;
      break;
    case "bP":
      counter -= 1;
      break;
    case "bR":
      counter -= 5;
      break;
    case "bQ":
      counter -= 9;
      break;
    case "bN":
      counter -= 3;
      break;
    default:
      break;
  }
  document.getElementById("matAdv").innerHTML = counter;

}