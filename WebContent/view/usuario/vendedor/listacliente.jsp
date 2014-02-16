<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>

	<h3>Lista de Clientes</h3>
	<hr/>
	<div class="col-md-8 col-md-offset-2">
	<table class="table table-hover ">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>CPF</th>
				<th>E-mail</th>
				<th>Endereço</th>
				<th>Opção</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="cliente" items="${clientes}">
			<tr>
				<td>${cliente.id}</td>
				<td>${cliente.nome}</td>
				<td>${cliente.cpf}</td>
				<td>${cliente.email}</td>
				<td>${cliente.endereco}</td>
				<td>
				<a href="editacliente.do?id=${cliente.id}" title="Editar"><span class="glyphicon glyphicon-edit" ></span></a> 
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>