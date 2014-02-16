package IM.br.com.model.dao.usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import IM.br.com.model.bean.usuario.Usuario;

public interface UsuarioDao {
	
	int adicionar(Usuario usuario);
	boolean alterar(Usuario usuario);
	Usuario buscarId(int id);
	Usuario buscarEmail(String email);
	Usuario bucarCPF(String cpf);
	List<Usuario> listar();
	Usuario criarUsuario(ResultSet rs) throws SQLException;
}
