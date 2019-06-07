function showInterests() {
    var x = document.getElementById("items");
    if(x.style.display === "none")
        x.style.display = "block";
    else
        x.style.display = "none";   
}


/*********** Registration Validation ************/

function validateRegistration(){
	var username = document.reg_form.username.value;
	var name = document.reg_form.name.value;
	var surname = document.reg_form.surname.value;
	var email = document.reg_form.email.value;
	var date = document.reg_form.date.value;  
    var today = new Date();
    date = Date.parse(date);
    var datec = date;
    var tel = document.reg_form.tel.value;
    var phoneRe = /^(\+)?([ 0-9]){10,16}$/;
	var digits = tel.replace(/\D/g, "");
	var address = document.reg_form.address.value;
	var password = document.reg_form.password.value;
	var confirm_password = document.reg_form.confirm_password.value;
	document.getElementById("error").style.color="red";
	if (username==null || username==""){  
		  document.getElementById("error").innerHTML="Lo username non pu&ograve; essere vuoto!";		  
		  return false;  
	}else if (name==null || name==""){  
		  document.getElementById("error").innerHTML="Il nome non pu&ograve; essere vuoto!";		     
		  return false;  
	}else if (surname==null || surname==""){  
		document.getElementById("error").innerHTML="Il Cognome non pu&ograve; essere vuoto!";  
		  return false;  
	}else if(date=="" || date==null || (today-date) < 0){
		document.getElementById("error").innerHTML="Inserire una data valida!";
    	return false;
    }else if (email==null || email==""){  
    	document.getElementById("error").innerHTML="L'Email non pu&ograve; essere vuoto!"; 
		  return false;  
	}else if (!phoneRe.test(digits)) {
		document.getElementById("error").innerHTML="Il numero di telefono deve essere nel formato(+393123456789)!";
    	return false;
	}else if (address==null || address==""){  
		document.getElementById("error").innerHTML="L'Indirizzo non pu&ograve; essere vuoto!";  
		  return false;  
	}else if (password==null || password==""){  
		document.getElementById("error").innerHTML="La Password non pu&ograve; essere vuota!";  
		  return false;  
	}else if (confirm_password!=password){  
		document.getElementById("error").innerHTML="Le Password devono coincidere!";  
		  return false;  
	}else 
		return (true);
}