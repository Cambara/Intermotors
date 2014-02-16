package IM.br.com.model.bean.auto.manutencao;

public enum StatusManutencao {
	
	ATIVA("Ativa"), CONSERTOFINALIZADA("Conserto finalizada"), FINALIZADA("Finalizada");
	
	private String descricao;
	private StatusManutencao(String desc) {
		descricao = desc;
	}
	
	public String getChave(){ return name();}
	
	public String getDescricao(){return descricao;}
}
