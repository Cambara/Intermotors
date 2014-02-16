 <%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	
	

	<form action="adicionamanutencao.do" method="post">
		<app:manutencaoFrom/>
		<br/>
		<input type="submit" value="Salvar" class="btn btn-primary">
		<br><br>
	</form>	
	
</body>
</html>