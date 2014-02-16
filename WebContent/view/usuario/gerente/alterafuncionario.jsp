<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h3>Altera  Funcionario</h3><hr/>
	<form action="editafuncionario.do" method="post" class="form">
		<input type="hidden" name="idUsuario" value="${funcionario.id}">
		<input type="hidden" name="idFunc" value="${funcionario.idFunc}">
		
		<app:funcionarioForm funcionario="${funcionario}" msg="${msg}"/>
		
		<input type="submit" value="Salvar" class="btn btn-primary">
	</form>
</body>
</html>