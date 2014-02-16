
<%@ include file="/WEB-INF/imports.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Alterar Cadastro de Carros</title>

</head>
<body>
		<div class="form">
			<form  action="editaCarro.do" method="POST">
					
					<fieldset>
						<legend><h3>Alterar Cadastro de Carros</h3></legend>
						<div class = "left">
						<app:carroForm carro="${carro}" msg="${msg}"></app:carroForm>
						<input class="btn btn-primary" type="submit" value="Confirmar" >
						<input class="btn btn-default" type="reset" value="Limpar" >			
						</div>
						<div>
							<img id="carroFotoReserva" alt="" src="buscaimagem.img?fotolink=${carro.imagem.caminhoImg}" class="img-rounded">
						</div>
						
					</fieldset>
			</form>
		</div>
</body>
</html>