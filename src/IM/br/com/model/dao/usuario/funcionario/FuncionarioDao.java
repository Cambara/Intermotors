package IM.br.com.model.dao.usuario.funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.bean.usuario.funcionario.TipoFuncionario;

public interface FuncionarioDao {
	
	boolean adicionar(Funcionario funcionario);
	boolean alterar(Funcionario funcionario);
	Funcionario buscarId(int id);
	List<Funcionario> buscarTipo(TipoFuncionario tipo);
	List<Funcionario> listar();
	Funcionario criarFuncionario(ResultSet rs) throws SQLException;
}
