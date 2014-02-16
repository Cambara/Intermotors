function mascaraData(campoData){
    var ano = campoData.value;
    if(ano.length == 2 || ano.length == 5){
      ano = ano + '/';
      document.forms[0].ano.value = ano;
      return true;              
    }
   
}

function mascaraPlaca(campoPlaca){

  var placa = campoPlaca.value;

  if(placa.length == 3){
    placa = placa + '-';
    document.forms[0].placa.value = placa;
    return true;

  }
  
  
}

function mascaraCPF(campoCPF){

	  var cpf = campoCPF.value;

	  if(cpf.length == 3 || cpf.length == 7){
	    cpf = cpf + '.';
	    document.forms[0].cpf.value = cpf;
	    return true;

	  }
	  if(cpf.length == 11){
		  cpf = cpf + '-';
		  document.forms[0].cpf.value = cpf;
	  }
}


