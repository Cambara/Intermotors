<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	
	<h3>Manutencao Cliente - ${manutencao.hist.cliente.nome }</h3>
	<form action="editamanutencao.do" method="post" class="col-md-8 col-md-offset-1">
		<input type="hidden" name="id" value="${manutencao.id}">
		<input type="hidden" name="status" value="CONSERTOFINALIZADA">
		<div class="left">
		<label class="form-group">
			<span>Placa</span>
			<span class="form-control">${manutencao.hist.auto.placa}</span>
		</label><br/>
		
		<label class="form-group">
			<span>Modelo</span>
			<span class="form-control">${manutencao.hist.auto.spec.modelo }</span>
		</label><br/>
		
		<label class="form-group">
			<span>Marca</span>
			<span class="form-control">${manutencao.hist.auto.spec.marca} </span>
		</label><br/>
		
		<label class="form-group">
			<span>Status</span>
			<span class="form-control">${manutencao.statusManutencao.descricao}</span>
		</label><br/>
		
		<label class="form-group">
			<span>Descricao</span>
			<textarea rows="3" cols="" name="descricao" class="form-control"></textarea>
		</label><br/>
		
				
		<input type="submit" value="Finalizar" class="btn btn-sucess">
		</div>
		<div >
			<img id="carroFotoReserva" alt="" src="buscaimagem.img?fotolink=${manutencao.hist.auto.imagem.caminhoImg}" class="img-rounded">
		</div>
		
		<br/><br/><br/>
	</form>
</body>
</html>