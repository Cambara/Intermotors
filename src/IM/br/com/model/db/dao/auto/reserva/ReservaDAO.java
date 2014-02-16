package IM.br.com.model.db.dao.auto.reserva;

import java.util.List;

import IM.br.com.model.bean.auto.reserva.Reserva;
import IM.br.com.model.bean.usuario.Usuario;

public interface ReservaDAO {
	
	boolean adicionar(Reserva reserva);
	
	boolean alterar(Reserva reserva);
	
	List<Reserva> listar();
	
	List<Reserva> listarCliente(Usuario cliente);
	
	Reserva procuraId(int id);
	

}
