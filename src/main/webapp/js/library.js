/* JS Document */

/*******************************************************************************
 * 
 * [Table of Contents] 1.Modify
 ******************************************************************************/
function deleteProduct(product_id){
	var xhr = new XMLHttpRequest();
	var url = "url";
	xhr.open("GET", "url?product=" + product_id, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);
			var element=document.getElementById(product_id);
			element.parentNode.removeChild(element);
		}
	};
	xhr.send(null);
	
}
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


/** ********** Modify Product Validation ************ */
/*function validateModify(){
	var price = document.modify_product_form.price.value;
	var priceRe = /^\d+(?:[.,]\d+)*$/;
	if(price!= null && price!=""){
	if (!priceRe.test(price)) {
		document.getElementById("modify_error").innerHTML = "Inserisci un prezzo valido!";
		document.getElementById("modify_error").style.color="red";
		return false;
		}
	}else
		return (true);
}*/
/** ************* Modify Product JSON *************** */
// Sending and receiving data in JSON format using POST method//
function modify() {
	var title = document.modify_product_form.title.value;
	var price = document.modify_product_form.price.value;
	var description = document.modify_product_form.price.value;
	var priceRe = /^\d+(?:[.,]\d+)*$/;
	if(price!= null && price!=""){
	if (!priceRe.test(price)) {
		document.getElementById("modify_error").innerHTML = "Inserisci un prezzo valido!";
		document.getElementById("modify_error").style.color="red";
		return false;
		}
	}
	else
	{
		return true;
	}
}

/*************** Insert Product Validation + JSON ****************/
function insert() {
	var image = document.insert_product_form.image.value;
	var title = document.insert_product_form.title.value;
	var price = document.insert_product_form.price.value;
	var authors = document.insert_product_form.authors.value;
	var date = document.insert_product_form.releaseDate.value;
	var category = document.insert_product_form.interest.value;
	var description = document.insert_product_form.description.value;
	 var today = new Date();
	 date = Date.parse(date);
	 var datev = date;
	 
	var priceRe = /^\d+(?:[.,]\d+)*$/;
	document.getElementById("insert_error").style.color="red";
	
	if(image == null || image==""){
		document.getElementById("insert_error").innerHTML = "Inserisci una Immagine valida!";
		return false;
	}else if(title == null || title==""){
		document.getElementById("insert_error").innerHTML = "Il titolo non pu&ograve; essere vuoto!";
		return false;
	}else if(price == null || price==0 || !priceRe.test(price)){
		document.getElementById("insert_error").innerHTML = "Inserisci un prezzo valido!";
		return false;
	}else if(authors == null || authors==""){
		document.getElementById("insert_error").innerHTML = "Il campo autori non pu&ograve; essere vuoto!";
		return false;
	}else if(date=="" || date==null || date==undefined || (today-datev) < 0){
		document.getElementById("insert_error").innerHTML="Inserire una data valida!";
    	return false;
    }else if(category == null || category==""){
		document.getElementById("insert_error").innerHTML = "Seleziona una Categoria!";
		return false;
	}else if(description == null || description==""){
		document.getElementById("insert_error").innerHTML = "Il campo descrizione non pu&ograve; essere vuoto!";
		return false;
	}
	else
	{
		return true;
	}
}