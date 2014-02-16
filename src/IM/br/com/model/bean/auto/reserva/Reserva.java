package IM.br.com.model.bean.auto.reserva;

import java.util.Date;

import IM.br.com.model.bean.auto.historico.Historico;

public class Reserva {
	private int id;
	private float valorSinal;
	private Historico hist;
	private StatusReserva statusReserva;
	private Date data;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getValorSinal() {
		return valorSinal;
	}
	public void setValorSinal(float valorSinal) {
		this.valorSinal = valorSinal;
	}
	public Historico getHist() {
		return hist;
	}
	public void setHist(Historico hist) {
		this.hist = hist;
	}
	public StatusReserva getStatusReserva() {
		return statusReserva;
	}
	public void setStatusReserva(StatusReserva statusReserva) {
		this.statusReserva = statusReserva;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	
	
	 
}
