package IM.br.com.model.bean.imagem;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class RedenrizaImagem {
	
	public void renderizar(String imagemCaminho, String thumbCaminho, int largura, int altura) throws IOException{
		Thumbnails.of(new File(imagemCaminho)).size(largura, altura).toFile(thumbCaminho);
	}
}
