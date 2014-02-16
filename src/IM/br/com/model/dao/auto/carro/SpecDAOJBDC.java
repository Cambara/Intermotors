package IM.br.com.model.dao.auto.carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IM.br.com.model.bean.auto.Marca;
import IM.br.com.model.bean.auto.Spec;
import IM.br.com.model.db.connect.ConnectionFactory;

public class SpecDAOJBDC implements SpecDAO{

	@Override
	public boolean adicionar(Spec spec) {
		boolean resp = false;
		Connection con = ConnectionFactory.createConnection();
		if(con != null){
			String sql = "INSERT INTO espautomovel(fk_id_automovel, modelo, marca,adicionais, ano, cor) VALUES(?,?,?,?,?,?)";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, spec.getIdSpec());
				ps.setString(2, spec.getModelo());
				ps.setNString(3, spec.getMarca().name());
				ps.setString(4, spec.getAdicionais());
				ps.setInt(5, spec.getAno());
				ps.setString(6, spec.getCor());
				int resultado = ps.executeUpdate();
				if(resultado > 0){
					resp = true;
					ps.close();
					
				}
				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

	@Override
	public boolean alterar(Spec spec) {
		boolean resp = false;
		Connection con = ConnectionFactory.createConnection();
		if(con != null){
			String sql = "UPDATE espautomovel SET  modelo = ?, marca = ?, adicionais = ?, ano = ?, cor = ? WHERE fk_id_automovel = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, spec.getModelo());
				ps.setString(2, spec.getMarca().name());
				ps.setString(3, spec.getAdicionais());
				ps.setInt(4, spec.getAno());
				ps.setString(5, spec.getCor());
				ps.setInt(6, spec.getIdSpec());
				int resultado = ps.executeUpdate();
				if(resultado > 0){
					resp = true;
					ps.close();
					
				}
				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resp;

	} 
	
	public static Spec criarSpec(ResultSet rs) throws SQLException{
		Spec spec = new Spec();
		spec.setModelo(rs.getString("modelo"));
		spec.setMarca(Marca.valueOf(rs.getString("marca")));
		spec.setAdicionais(rs.getString("adicionais"));
		spec.setAno(rs.getInt("ano"));//TODO: arrumar gambiarra
//		spec.setCor(rs.getString("cor"));
		return spec;
	}
}
