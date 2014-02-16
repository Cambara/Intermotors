package IM.br.com.model.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.db.connect.ConnectionFactory;

public class UsuarioDaoJBDC implements UsuarioDao{

	@Override
	public int adicionar(Usuario usuario) {
		int resposta = 0;
		
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			String sql = "INSERT INTO usuario(nome, cpf, endereco, email) VALUES(?,?,?,?)";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, usuario.getNome() );
				ps.setString(2, usuario.getCpf());
				ps.setString(3, usuario.getEndereco());
				ps.setString(4, usuario.getEmail());
				ps.executeUpdate();
				
				ResultSet rs =  ps.getGeneratedKeys();//recupera o ultimo id add
				
				if(rs.next()){
					resposta = rs.getInt(1);
				}
				ps.close();
				con.close();
			} catch (Exception e) {
			}
			
		}
		
		
		return resposta;
	}

	@Override
	public boolean alterar(Usuario usuario) {
		int resposta = 0;
		Connection con = ConnectionFactory.createConnection();
		if(con != null){
			
			String sql = "UPDATE usuario SET nome = ?, cpf = ?, endereco = ?, email = ? WHERE id_usuario = ?";
			
			try {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, usuario.getNome() );
				ps.setString(2, usuario.getCpf());
				ps.setString(3, usuario.getEndereco());
				ps.setString(4, usuario.getEmail());
				ps.setInt(5, usuario.getId());
				resposta = ps.executeUpdate();
				ps.close();
				con.close();
			} catch (Exception e) {
			}
		}
		return resposta > 0;
	}

	@Override
	public Usuario buscarId(int id) {
		Usuario usuario = null;
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			
			String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					usuario = criarUsuario(rs);
				}
				ps.close();
				con.close();
			} catch (Exception e) {
			}
		}
		
		return usuario;
	}

	@Override
	public Usuario buscarEmail(String email) {
		Usuario usuario = null;
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			
			String sql = "SELECT * FROM usuario WHERE email = ?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					usuario = criarUsuario(rs);
				}
				ps.close();
				con.close();
			} catch (Exception e) {
			}
		}
		
		return usuario;
	}
	 
	@Override
	public Usuario bucarCPF(String cpf) {
		Usuario usuario = null;
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			
			String sql = "SELECT * FROM usuario WHERE cpf = ?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, cpf);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					usuario = criarUsuario(rs);
				}
				ps.close();
				con.close();
			} catch (Exception e) {
			}
		}
		
		return usuario;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<>();
		
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			
			String sql = "SELECT * FROM usuario";
			try {
				Statement st = con.createStatement();
				
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()){
					
					Usuario usuario = criarUsuario(rs);
					usuarios.add(usuario);
				}
				st.close();
				con.close();
			} catch (Exception e) {
			}
		}
		
		return usuarios;
	}
	
	@Override
	public Usuario criarUsuario(ResultSet rs) throws SQLException{
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("id_usuario"));
		usuario.setNome(rs.getString("nome"));
		usuario.setCpf(rs.getString("cpf"));
		usuario.setEndereco(rs.getString("endereco"));
		usuario.setEmail(rs.getString("email"));
		
		return usuario;
	}

}
