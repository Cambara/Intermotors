<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<link href="../css/headerMenu.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/indexCss.css"> 
	<script type="text/javascript" src="../js/scriptMascaras.js"></script>
	
	<!-- 	Bootstrap     -->
	<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	
				<form action="login.do" method="post">
					<label class="form-group">
						<span>Login</span>
						<input type="text" name="nomeUsuario" class="form-control">
					</label>
					<br/>
					<label class="form-group">
						<span>Senha</span>
						<input type="password" name="senha" class="form-control">
					</label>
					<br/>
					
					<input type="submit" value="logar" class="btn btn-primary">
				</form>
	    	
		   
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
	
	
</body>
</html>