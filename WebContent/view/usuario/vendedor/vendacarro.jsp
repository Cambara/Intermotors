<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/scriptMascaras.js"></script>
<title>Insert title here</title>
</head>
<body>

	<h3>Venda </h3>
	
	
		<form action="vendecarro.do" method="post">
			<app:vendaForm carro="${carro}" cliente="${cliente }" sinal="${sinal }"></app:vendaForm>
		</form>

</body>
</html>