function validarForm(){
	
	var cpf   = document.forms[0].cpf.value;
	
	
	if(cpf.length < 14){
		alert("CPF Invalida");
		document.forms[0].cpf.focus();
		return false;
	}
	return true;
}