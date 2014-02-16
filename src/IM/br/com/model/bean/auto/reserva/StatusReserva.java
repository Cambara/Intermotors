package IM.br.com.model.bean.auto.reserva;

public enum StatusReserva {
	ATIVA("Ativa"), FINALIZADA("Finalizada"), CANCELADA("Cancelada");
	
	private String descricao;
	private StatusReserva(String desc) {
		descricao = desc;
	}
	
	public String getChave(){ return name();}
	
	public String getDescricao(){return descricao;}
}
