package IM.br.com.model.bean.usuario.funcionario;

public enum TipoFuncionario {
	
	VENDEDOR("Vendedor"), ESTOQUISTA("Estoquista"), 
	GERENTE("Gerente"), MECANICO("Mecanico");
	
	private String descricao;
	
	TipoFuncionario(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public String getChave(){
		return name();
	}
}
