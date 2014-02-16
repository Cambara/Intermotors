<%@ tag language="java" pageEncoding="UTF-8" import="IM.br.com.model.bean.auto.Marca"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<%@ attribute name="carro" required="false" rtexprvalue="true" type="IM.br.com.model.bean.auto.carro.Carro"%>
<%@ attribute name="msg" required="false" rtexprvalue="true" type="java.lang.String"%>



<fieldset>
	<input type="hidden" name="id" value="${carro.idAuto}">
	<c:if test="${msg != null }">
		<span class="label label-danger">${msg}</span>
	</c:if>	
	<div class="form-group">
		<label ><span>Placa</span> 
		<input name="placa" value="${carro.placa}" class="form-control" onkeyup="mascaraPlaca(this);" maxlength="8" required >
		</label>
	</div>
	<br/>
	<div class="form-group">
		<label ><span>Valor</span>
		<input name="valor" class="form-control" value="${carro.valor}" type="text" required>
		</label>
	</div>
	<br/>
	<div >
	<label>KM
	<input name="km" type="text" class = "form-control" value="${carro.km}" required>
	</label>
	</div>
	<br/>
	<div class="form-group">
	<label > <span>Modelo</span>
	<input name="modelo" class = "form-control" type="text" value="${carro.spec.modelo }" required>
	</label>
	</div>
	<br/>
	<div class="form-group ">
	<label ><span>Marca</span>
		
		<c:set var="marcas" value="<%=Marca.values() %>" />
		<select name="marca" class="form-control">
		<c:if test="${carro != null }">
			<option value = "${carro.spec.marca.chave}"> ${carro.spec.marca.descricao} </option>
		</c:if>
		<c:forEach var="marca" items="${marcas}">
			<option value="${marca.chave }"> ${marca.descricao }</option>
			
		</c:forEach>
		</select>
	</label>
	</div>
	<br/>
	<div class="form-group">
	<label ><span>Ano</span>
	<input name="ano" class="form-control" type="text" maxlength="10" value="${carro.spec.ano }">
	</label>
	</div>
	<br/>
	<div class="form-group">
	<label ><span>Cor</span>
	<input name="cor" class="form-control" value="${carro.spec.cor}" type="text" required>
	</label>
	</div>
	<br/>
	<div class="class="col-md-8"">
	<label><strong>Adicionais</strong></label>
	<div class="checkbox">
	<table class="col-md-6">
		<tr>
			<td><label><input type="checkbox" value="Air Bag" name="adicionais">Air Bag</label></td>
			<td><label><input type="checkbox" value="ABS" name="adicionais">ABS</label></td>
			<td><label><input type="checkbox" value="Alarme" name="adicionais">Alarme</label></td>
		</tr>
		<tr>
			<td><label><input type="checkbox" value="Ar Condicionado" name="adicionais">Ar Condicionado</label></td>
			<td><label><input type="checkbox" value="CD Player" name="adicionais">CD Player</label></td>
			<td><label><input type="checkbox" value="Direção Hidráulica" name="adicionais">Direção Hidráulica</label></td>
		</tr>
		<tr>
			<td><label><input type="checkbox" value="Travas Elétricas" name="adicionais">Travas Elétricas</label></td>
			<td><label><input type="checkbox" value="Vidros Elétricos" name="adicionais">Vidros Elétricos</label></td>
		</tr>
	</table>
	</div>
	</div>
	<br/><br/><br/>
	
</fieldset>
		