<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<c:if test="${msg != null }">
		<span class="label label-danger">${msg}</span>
</c:if>

<hr>
<app:perfilFuncionario funcionario="${funcionarioLogado}"/>
</body>
</html>