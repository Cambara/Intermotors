<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	
	<h3>Lista Manutenção</h3><hr>
	<div class="col-md-8 col-md-offset-2">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Placa</th>
				<th>Modelo</th>
				<th>Marca</th>
				<th>Status</th>
				<th>Opção</th> 
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${manutencoes}" var="manutencao">
				<c:if test="${manutencao.statusManutencao == 'CONSERTOFINALIZADA' }">
				<tr>
					<td>${manutencao.hist.auto.placa}</td>
					<td>${manutencao.hist.auto.spec.modelo}</td>
					<td>${manutencao.hist.auto.spec.marca.descricao}</td>
					<td>${manutencao.statusManutencao.descricao}</td>
					<td>
						<a href="buscamanutencao.do?id=${manutencao.id}" title="finaliza"><span class="glyphicon glyphicon-bookmark" ></span></a>
					</td>
					
				</tr>
				</c:if>
			</c:forEach>
			
		</tbody>
	</table>
	</div>
	
	
</body>
</html>