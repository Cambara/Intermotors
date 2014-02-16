<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	
	<h3>Lista Reserva</h3><hr>
	<div class="col-md-8 col-md-offset-2">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Placa</th>
				<th>Modelo</th>
				<th>Marca</th>
				<th>Ano</th>
				<th>Opção</th> 
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${reservas}" var="reserva">
				<c:if test="${reserva.statusReserva == 'ATIVA' }">
				<tr>
					<td>${reserva.hist.auto.placa}</td>
					<td>${reserva.hist.auto.spec.modelo}</td>
					<td>${reserva.hist.auto.spec.marca.descricao}</td>
					<td>${reserva.hist.auto.spec.ano}</td>
					<td>
						<a href="buscareserva.do?id=${reserva.id}" title="Finaliza reserva"><span class="glyphicon glyphicon-bookmark" ></span></a>
					</td>
				
				</tr>
				</c:if>
			</c:forEach>
			
		</tbody>
	</table>
	</div>
	
	

	

</body>
</html>