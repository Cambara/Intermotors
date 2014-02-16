package IM.br.com.model.dao.usuario.funcionario.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IM.br.com.model.bean.usuario.funcionario.login.Login;
import IM.br.com.model.db.connect.ConnectionFactory;

public class LoginDaoJBDC implements LoginDao{

	@Override
	public boolean adicionar(Login login) {
		int reposta = 0;
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			String sql = "INSERT INTO login(fk_id_func,nome_usuario, senha) VALUES(?,?,?)"; 
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, login.getIdFunc());
				ps.setString(2, login.getNome());
				ps.setString(3, login.getSenha());
				reposta = ps.executeUpdate();
				ps.close();
				con.close();
			} catch (Exception e) {
			}
		}
		return reposta > 0;
	}

	@Override
	public boolean alterar(Login login) {
		int reposta = 0;
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			String sql = "UPDATE  login SET nome_usuario = ?, senha = ? WHERE fk_id_func = ?"; 
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, login.getNome());
				ps.setString(2, login.getSenha());
				ps.setInt(3, login.getIdFunc());
				reposta = ps.executeUpdate();
				ps.close();
				con.close();
			} catch (Exception e) {
			}
		}
		
		return reposta > 0;
	}

	@Override
	public Login buscarId(int id) {
		Login login = null;
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			
			String sql = "SELECT * FROM login WHERE fk_id_func = ?"; 
			try {
				PreparedStatement ps = con.prepareStatement(sql); 
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					
					login = criarLogin(rs);
				}
				ps.close();
				con.close();
			} catch (Exception e) {
			}
		}
		
		return login;
	}

	@Override
	public Login buscarNomeLogin(String nomeLogin) {
		Login login = null;
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			
			String sql = "SELECT * FROM login WHERE nome_usuario = ?"; 
			try {
				PreparedStatement ps = con.prepareStatement(sql); 
				ps.setString(1, nomeLogin);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					
					login = criarLogin(rs);
				}
				
			} catch (Exception e) {
			}
		}
		
		return login;
	}

	@Override
	public Login criarLogin(ResultSet rs) throws SQLException {
		Login login = new Login();
		login.setIdFunc(rs.getInt("fk_id_func"));
		login.setNome(rs.getString("nome_usuario"));
		login.setSenha(rs.getString("senha"));
		
		return login;
	}

}
