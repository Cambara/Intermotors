<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html>
<html>
<head>
    
<!--     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!--     <meta name="viewport" view="width=device-width, initial-scale=1.0"> -->
<!--     <meta http-equiv="Content-Type" view="text/html; charset=utf-8" /> -->
<!--     <meta http-equiv="Content-Language" view="pt-BR" /> -->
    
    <link href="../../../css/headerMenu.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../../css/indexCss.css"> 
	<script type="text/javascript" src="../../../js/scriptMascaras.js"></script>
	
	<!-- 	Bootstrap     -->
	<link href="../../../bootstrap/css/bootstrap.css" rel="stylesheet">
    <title><sitemesh:write property="title" /></title>
   	 
    <sitemesh:write property="head" />
</head>
<body>
	
	<div id=""class="row containerMain">
		<div class="col-md-10 col-md-offset-1" >
			<c:if test="${funcionarioLogado !=  null}">
		    	<jsp:include page="template/header.jsp" />
			</c:if>
			<c:if test="${funcionarioLogado == null}">
		    	<jsp:include page="template/headerLogin.jsp" />
			</c:if>
		
		    
		    
		    <div class="containerB">
		    	<sitemesh:write property="body" />
		    </div>
		    <jsp:include page="template/footer.jsp"/>
	    </div>
    </div>
    <script type="text/javascript" src="../../../js/jquery-1.10.0.min.js"></script>
<!--     <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script> -->
    <script src="../../../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>