<%@ tag language="java" pageEncoding="UTF-8" import="IM.br.com.model.bean.usuario.funcionario.TipoFuncionario"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<%@ attribute name="msg" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="funcionario" required="false" rtexprvalue="true" type="IM.br.com.model.bean.usuario.funcionario.Funcionario"%>

<app:loginForm login="${funcionario.login}"/>
<label class="form-group">
	<span>Tipo</span>
	
	<c:set var="tipos" value="<%= TipoFuncionario.values() %>"/>
	<select name="tipo" class="form-control">
		<c:if test="${funcionario != null }">
			<option value="${funcionario.tipo.chave}"> ${funcionario.tipo.descricao} </option>
		</c:if> 
		<c:forEach var="tipo" items="${tipos}">
			<option value="${tipo.chave}">${tipo.descricao}</option>
		</c:forEach>
	</select>
	 
</label><br/>

<label class="form-group">
	<span>Salario</span>
	<input type="text" name="salario" class="form-control" value="${funcionario.salario}">
</label><br/>

<app:usuarioForm usuario="${funcionario}" msg="${msg}"/>