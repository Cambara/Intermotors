<%@ include file="/WEB-INF/imports.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adiciona Funcionario</title>
</head>
<body>
	<h3>Adiciona Funcionario</h3><hr/>
	<form action="adicionafuncionario.do" method="post" class="form">
		
		<app:funcionarioForm msg="${msg}"/>
		
		<input type="submit" value="Salvar" class="btn btn-primary">
	</form>
</body>
</html>