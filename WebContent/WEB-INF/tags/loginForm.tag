<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>

<%@ attribute name="login" required="false" rtexprvalue="true" type="IM.br.com.model.bean.usuario.funcionario.login.Login"%>

<label class="form-group">
	<span>Nome Usuario</span>
	<input type="text" name="nomeUsuario" class="form-control" value="${login.nome}">
</label><br/>

<label class="form-group">
	<span>Senha</span>
	<input type="text" name="senha" class="form-control" value="${login.senha}">
</label><br/>