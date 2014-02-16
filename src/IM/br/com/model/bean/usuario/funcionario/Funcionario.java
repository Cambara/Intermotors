package IM.br.com.model.bean.usuario.funcionario;

import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.login.Login;

public class Funcionario extends Usuario{

	private int idFunc;
	private double salario;
	private TipoFuncionario tipo;
	private Login login;
	
	public int getIdFunc() {
		return idFunc;
	}
	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public TipoFuncionario getTipo() {
		return tipo;
	}
	public void setTipo(TipoFuncionario tipo) {
		this.tipo = tipo;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	
}
