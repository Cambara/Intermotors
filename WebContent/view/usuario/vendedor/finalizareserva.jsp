<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<div class="form">
	<form action="alterareserva.do" method="POST" class="form-inline">
		<fieldset>
			<legend>Reserva Carro</legend>
			<input type="hidden" name="id" value="${reserva.id}">
			<input type="hidden" name="statusReserva" value="FINALIZADA">
			<div class="left">
			<label>Placa</label>  ${reserva.hist.auto.placa }<br/>
			<label>Preço</label>  ${reserva.hist.auto.valor }<br/>
			<label>Modelo</label> ${reserva.hist.auto.spec.modelo }<br/>
			<label>Marca</label>  ${reserva.hist.auto.spec.marca.descricao}<br/>
			<label>Ano</label>    ${reserva.hist.auto.spec.ano }<br/>
			<label>KM</label>     ${reserva.hist.auto.km}<br/>
			<label>Nome</label>   ${reserva.hist.cliente.nome}<br/>
			<label>CPF</label>    ${reserva.hist.cliente.cpf}<br/>
			<label>Sinal</label>  ${reserva.valorSinal}<br/>
			
			
			<br/>
			<input type="submit" class="btn btn-primary" value="Confirmar Venda">
			
			</div>
			<div class="left">
				<img id="carroFotoReserva" alt="" src="buscaimagem.img?fotolink=${reserva.hist.auto.imagem.caminhoImg}" class="img-rounded">
			</div> 
			
		</fieldset>
	</form>
	<br/>
	<form action="alterareserva.do" method="post">
		<input type="hidden" name="id" value="${reserva.id}">
		<input type="hidden" name="statusReserva" value="CANCELADA">
		<input type="submit" class="btn btn-danger" value="Cancelar Reserva">
	</form>
	
	</div>


</body>
</html>