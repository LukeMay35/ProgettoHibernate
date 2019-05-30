/* JS Document */

/******************************

[Table of Contents]
1.Modify
*****************************/
	
function showModifyProduct() {
		var x = document.getElementById("modifyproduct");		  
		if (x.style.display === "none") {
			x.style.display = "block";
		}
		else {
			x.style.display = "none";
		}
	}

function showInsertProduct(){
		var y = document.getElementById("insertproduct");
		if (y.style.display === "none") {
			y.style.display = "block";
		}
		else {
			y.style.display = "none";
		}
	}

function showInterests() {
		var x = document.getElementById("items");
		if(x.style.display === "none")
			x.style.display = "block";
		else
			x.style.display = "none";   
	}
