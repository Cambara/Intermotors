<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/imports.jsp"%>
<%@ attribute name="funcionario" required="false" rtexprvalue="true" type="IM.br.com.model.bean.usuario.funcionario.Funcionario"%>


<label>Nome </label> <span>${funcionario.nome}</span><br/>
<label>CPF </label> <span>${funcionario.cpf}</span><br/>
<label>E-mail </label> <span>${funcionario.email}</span><br/>
<label>Cargo </label> <span>${funcionario.tipo.descricao}</span><br/>
<label>Endereco </label> <span>${funcionario.endereco}</span><br/>
