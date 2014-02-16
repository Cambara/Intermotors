package IM.br.com.model.dao.auto.carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.Status;
import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.bean.imagem.Imagem;
import IM.br.com.model.dao.imagem.ImagemDAO;
import IM.br.com.model.dao.imagem.ImagemDAOJBDC;
import IM.br.com.model.db.connect.ConnectionFactory;

public class CarroDAOJBDC implements CarroDAO{
	
	
	@Override
	public int adicionar(Carro auto) { 
		int resp = 0;
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			String sql = "INSERT INTO automovel(placa, valor, status, kilometragem) VALUES(?,?,?,?)";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,auto.getPlaca());
				ps.setDouble(2, auto.getValor());
				ps.setString(3, auto.getStatus().name());
				ps.setDouble(4, auto.getKm());
				ps.executeUpdate();
				ResultSet rs =  ps.getGeneratedKeys();//recupera o ultimo id add
				
				if (rs.next()){
					int idAuto = rs.getInt(1);
					auto.getSpec().setIdSpec(idAuto);
					SpecDAO specDao = new SpecDAOJBDC();
					boolean specResp = specDao.adicionar(auto.getSpec());
					if(specResp){
						resp = idAuto;
					}else{
						//saber como dar um rollback
					}
				}
				ps.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

	@Override
	public int alterar(Carro auto) {
		int resp = 0;
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			String sql = "UPDATE automovel SET placa = ?, valor = ?, status = ?, kilometragem = ? WHERE id_auto = ? ";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,auto.getPlaca());
				ps.setDouble(2, auto.getValor());
				ps.setString(3, auto.getStatus().name());
				ps.setDouble(4, auto.getKm());
				ps.setInt(5, auto.getIdAuto());
				int resultado = ps.executeUpdate();
				
				if (resultado > 0){
					SpecDAO specDao = new SpecDAOJBDC();
					auto.getSpec().setIdSpec(auto.getIdAuto());
					boolean specResp = specDao.alterar(auto.getSpec());
					if(specResp){
						resp = auto.getIdAuto();
					}else{
						//saber como dar um rollback
					}
				}
				ps.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resp;

	}


	@Override
	public List<Carro> listar() {
		List<Carro> automoveis = new ArrayList<>();
		
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			
			String sql = "SELECT *FROM automovel a, espautomovel e" +
					" WHERE a.id_auto = e.fk_id_automovel ";
			
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()){
					
					Carro auto = criarAutomovel(rs);
					automoveis.add(auto);
					ImagemDAO imagemDAO = new ImagemDAOJBDC();
					Imagem imagem = imagemDAO.procuraId(auto.getIdAuto());
					if(imagem != null){
						auto.setImagem(imagem);
						System.out.println("\n\n\n"+imagem.getCaminhoImg());
						
					}
				
				}
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return automoveis;
	}

	@Override
	public Carro procuraId(int id) {
		Carro auto = null;
		
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			String sql = "SELECT a.id_auto, a.placa, a.valor, a.status, a.kilometragem, " +
					"e.modelo, e.marca, e.ano, e.adicionais FROM automovel a, espautomovel e "+
					"WHERE a.id_auto = e.fk_id_automovel AND a.id_auto = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					auto = criarAutomovel(rs);
					ImagemDAO imagemDAO = new ImagemDAOJBDC();
					Imagem imagem = imagemDAO.procuraId(auto.getIdAuto());
					if(imagem != null)auto.setImagem(imagem);
				}
				ps.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return auto;
	}

	@Override
	public Carro procurarPlaca(String placa) {
		Carro auto = null;
		
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			String sql = "SELECT * FROM automovel a, espautomovel e WHERE a.id_auto = e.fk_id_automovel AND a.placa = ?";
				
			
			try {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, placa);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					auto = criarAutomovel(rs);
					ImagemDAO imagemDAO = new ImagemDAOJBDC();
					Imagem imagem = imagemDAO.procuraId(auto.getIdAuto());
					if(imagem != null)auto.setImagem(imagem);

				}
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return auto;
	}
	
	
	public Carro criarAutomovel(ResultSet rs) throws SQLException{ 
		Carro auto = new Carro();
		auto.setIdAuto(rs.getInt("id_auto"));
		auto.setPlaca(rs.getString("placa"));
		auto.setStatus(Status.valueOf(rs.getString("status")));
		auto.setValor(rs.getFloat("valor"));
		auto.setKm(rs.getDouble("kilometragem"));
		auto.setSpec(SpecDAOJBDC.criarSpec(rs));//muuito feio, arruma
		return auto;
		
	}

}
