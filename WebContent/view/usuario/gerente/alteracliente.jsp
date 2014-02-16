<%@ include file="/WEB-INF/imports.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Altera Cliente</title>
</head>
<body>

<h3>Altera Cliente - ${cliente.nome}</h3>
<form action="editacliente.do" method="post" class="form">
	<input name="id" type="hidden" value="${cliente.id}">
	<app:usuarioForm usuario="${cliente}" msg="${msg}"/>
	<input type="submit" value="Alterar" class="btn btn-primary">
</form>

</body>
</html>