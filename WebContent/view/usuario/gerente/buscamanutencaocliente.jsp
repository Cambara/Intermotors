<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/scriptMascaras.js"></script>
<title>Insert title here</title>
</head>
<body>
	
	
	<form action="listamanutencaocliente.do" method="get">
		<label class="form-group">
			<span>CPF</span>
			<input type="text" name="cpf" OnKeyUp="mascaraCPF(this);" maxlength="14" class="form-control">
		</label><br/>
		
		<input type="submit" value="Buscar" class="btn btn-primary">
		
		
		
	</form>
</body>
</html>