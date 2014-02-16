package IM.br.com.model.dao.auto.carro.manutencao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import IM.br.com.model.bean.auto.manutencao.Manutencao;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;

public interface ManutencaoDao {
	
	boolean adicionar(Manutencao manutencao);
	
	boolean alterar(Manutencao manutencao);
	
	List<Manutencao> listar();
	
	List<Manutencao> listarMecanico(Funcionario funcionario);
	
	List<Manutencao> buscaCliente(Usuario cliente);
	
	Manutencao buscaId(int id); 
	
	Manutencao criarManutencao(ResultSet rs) throws SQLException;

}




