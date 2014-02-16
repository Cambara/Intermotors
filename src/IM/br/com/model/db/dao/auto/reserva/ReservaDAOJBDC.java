package IM.br.com.model.db.dao.auto.reserva;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.Status;
import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.bean.auto.historico.HistoricoVenda;
import IM.br.com.model.bean.auto.reserva.Reserva;
import IM.br.com.model.bean.auto.reserva.StatusReserva;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;
import IM.br.com.model.dao.auto.carro.historico.HistoricoCarroDAO;
import IM.br.com.model.dao.auto.carro.historico.HistoricoCarroVendaDAOJBDC;
import IM.br.com.model.db.connect.ConnectionFactory;

public class ReservaDAOJBDC implements ReservaDAO{

	@Override
	public boolean adicionar(Reserva reserva) {
		int  resp = 0;
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			String sql = "INSERT INTO reserva(fk_id_hist_venda, sinal,status, data) VALUES(?,?,?,?)";
			HistoricoCarroDAO dao =  new HistoricoCarroVendaDAOJBDC();
			int idHist = dao.adicionar(reserva.getHist());
			
			if(idHist > 0){
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, idHist);
					ps.setDouble(2, reserva.getValorSinal());
					ps.setString(3, reserva.getStatusReserva().name());
					ps.setDate(4, new  Date (reserva.getData().getTime()));
					resp = ps.executeUpdate();
					
					if (resp > 0){
						 
						CarroDAO daoCarro = new CarroDAOJBDC();
						Carro carro = (Carro) reserva.getHist().getAuto();
						carro.setStatus(Status.RESERVADO); 
						daoCarro.alterar(carro);
					}
					ps.close();
					con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resp > 0;
	}

	@Override
	public boolean alterar(Reserva reserva) {
		int resp = 0;
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			String sql = "UPDATE reserva SET  sinal = ?, status = ?, data = ? WHERE id_reserva = ?";
			
			
		
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDouble(1, reserva.getValorSinal());
				ps.setString(2, reserva.getStatusReserva().name());
				ps.setDate(3, new  Date (reserva.getData().getTime()));
				ps.setInt(4, reserva.getId());
				resp = ps.executeUpdate();
				
				if(resp > 0 && reserva.getStatusReserva().equals(StatusReserva.CANCELADA)){
					CarroDAO daoCarro = new CarroDAOJBDC();
					Carro carro = (Carro) reserva.getHist().getAuto();
					carro.setStatus(Status.VENDA);
					daoCarro.alterar(carro);
				}
				
				ps.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return resp > 0;
	}

	@Override
	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<>();
		
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			
			String sql = "SELECT r.id_reserva, r.sinal, r.data, r.status, " +
					"hv.id_hist_venda, hv.fk_auto, hv.fk_usuario, hv.fk_func FROM reserva r, historico_venda hv" +
					"WHERE r.fk_id_hist_venda = hv.id_hist_venda";
			
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()){
					
					Reserva reserva = criarReserva(rs);
					reservas.add(reserva);
				}
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reservas;
	}

	@Override
	public List<Reserva> listarCliente(Usuario cliente){
		List<Reserva> reservas = new ArrayList<>();
		
		Connection con = ConnectionFactory.createConnection();
		
		if(con !=null){
			
			String sql = "SELECT * FROM reserva r, historico_venda hv WHERE r.fk_id_hist_venda = hv.id_hist_venda AND hv.fk_usuario = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, cliente.getId());
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					Reserva reserva = criarReserva(rs);
					reservas.add(reserva);
				}
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return reservas;
	}

	@Override
	public Reserva procuraId(int id) {
		
		Connection con = ConnectionFactory.createConnection();
		Reserva reserva = new Reserva();
		if(con !=null){
			
			String sql = "SELECT * FROM reserva r, historico_venda hv WHERE r.fk_id_hist_venda = hv.id_hist_venda and r.id_reserva = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					
					reserva = criarReserva(rs);
				}
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reserva;
	}
	
	public Reserva criarReserva(ResultSet rs) throws SQLException{
		Reserva reserva = new Reserva();
		HistoricoCarroDAO historicoDao = new HistoricoCarroVendaDAOJBDC();
		HistoricoVenda historico = (HistoricoVenda) historicoDao.criarHistorico(rs);
		
		reserva.setId(rs.getInt("id_reserva"));
		reserva.setStatusReserva(StatusReserva.valueOf(rs.getString("status")));
		reserva.setValorSinal(rs.getInt("sinal"));
		reserva.setData(rs.getDate("data"));
		reserva.setHist(historico);
		
		return reserva;
	}


}
