
<%@ include file="/WEB-INF/imports.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/scriptMascaras.js"></script>
<script type="text/javascript" src="js/validaFormReservaCarro.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h3>Adiciona Cliente</h3>
	<hr/>
	<form action="adicionacliente.do" method="post" class="form">
		<app:usuarioForm/>
		<input type="submit" value="Salvar" class="btn btn-primary">
	</form>
	
</body>
</html>