function validarCampos(idInput) {
	var correcto = true;
	var inp = document.getElementById(idInput);
	var regex = "";
	switch (idInput) {
		case "email":
			if (/[a-zA-Z0-9]+@[a-zA-Z0-9]+.+/.test(inp.value)) {
				inp.style.color = "white";
				inp.style.backgroundColor = "green";
			} else {
				correcto = false;
				inp.style.color = "black";
				inp.style.backgroundColor = "red";
				alert("El correo esta mal introducido, ejemplo@gmail.com");
			}
		break;

		case "password":
			if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/.test(inp.value)){
				inp.style.color = "white";
				inp.style.backgroundColor = "green";
			}else{
				correcto = false;
				inp.style.color = "black";
				inp.style.backgroundColor = "red";
				alert("La contraseña debe tener al entre 8 y 16 caracteres. Minimo una letra mayuscula, una minuscula y un simbolo");
			}
		break;

		case "usuario":
			if (/[A-Z]{1}[a-z]{3,14}$/.test(inp.value)){
				inp.style.color = "white";
				inp.style.backgroundColor = "green";
			}else{
				correcto = false;
				inp.style.color = "black";
				inp.style.backgroundColor = "red";
				alert("El nombre debe de empezar por mayuscula y debe tener entre 4 caracteres y 15");
			}
		break;
		
		case "apellido1":
		case "apellido2":
			if (/[A-Z]{1}[a-z]{3,14}$/.test(inp.value)){
				inp.style.color = "white";
				inp.style.backgroundColor = "green";
			}else{
				correcto = false;
				inp.style.color = "black";
				inp.style.backgroundColor = "orange";
				alert("El apellido debe de empezar por mayuscula y debe tener entre 4 caracteres y 15, pero no es obligatorio introducirlo");
			}
		break;
		
		case "direccion":
			if (/[a-zA-Z0-9]{1,255}/.test(inp.value)){
				inp.style.color = "white";
				inp.style.backgroundColor = "green";
			}else{
				correcto = false;
				inp.style.color = "black";
				inp.style.backgroundColor = "orange";
				alert("La direccion puede contener como minimi 1 caracter y como maximo 255 caracteres, no es obligatorio");
			}
		break;

		case "telefono":
			if (/[0-9]{9}/.test(inp.value)){
				inp.style.color = "white";
				inp.style.backgroundColor = "green";
			}else{
				correcto = false;
				inp.style.color = "black";
				inp.style.backgroundColor = "orange";
				alert("El telefono contiene 9 numeros, no es obligatorio");
			}
		break;
		
		case "dni":
			if (/^\d{8}[a-zA-Z]$/.test(inp.value)){
				var numero = inp.value.substr(0,inp.value.length-1);
		     	var letr = inp.value.substr(inp.value.length-1,1);
		     	var numero = numero % 23;
		     	var letra='TRWAGMYFPDXBNJZSQVHLCKET';
		     	var letra=letra.substring(numero,numero+1);
		    	if (letra==letr.toUpperCase()) {
		    		inp.style.color = "white";
		       		inp.style.backgroundColor = "green";		       		
		     	}else{
		     		correcto = false;		     		
					inp.style.color = "black";
		     		inp.style.backgroundColor = "red";
		       		alert('Dni erroneo, la letra del dni no se corresponde');
	    		}
			}else{
				correcto = false;
				inp.style.backgroundColor = "red";
				alert("El dni contiene 8 numeros y una letra");
			}
		break;
		
		case "emailLogin":
			if (/[a-zA-Z0-9]+@[a-zA-Z0-9]+.+/.test(inp.value)) {
				inp.style.color = "white";
				inp.style.backgroundColor = "green";
				document.getElementById("p1").innerHTML = "";
			} else {
				correcto = false;
				inp.style.color = "black";
				document.getElementById("p1").innerHTML = "El correo esta mal introducido, ejemplo@gmail.com";
				document.getElementById("p1").style.color = "white";
			}
		break;

		case "passwordLogin":
			if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/.test(inp.value)){
				inp.style.color = "white";
				inp.style.backgroundColor = "green";
				document.getElementById("p2").innerHTML = "";
			}else{
				correcto = false;
				inp.style.color = "black";
				document.getElementById("p2").innerHTML = "La contraseña debe tener al entre 8 y 16 caracteres. Minimo una letra mayuscula, una minuscula y un simbolo";
				document.getElementById("p2").style.color = "white";
			}
		break;
	}
	if(!correcto){
		document.getElementById("btnRegistro").disabled=true;
	}else{
		document.getElementById("btnRegistro").disabled=false;
	}
}

function filtrarMunicipios() {
	var provincias = document.getElementById("provincias").selectedIndex;
	var municipios = document.getElementById("municipios");
	for (var i = 0; i < municipios.length; i++) {
		var opcion = municipios[i].id;
		if (provincias < 10) {
			if (provincias == opcion.substring(0, 1) && opcion < 10000) {
				municipios[i].classList.remove("hidden");
			} else {
				municipios[i].classList.add("hidden");
			}
		} else {
			if (provincias == opcion.substring(0, 2) && opcion >= 10000) {
				municipios[i].classList.remove("hidden");
			} else {
				municipios[i].classList.add("hidden");
			}
		}
	}
}
