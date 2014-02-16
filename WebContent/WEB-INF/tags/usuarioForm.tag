<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<%@ attribute name="msg" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="usuario" required="false" rtexprvalue="true" type="IM.br.com.model.bean.usuario.Usuario"%>

<c:if test="${msg != null }">
		<span class="label label-danger">${msg}</span><br/>
</c:if>	
<label class="form-group">
	<span>Email</span>
	<input type="text" name="email" class="form-control" value="${usuario.email}">
</label><br/>
<label class="form-group">
	<span>Nome:</span>
	<input type="text" name = "nome" class="form-control" value="${usuario.nome}">
</label><br/>

<label class="form-group">
	<span>CPF:</span>
	<input type="text" name="cpf" OnKeyUp="mascaraCPF(this);" maxlength="14" class="form-control" value="${usuario.cpf}">
</label><br/>

<label class="form-group">
	<span>Endereço:</span>
	<input type="text" name="endereco" class="form-control" value="${usuario.endereco}">
</label><br/>

