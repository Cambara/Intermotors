<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
	
	<h3 class="clol-md-3 col-md-offset-1">Carros</h3><hr/>
	<div class="col-md-8 col-md-offset-2">
	<table class="table table-hover ">
	
		<thead>
			<tr>
				<th> </th>
				<th>Id</th>
				<th>Placa</th>
				<th>Modelo</th>
				<th>Marca</th>
				<th>KM</th>
				<th>ano</th>
				<th>Opção</th>
			</tr>
		</thead>
				
		<tbody>
		<c:forEach var="carro" items="${carros}">

			<c:if test="${carro.status.chave eq 'VENDA'  }">	
				<tr>
					<td><img alt="" src="buscaimagem.img?fotolink=${carro.imagem.caminhoThumb}"></td>
					<td>${carro.idAuto}</td> 
					<td>${carro.placa}</td>
					<td>${carro.spec.modelo}</td> 
					<td>${carro.spec.marca.descricao}</td>
					<td>${carro.km}</td>
					<td>${carro.spec.ano}</td>
					<td>
						<a href="vendecarro.do?id=${carro.idAuto}" title="Comprar"><span class="glyphicon glyphicon-shopping-cart" ></span></a> 
						<a href="buscacarroreserva.do?id=${carro.idAuto}" title="Reserva"><span class="glyphicon glyphicon-bookmark" ></span></a>
					</td>
				</tr>			
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>