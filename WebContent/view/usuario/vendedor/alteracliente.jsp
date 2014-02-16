<%@ include file="/WEB-INF/imports.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript" src="js/scriptMascaras.js"></script>
<script type="text/javascript" src="js/validaFormReservaCarro.js"></script>
<title>Altera Cliente</title>
</head>
<body>

<h3>Altera Cliente - ${cliente.nome}</h3>
<form action="editacliente.do" method="post" class="form">
	<input name="id" type="hidden" value="${cliente.id}">
	<app:usuarioForm usuario="${cliente}"/>
	<input type="submit" value="Alterar" class="btn btn-primary">
</form>

</body>
</html>