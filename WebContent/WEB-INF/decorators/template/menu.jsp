<%@ include file="/WEB-INF/imports.jsp" %>





<c:if test="${funcionarioLogado.tipo.chave == 'ESTOQUISTA'}">
	<jsp:include page="menuEstoquista.jsp" />
</c:if>
<c:if test="${funcionarioLogado.tipo.chave == 'GERENTE'}">
	<jsp:include page="menuGerente.jsp" />
</c:if>
<c:if test="${funcionarioLogado.tipo.chave == 'MECANICO'}">
	<jsp:include page="menuMecanico.jsp" />
</c:if>
<c:if test="${funcionarioLogado.tipo.chave == 'VENDEDOR'}">
	<jsp:include page="menuVendedor.jsp" />
</c:if>
