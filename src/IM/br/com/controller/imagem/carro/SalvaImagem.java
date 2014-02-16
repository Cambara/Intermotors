package IM.br.com.controller.imagem.carro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.imagem.Imagem;
import IM.br.com.model.bean.imagem.RedenrizaImagem;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;
import IM.br.com.model.dao.imagem.ImagemDAO;
import IM.br.com.model.dao.imagem.ImagemDAOJBDC;

public class SalvaImagem {
	private static final String CAMINHO_FIXO  = "C:\\imgs\\";
	public boolean upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean resp = false;
		
		boolean isMultPart = ServletFileUpload.isMultipartContent(request);
		
		if(!isMultPart) return resp;
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try{
			List<FileItem> items = upload.parseRequest(request);
			FileItem fiIdCarro = items.get(0);
			int idCarro = Integer.parseInt(fiIdCarro.getString());
			CarroDAO daoCarro = new CarroDAOJBDC();
			Automovel carro = daoCarro.procuraId(idCarro);
			FileItem imagem = items.get(1);
			if(carro != null && imagem.getName().length() > 0){
				String caminho = inserirImagem(imagem, carro.getPlaca());
				RedenrizaImagem ri = new RedenrizaImagem();
				ri.renderizar(caminho+carro.getPlaca()+".jpg", caminho+carro.getPlaca()+".jpg", 500, 300);
				ri.renderizar(caminho+carro.getPlaca()+".jpg", caminho+"Thumb"+carro.getPlaca()+".jpg", 50, 32);
				
				Imagem im = new Imagem();
				im.setId(carro.getIdAuto());
				im.setCaminhoImg(caminho+carro.getPlaca()+".jpg");
				im.setCaminhoThumb(caminho+"Thumb"+carro.getPlaca()+".jpg");
				
				ImagemDAO dao = new  ImagemDAOJBDC();
				resp = dao.adiciona(im);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resp;
	}
	
	private String inserirImagem(FileItem item, String placa) throws IOException{
		String caminho = CAMINHO_FIXO + placa;
		File diretorio = new File(caminho);
		if(!diretorio.exists()) diretorio.mkdir();
		
//		String nome = item.getName();
//		String arg[] = nome.split("\\\\");
//		for (int i = 0; i < arg.length; i++) nome = arg[i];
		
		File file = new File(diretorio, placa+".jpg");
		FileOutputStream output = new FileOutputStream(file);
		InputStream is = item.getInputStream();
		byte[] buffer = new byte[2048];
		int nLidos;
		while ((nLidos = is.read(buffer))>= 0){
			output.write(buffer, 0, nLidos);
		}
		output.flush();
		output.close();
		return caminho + "\\" ;
	}
}
