

function validarForm(){
	var placa  = document.forms[0].placa.value;
	var ano   = document.forms[0].ano.value;
	
	
	
	
	if(placa.length < 8){
		alert("Placa Invalida");
		document.forms[0].placa.focus();
		return false;
	}
	
	if(ano.length < 10){
		alert("Data Invalida");
		document.forms[0].ano.focus();
		return false;
	}
	
	
	return true;
	
}
