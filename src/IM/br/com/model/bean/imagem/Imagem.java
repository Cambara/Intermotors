package IM.br.com.model.bean.imagem;

public class Imagem {
	public static final String CAMINHO_FIXO = "C:imgs/";
	
	private int id;
	private String caminhoImg;
	private String caminhoThumb;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaminhoImg() {
		return caminhoImg;
	}
	public void setCaminhoImg(String caminhoImg) {
		this.caminhoImg = caminhoImg;
	}
	public String getCaminhoThumb() {
		return caminhoThumb;
	}
	public void setCaminhoThumb(String caminhoThumb) {
		this.caminhoThumb = caminhoThumb;
	}
	
}
