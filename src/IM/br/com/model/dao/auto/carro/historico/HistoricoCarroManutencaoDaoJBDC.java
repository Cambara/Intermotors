package IM.br.com.model.dao.auto.carro.historico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.historico.Historico;
import IM.br.com.model.bean.auto.historico.HistoricoVenda;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;
import IM.br.com.model.dao.usuario.UsuarioDao;
import IM.br.com.model.dao.usuario.UsuarioDaoJBDC;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDao;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDaoJBDC;
import IM.br.com.model.db.connect.ConnectionFactory;

public class HistoricoCarroManutencaoDaoJBDC implements HistoricoCarroDAO{

	@Override
	public int adicionar(Historico hist) {
		int resp = 0;
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			String sql = "INSERT INTO historico_manutencao(fk_auto, fk_usuario, fk_func) VALUES(?,?,?)";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, hist.getAuto().getIdAuto());
				ps.setInt(2, hist.getCliente().getId());
				ps.setInt(3, hist.getFunc().getIdFunc());
				ps.executeUpdate();
				ResultSet rs =  ps.getGeneratedKeys();//recupera o ultimo id add
				
				if (rs.next()){
					resp = rs.getInt(1);
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
	public int alterar(Historico hist) {
		int resp = 0;
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			
			String sql = "UPDATE historico_manutencao SET fk_auto = ?, fk_usuario = ?, fk_func ? WHERE id_hist_manutencao = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, hist.getAuto().getIdAuto());
				ps.setInt(2, hist.getCliente().getId());
				ps.setInt(3, hist.getFunc().getIdFunc());
				ps.setInt(4, hist.getId());
				resp = ps.executeUpdate();
				
				
				
				ps.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

	@Override
	public List<Historico> listar() {
		return null;
	}

	@Override
	public Historico procuraId(int id) {
		return null;
	}
	
	public Historico criarHistorico(ResultSet rs) throws SQLException{
		HistoricoVenda historicoVenda = new HistoricoVenda();
		CarroDAO carroDAO = new CarroDAOJBDC();
		FuncionarioDao funcDao = new FuncionarioDaoJBDC();
		UsuarioDao usuarioDao = new UsuarioDaoJBDC();
		
		Automovel carro = carroDAO.procuraId(rs.getInt("fk_auto"));
		Funcionario func = funcDao.buscarId(rs.getInt("fk_func"));
		Usuario usuario = usuarioDao.buscarId(rs.getInt("fk_usuario"));
		
		historicoVenda.setId(rs.getInt("id_hist_manutencao")); 
		historicoVenda.setAuto(carro);
		historicoVenda.setFunc(func);
		historicoVenda.setCliente(usuario);
	
		
		
		
		return historicoVenda;
	}
	
}
