package IM.br.com.model.dao.imagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IM.br.com.model.bean.imagem.Imagem;
import IM.br.com.model.db.connect.ConnectionFactory;

public class ImagemDAOJBDC implements ImagemDAO{

	@Override
	public boolean adiciona(Imagem imagem) {
		int resp = 0;
		
		Connection con = ConnectionFactory.createConnection();
            if (con != null) {
            	try {
	                String sql = "INSERT INTO imagem( fk_id_auto, caminho_img, caminho_thumb) VALUES (?,?,?)";
	                PreparedStatement ps = con.prepareStatement(sql);
	                ps.setInt(1, imagem.getId());
	                ps.setString(2, imagem.getCaminhoImg());
	                ps.setString(3, imagem.getCaminhoThumb());
	                resp = ps.executeUpdate();
	                ps.close();
	                con.close();
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
            }

		return resp > 0;
	}

	@Override
	public boolean altera(Imagem imagem) {
		int resp = 0;
		return resp > 0;
	}

	@Override
	public Imagem procuraId(int id) {
		Imagem imagem = new Imagem();
		
		Connection con = ConnectionFactory.createConnection();
        if (con != null) {
        	try {
        		String sql = "SELECT * FROM imagem WHERE fk_id_auto = ?";
        		PreparedStatement ps = con.prepareStatement(sql); 
        		ps.setInt(1, id);
        		ResultSet rs = ps.executeQuery();
        		
        		if(rs.next()){
        			imagem = criaImagem(rs);
        		}
        		ps.close();
				con.close();
        	
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
        }
		
        	
		return imagem;
	}
	
	public static Imagem criaImagem(ResultSet rs) throws SQLException{
		Imagem imagem = new Imagem();
		imagem.setCaminhoImg(rs.getString("caminho_img"));
		imagem.setCaminhoThumb(rs.getString("caminho_thumb"));
		return imagem;
	}
}
