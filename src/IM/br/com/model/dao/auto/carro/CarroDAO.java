package IM.br.com.model.dao.auto.carro;

import java.util.List;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.dao.auto.AutomovelDAO;

public interface CarroDAO extends AutomovelDAO{
	
	int adicionar(Carro auto);
	
	int alterar(Carro auto);
	
	List<Carro> listar();
	
	Carro procuraId(int id);
	
	Carro procurarPlaca(String placa);

}
