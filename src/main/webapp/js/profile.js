/* JS Document */

/*******************************************************************************
 * 
 * [Table of Contents] 1.Modify
 ******************************************************************************/

function showModify() {
	var x = document.getElementById("modify");
	if (x.style.display === "none") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}

function showInterests() {
	var x = document.getElementById("items");
	if (x.style.display === "none")
		x.style.display = "block";
	else
		x.style.display = "none";
}

/** *********** Validation ************* */
function modify() {			
	var email = document.modify_form.email.value;
	var tel = document.modify_form.tel.value;
	var address = document.modify_form.address.value;
	var password = document.modify_form.passwordVecchia.value;
	var new_password = document.modify_form.passwordNuova.value;
	var confirm_new_password = document.modify_form.confirm_new_password.value;
	var phoneRe = /^(\+)?([ 0-9]){10,16}$/;
	var digits = tel.replace(/\D/g,"");
	
	document.getElementById("error").style.color="red";

	if(tel!=null && tel!="" && tel!=undefined){
	if (!phoneRe.test(digits)) {
		document.getElementById("error").innerHTML = "Il numero di Telefono deve essere nella forma (+393123456789).";
		return false;
	}}
	if((password==null || password=="" || password==undefined) && (new_password!=null || new_password!="") || new_password==undefined){
		document.getElementById("error").innerHTML = "Inserire la Vecchia Password!";
		return false;
	}else if (confirm_new_password!=new_password){  
		document.getElementById("error").innerHTML = "Le Password devono coincidere!";
		return false;  
	}else{
		return true;
	}
}

