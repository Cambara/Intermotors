package IM.br.com.model.bean.auto.manutencao;

import java.util.Date;

import IM.br.com.model.bean.auto.historico.Historico;

public class Manutencao {
	private int id;
	private Historico hist;
	private StatusManutencao statusManutencao;
	private Date data;
	private float valor;
	private String descricao;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Historico getHist() {
		return hist;
	}
	public void setHist(Historico hist) {
		this.hist = hist;
	}
	public StatusManutencao getStatusManutencao() {
		return statusManutencao;
	}
	public void setStatusManutencao(StatusManutencao statusManutencao) {
		this.statusManutencao = statusManutencao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	
}
