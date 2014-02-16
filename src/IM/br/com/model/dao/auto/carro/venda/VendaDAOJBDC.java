package IM.br.com.model.dao.auto.carro.venda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import IM.br.com.model.bean.auto.Status;
import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.bean.auto.venda.Venda;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;
import IM.br.com.model.dao.auto.carro.historico.HistoricoCarroDAO;
import IM.br.com.model.dao.auto.carro.historico.HistoricoCarroVendaDAOJBDC;
import IM.br.com.model.db.connect.ConnectionFactory;

public class VendaDAOJBDC implements VendaDAO{

	@Override
	public boolean adiciona(Venda venda) {
		int resp = 0;
		Connection con = ConnectionFactory.createConnection();
		HistoricoCarroDAO daoHist = new HistoricoCarroVendaDAOJBDC();
		int idHist = daoHist.adicionar(venda.getHist());
		
		if(con != null && idHist > 0){
			
			String sql = "INSERT INTO venda(fk_id_hist_venda, valor, n_prestacoes, data) VALUES(?,?,?,?)";
			
			try {
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, idHist);
				ps.setDouble(2, venda.getValor());
				ps.setInt(3, venda.getnPrestacoes());
				ps.setDate(4, new Date(venda.getData().getTime()));
				resp = ps.executeUpdate();
				
				if(resp > 0){
					
					Carro carro = (Carro) venda.getHist().getAuto();
					carro.setStatus(Status.VENDIDO);
					CarroDAO daoCarro = new CarroDAOJBDC();
					daoCarro.alterar(carro);
				}
				ps.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return resp > 0;
	}

	@Override
	public List<Venda> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venda criaVenda(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
