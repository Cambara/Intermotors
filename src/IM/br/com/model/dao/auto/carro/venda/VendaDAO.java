package IM.br.com.model.dao.auto.carro.venda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import IM.br.com.model.bean.auto.venda.Venda;

public interface VendaDAO {
	
	boolean adiciona(Venda venda);
	
	List<Venda> lista();
	
	Venda criaVenda(ResultSet rs) throws SQLException;
	
}
