<%@page import="IM.br.com.model.bean.auto.Marca"%>
<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Carro</title>
</head>
<body>
		<div class="form">
			<form  action="adicionarcarro" method="POST">
				<legend><h3>Cadastro de Carros</h3></legend>
				<app:carroForm msg="${msg}"></app:carroForm>
				
				<input class="btn btn-primary" type="submit" value="Confirmar" >
				<input class="btn btn-default" type="reset" value="Limpar" >			
			</form>
		</div>
</body>
</html>