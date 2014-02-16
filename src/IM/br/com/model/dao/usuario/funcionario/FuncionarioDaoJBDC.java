package IM.br.com.model.dao.usuario.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.bean.usuario.funcionario.TipoFuncionario;
import IM.br.com.model.bean.usuario.funcionario.login.Login;
import IM.br.com.model.dao.usuario.UsuarioDao;
import IM.br.com.model.dao.usuario.UsuarioDaoJBDC;
import IM.br.com.model.dao.usuario.funcionario.login.LoginDao;
import IM.br.com.model.dao.usuario.funcionario.login.LoginDaoJBDC;
import IM.br.com.model.db.connect.ConnectionFactory;

public class FuncionarioDaoJBDC implements FuncionarioDao{

	@Override
	public boolean adicionar(Funcionario funcionario) {
		int resposta = 0;
		Connection con = ConnectionFactory.createConnection();
		
		Usuario usuario = funcionario;
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		int idUsuario = daoUsuario.adicionar(usuario);
		if(con != null && idUsuario > 0){
			
			
			String sql = "INSERT INTO funcionario(fk_id_usuario, tipo, salario) VALUES(?,?,?)";
			try {
				PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, idUsuario);
				ps.setString(2, funcionario.getTipo().name());
				ps.setDouble(3, funcionario.getSalario());
				ps.executeUpdate();
				ResultSet rs =  ps.getGeneratedKeys();//recupera o ultimo id add
				
				if(rs.next()){
					int idFunc = rs.getInt(1);
					Login login = funcionario.getLogin();
					login.setIdFunc(idFunc);
					LoginDao daoLogin = new LoginDaoJBDC();
					
					if(daoLogin.adicionar(login))resposta = 1;
					
				}
				ps.close();
				con.close();
				
			} catch (Exception e) {
			}
			
		}
		
		
		return resposta > 0;
	}

	@Override
	public boolean alterar(Funcionario funcionario) {
		int resposta = 0;
		
		Connection con = ConnectionFactory.createConnection();
		
		Usuario usuario = funcionario;
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		LoginDao daoLogin = new LoginDaoJBDC();
		boolean respUsuario = daoUsuario.alterar(usuario);
		boolean resLogin = daoLogin.alterar(funcionario.getLogin());
		if(con != null && respUsuario == true && resLogin == true){
			
			
			String sql = "UPDATE funcionario SET tipo = ?, salario = ? WHERE id_funcionario = ?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				
				ps.setString(1, funcionario.getTipo().name());
				ps.setDouble(2, funcionario.getSalario());
				ps.setInt(3, funcionario.getIdFunc());
				resposta = ps.executeUpdate();
				
				
				ps.close();
				con.close();
				
				
			} catch (Exception e) {
			}
			
		}
		
		return resposta > 0;
	}

	@Override
	public Funcionario buscarId(int id) {
		Funcionario funcionario = null;
		Connection con = ConnectionFactory.createConnection();
		if(con != null){
			String sql = "SELECT * FROM funcionario f, usuario u, login l  "+
						"WHERE u.id_usuario = f.fk_id_usuario and f.id_funcionario = l.fk_id_func and f.id_funcionario = ?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs =  ps.executeQuery();
				if(rs.next()){
					
					funcionario = criarFuncionario(rs); 
					
				}
				
				
			} catch (Exception e) {
			}
			
		}
		return funcionario;
	}

	@Override
	public List<Funcionario> buscarTipo(TipoFuncionario tipo) {
		List<Funcionario> funcionarios = new ArrayList<>();   
		
		Connection con = ConnectionFactory.createConnection();
		if(con != null){
			
			String sql = "SELECT * FROM funcionario f, usuario u, login l  "+
					"WHERE u.id_usuario = f.fk_id_usuario and f.id_funcionario = l.fk_id_func and tipo = ?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, tipo.getDescricao());
				ResultSet rs =  ps.executeQuery();
				while(rs.next()){
					
					Funcionario funcionario = criarFuncionario(rs); 
					funcionarios.add(funcionario);
				}
				
				
			} catch (Exception e) {
			}
			
		}
		
		return funcionarios;
	}

	@Override
	public List<Funcionario> listar() {
		List<Funcionario> funcionarios = new ArrayList<>();   
		
		Connection con = ConnectionFactory.createConnection();
		if(con != null){
			String sql = "SELECT * FROM funcionario f, usuario u, login l  "+
					"WHERE u.id_usuario = f.fk_id_usuario and f.id_funcionario = l.fk_id_func";			
			try {
				Statement st = con.createStatement();
				ResultSet rs =  st.executeQuery(sql);
				while(rs.next()){
					
					Funcionario funcionario = criarFuncionario(rs); 
					funcionarios.add(funcionario);
				}
				st.close();
				con.close();
				
			} catch (Exception e) {
			}
			
		}
		
		return funcionarios;
	}

	@Override
	public Funcionario criarFuncionario(ResultSet rs) throws SQLException {
		
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setId(rs.getInt("id_usuario"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setCpf(rs.getString("cpf"));
		funcionario.setEndereco(rs.getString("endereco"));
		funcionario.setEmail(rs.getString("email"));
		
		
		
		funcionario.setIdFunc(rs.getInt("id_funcionario"));
		funcionario.setTipo(TipoFuncionario.valueOf(TipoFuncionario.class, rs.getString("tipo")) );
		funcionario.setSalario(rs.getDouble("salario"));
		funcionario.setLogin( new LoginDaoJBDC().criarLogin(rs));
		
		
		return funcionario;
	}

}
