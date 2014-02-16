<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reserva Carro</title>
<link rel="stylesheet" type="text/css" href="css/formscss.css">
<script type="text/javascript" src="js/scriptMascaras.js"></script>
<script type="text/javascript" src="js/validaFormReservaCarro.js"></script>
</head>
<body>
	<div class="form">
	<form action="adicionareserva.do" method="POST" class="form-inline">
		<fieldset>
			<legend>Reserva Carro</legend>
			<input type="hidden" name="idCarro" value="${carro.idAuto}">
			<div class="left">
			<label>Placa</label>  ${carro.placa }<br/>
			<label>Preço</label>  ${carro.valor }<br/>
			<label>Modelo</label> ${carro.spec.modelo }<br/>
			<label>Marca</label>  ${carro.spec.marca.descricao}<br/>
			<label>Ano</label>    ${carro.spec.ano }<br/>
			<label>KM</label>     ${carro.km}<br/>
			<div class="form-group">
				<label><span>CPF</span> 
					<input type="text" name="cpf" OnKeyUp="mascaraCPF(this);" placeholder="CPF do Cliente" maxlength="14" class="form-control" required>
				</label> 
			</div>
			<br/>
			<div class="form-group">
				<label><span>Sinal</span> 
				<input type="text" name="sinal"  placeholder="Valor de Sinal" class="form-control" required>
				</label>
			</div>
			<br/><br/>
			<input type="submit" class="btn btn-primary" onclick="return validarForm();" value="Confirmar">
			<a href="listaCarro"  class="btn btn-danger">Cancelar</a>
			</div>
			<div class="left">
				<img id="carroFotoReserva" alt="" src="buscaimagem.img?fotolink=${carro.imagem.caminhoImg}" class="img-rounded">
			</div> 
			
		</fieldset>
	</form>
	</div>
</body>
</html>