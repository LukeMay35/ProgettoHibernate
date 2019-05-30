/* JS Document */

/******************************

[Table of Contents]
1.Modify
*****************************/
	
function showMessages() {

		var x = document.getElementById("msgs");	
		var y = document.getElementById("inbox_people");
		var element = document.getElementById("msg_history");	
	if(document.documentElement.clientWidth <= 798){
			y.style.display = "none";
			x.style.display = "block";		
			element.scrollTop = element.scrollHeight;
		}
	}
function showContacts() {
		var x = document.getElementById("msgs");	
		var y = document.getElementById("inbox_people");	  
			x.style.display = "none";
			y.style.display = "block";
	}	


