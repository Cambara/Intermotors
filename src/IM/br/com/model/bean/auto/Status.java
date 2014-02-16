package IM.br.com.model.bean.auto;

public enum Status {
	VENDA("Venda"), VENDIDO("Vendido"), RESERVADO("Reservado"), MANUTENCAO("Manutenção");
	
	private String descricao;
	Status(String desc){
		descricao = desc;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public String getChave(){
		return name();
	}
	
			
	
	
}
