package IM.br.com.model.bean.auto.historico;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;

public class Historico {
	private int id;
	private Automovel auto;
	private Usuario cliente;  
	private Funcionario func; 

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Automovel getAuto() {
		return auto;
	}

	public void setAuto(Automovel auto) {
		this.auto = auto;
	}

}
