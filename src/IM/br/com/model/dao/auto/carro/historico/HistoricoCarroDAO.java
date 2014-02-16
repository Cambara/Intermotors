package IM.br.com.model.dao.auto.carro.historico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import IM.br.com.model.bean.auto.historico.Historico;

public interface HistoricoCarroDAO {
	
	int adicionar(Historico hist);
	
	int alterar(Historico hist);
	
	List<Historico> listar();
	
	Historico procuraId(int id);

	Historico criarHistorico(ResultSet rs)throws SQLException;

	
	
}
