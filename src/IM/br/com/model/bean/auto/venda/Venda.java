package IM.br.com.model.bean.auto.venda;

import java.util.Date;

import IM.br.com.model.bean.auto.historico.Historico;

public class Venda {
	
	private int id;
	private Historico hist;
	private int nPrestacoes;
	private Double valor;
	private Date data;
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
	public int getnPrestacoes() {
		return nPrestacoes;
	}
	public void setnPrestacoes(int nPrestacoes) {
		this.nPrestacoes = nPrestacoes;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	

}
