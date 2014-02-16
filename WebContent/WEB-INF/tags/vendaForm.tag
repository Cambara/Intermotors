<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp" %>
<%@ attribute name="carro" required="false" rtexprvalue="true" type="IM.br.com.model.bean.auto.Automovel"%>
<%@ attribute name="cliente" required="false" rtexprvalue="true" type="IM.br.com.model.bean.usuario.Usuario"%>
<%@ attribute name="sinal" required="false" rtexprvalue="true" type="java.lang.Float"%>

<div class="left">
		<input type="hidden" name="valorTotal" value="${carro.valor - sinal}">
		<input type="hidden" name="idCarro" value="${carro.idAuto}">
	<label >
		Placa
	</label>
	<span>${carro.placa}</span>
	<br/>
	
	<label >
		Modelo
	</label>
	<span >${carro.spec.modelo }</span>
	<br/>
	
	<label >
		Marca
	</label>
	<span >${carro.spec.marca.descricao}</span>
	<br/>
	
	<label >
		Ano
	</label>
	<span >${carro.spec.ano}</span>
	<br/>
	
	<label >
		KM
	</label>
	<span >${carro.km }</span>
	<br/>
	
	<label >
		Cor
	</label>
	<span >${carro.spec.cor}</span>
	<br/>
	
	<label >
		Adicionais
	</label>
	<span >${carro.spec.adicionais}</span>
	<br/>
	
	<label >
		Preço
	</label>
	<span >${carro.valor}</span>
	<br/>
	<c:if test="${ cliente != null}">
		<label >
			Preço Total
		</label>
		<span >${carro.valor - sinal}</span>
		<br/>
	</c:if>
	<label class="form-group">
		<span>Número Prestações</span>
		<input  required="required" type="number" name="nPrestacoes" class="form-control">
	</label><br/>
	<label class="form-group">
		<span>Cpf</span>
		<input required="required" OnKeyUp="mascaraCPF(this);" maxlength="14"  placeholder="CPF Cliente" name="cpf" value="${cliente.cpf}" class="form-control">
	</label><br/>
	
	<input type="submit" class="btn btn-primary" value="Comprar">
	<a href="listaCarro" class="btn btn-danger">Cancelar</a>
</div>

<div class="left">
	<img id="carroFoto" alt="" src="buscaimagem.img?fotolink=${carro.imagem.caminhoImg}" class="img-rounded">
</div>
