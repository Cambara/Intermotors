package IM.br.com.model.dao.auto.carro.manutencao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import IM.br.com.model.bean.auto.Status;
import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.bean.auto.manutencao.Manutencao;
import IM.br.com.model.bean.auto.manutencao.StatusManutencao;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;
import IM.br.com.model.dao.auto.carro.historico.HistoricoCarroDAO;
import IM.br.com.model.dao.auto.carro.historico.HistoricoCarroManutencaoDaoJBDC;
import IM.br.com.model.db.connect.ConnectionFactory;

public class ManutencaoDaoJBDC implements ManutencaoDao{

	@Override
	public boolean adicionar(Manutencao manutencao) {
		int resposta = 0;
		Connection con = ConnectionFactory.createConnection();
		HistoricoCarroDAO daoHistorico = new HistoricoCarroManutencaoDaoJBDC();
		int idHist = daoHistorico.adicionar(manutencao.getHist());
		Date data = new Date(manutencao.getData().getTime());
		if(con != null && idHist > 0){
			String sql = "INSERT INTO manutencao(fk_id_hist_manutencao, data, status) VALUES(?,?,?)";
			try {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, idHist);
				ps.setDate(2, data);
				ps.setString(3, manutencao.getStatusManutencao().getChave());
				resposta = ps.executeUpdate();
				if(resposta > 0){
					CarroDAO daoCarro = new CarroDAOJBDC();
					Carro carro = (Carro) manutencao.getHist().getAuto();
					carro.setStatus(Status.MANUTENCAO);
					daoCarro.alterar(carro);
				}
				ps.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return resposta > 0;
	}

	@Override
	public boolean alterar(Manutencao manutencao) {
		int resposta = 0;
		
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			String sql = "UPDATE manutencao SET data = ?, valor = ?, descricao = ?, status = ? WHERE id_manutencao = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDate(1,new Date (manutencao.getData().getTime()));
				ps.setFloat(2, manutencao.getValor());
				ps.setString(3, manutencao.getDescricao());
				ps.setString(4, manutencao.getStatusManutencao().getChave());
				ps.setInt(5, manutencao.getId()); 
				resposta = ps.executeUpdate();
				
				if(resposta > 0 && manutencao.getStatusManutencao().equals(StatusManutencao.FINALIZADA)){
					CarroDAO daoCarro = new CarroDAOJBDC();
					Carro carro = (Carro) manutencao.getHist().getAuto();
					carro.setStatus(Status.VENDIDO);
					daoCarro.alterar(carro); 
				}
				ps.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		
		return resposta>0;
	}

	@Override
	public List<Manutencao> listar() {
		List<Manutencao> manutencoes = new ArrayList<>();
		Connection con = ConnectionFactory.createConnection(); 
		
		if(con != null){
			String sql = "SELECT * FROM manutencao m, historico_manutencao h WHERE m.fk_id_hist_manutencao = h.id_hist_manutencao";
			
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql); 
				
				while(rs.next()){
					Manutencao manutencao = criarManutencao(rs);
					manutencoes.add(manutencao);
				}
				st.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return manutencoes;
	}

	@Override
	public List<Manutencao> listarMecanico(Funcionario funcionario) {
		List<Manutencao> manutencoes = new ArrayList<>();
		Connection con = ConnectionFactory.createConnection(); 
		
		if(con != null){
			String sql = "SELECT * FROM manutencao m, historico_manutencao h WHERE m.fk_id_hist_manutencao = h.id_hist_manutencao AND h.fk_func = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, funcionario.getIdFunc());
				ResultSet rs = ps.executeQuery(); 
				
				while(rs.next()){
					Manutencao manutencao = criarManutencao(rs);
					manutencoes.add(manutencao);
				}
				
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return manutencoes;
	}

	@Override
	public List<Manutencao> buscaCliente(Usuario cliente) {
		List<Manutencao> manutencoes = new ArrayList<>();
		Connection con = ConnectionFactory.createConnection(); 
		
		if(con != null){
			String sql = "SELECT * FROM manutencao m, historico_manutencao h WHERE m.fk_id_hist_manutencao = h.id_hist_manutencao AND h.fk_usuario = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, cliente.getId());
				ResultSet rs = ps.executeQuery(); 
				
				while(rs.next()){
					Manutencao manutencao = criarManutencao(rs);
					manutencoes.add(manutencao);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return manutencoes;
	}
	
	@Override
	public Manutencao buscaId(int id){
		Manutencao manutencao = null; 
		Connection con = ConnectionFactory.createConnection();
		
		if(con != null){
			
			String sql = "SELECT * FROM manutencao m, historico_manutencao h WHERE m.fk_id_hist_manutencao = h.id_hist_manutencao AND m.id_manutencao = ?";
			
			try {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id); 
				ResultSet rs =  ps.executeQuery();
				
				if(rs.next()){
					
					manutencao = criarManutencao(rs);
					
				}
				ps.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return manutencao;
	}

	@Override
	public Manutencao criarManutencao(ResultSet rs) throws SQLException {
		HistoricoCarroDAO histDao = new HistoricoCarroManutencaoDaoJBDC();
		Manutencao manutencao = new Manutencao();
		
		manutencao.setId(rs.getInt("id_manutencao"));
		manutencao.setData(rs.getDate("data"));
		manutencao.setHist(histDao.criarHistorico(rs));
		manutencao.setDescricao(rs.getString("descricao"));
		manutencao.setStatusManutencao(StatusManutencao.valueOf(rs.getString("status")));
		
		
		
		
		return manutencao;
	}

}
