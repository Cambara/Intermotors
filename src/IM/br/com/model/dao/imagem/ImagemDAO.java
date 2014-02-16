package IM.br.com.model.dao.imagem;

import IM.br.com.model.bean.imagem.Imagem;

public interface ImagemDAO {
	
	boolean adiciona(Imagem imagem);
	
	boolean altera(Imagem imagem);
	
	Imagem procuraId(int id);

}
