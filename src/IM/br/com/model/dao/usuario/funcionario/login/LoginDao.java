package IM.br.com.model.dao.usuario.funcionario.login;

import java.sql.ResultSet;
import java.sql.SQLException;

import IM.br.com.model.bean.usuario.funcionario.login.Login;

public interface LoginDao {
	
	boolean adicionar(Login login);
	boolean alterar(Login login);
	Login buscarId(int id);
	Login buscarNomeLogin(String nomeLogin);
	Login criarLogin(ResultSet rs) throws SQLException;
	
}
