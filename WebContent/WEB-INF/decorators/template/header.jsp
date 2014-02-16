	<div  class="row">
		
		<div class="col-md-4">
			<div class="row">
				<div style="float: right;">
					<img src="../../../img/golf3.jpg">
				</div>
			</div>
		</div>
		<div class="col-md-8" style="height: 145px;">
			
			<div class="row conf" >

				<div class="conf-txt" >
					
					<a href="index.jsp">${funcionarioLogado.nome}</a>
					<a href="login.out">Sair</a>
				</div>
				<div>
					<a href="#" id="title">Intermotors</a>
				</div>
			
			</div>
			<!-- Logica que vai pegar o tipo do usuario e colocar o menu dele -->
			<div class = "row">
			<jsp:include page="menu.jsp" />
			</div>	
		</div>
	</div>
	<div class="row">
 	<div id="line" class="col-md-12">	</div> 
	</div>
