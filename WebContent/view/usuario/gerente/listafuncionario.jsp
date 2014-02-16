<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<h3>Lista de Funcionarios</h3>
	<hr/>
	<div class="col-md-8 col-md-offset-2">
	<table class="table table-hover ">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>CPF</th>
				<th>E-mail</th>
				<th>Tipo</th>
				<th>Opção</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="funcionario" items="${funcionarios}">
			<tr>
				<td>${funcionario.idFunc}</td>
				<td>${funcionario.nome}</td>
				<td>${funcionario.cpf}</td>
				<td>${funcionario.email}</td>
				<td>${funcionario.tipo}</td>
				<td>
				<a href="editafuncionario.do?id=${funcionario.idFunc}" title="Editar"><span class="glyphicon glyphicon-edit" ></span></a> 
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>