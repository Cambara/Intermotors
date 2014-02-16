<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp" %>
<%@ attribute name="manutencao" required="false" rtexprvalue="true" type="IM.br.com.model.bean.auto.manutencao.Manutencao"%>

<jsp:useBean id="auxiliaInterface" class="IM.br.com.model.auxilia.AuxiliaInterface"></jsp:useBean>

<c:if test="${msg != null }">
		<span class="label label-danger">${msg}</span>
	</c:if>	
<label class="form-group">
	<span>CPF</span>
	<input type="text" OnKeyUp="mascaraCPF(this);" maxlength="14" name="cpf" class="form-control">
</label><br/>

<label class="form-group">
	<span>Placa</span>
	<input type="text" OnKeyUp="mascaraPlaca(this);" maxlength="8" name="placa" class="form-control">
</label><br/>

<label class="form-group">
	<span>Mecanico</span>
	
	<select class="form-control" name="idMecanico">
		<c:if test="${manutencao != null }">
			<option value="${manutencao.hist.func.idFunc}">${manutencao.hist.func.nome}</option>
		</c:if>
		<c:forEach items="${auxiliaInterface.mecanicos}" var="mecanico"> 
			<option value="${mecanico.idFunc}">${mecanico.nome}</option>
		</c:forEach>
		
	</select>
</label>