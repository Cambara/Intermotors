package IM.br.com.controller.auto.carro;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.Marca;
import IM.br.com.model.bean.auto.Spec;
import IM.br.com.model.bean.auto.Status;
import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;


public class AdicionaCarroServlet extends HttpServlet{
	//atributos para criar a classe spec
	private String modelo;
	private Marca marca;
	private int ano;
	private String cor;
	private String adicionais;
	//atributos para criar a classe automovel
	private String placa;
	private int valor;
	private Status status;
	private int km;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = "Erro ao adicionar o carro Placa existente";
		
		CarroDAO dao = new CarroDAOJBDC();
		Carro validaPlaca = dao.procurarPlaca( req.getParameter("placa"));
		int id = 0;
		if(validaPlaca == null){
			Carro carro = criarCarro(req);
			
			
			id =dao.adicionar(carro);
			if(id > 0){
				req.setAttribute("id", id);
				RequestDispatcher rd = req.getRequestDispatcher("adicionaimagem.jsp");
				rd.forward(req, resp);
			}else{
				msg = "Erro ao adicionar o carro";
			}
		}
		
		if(id <= 0){
			req.setAttribute("msg", msg);
			RequestDispatcher rd = req.getRequestDispatcher("cadastracarro.jsp");
			rd.forward(req, resp);
		}
	}
	private Spec criarSpec(HttpServletRequest req){
		//recuperar os parametros do spec
		modelo = req.getParameter("modelo");
		String reqMarca = req.getParameter("marca");
		marca = Marca.valueOf(reqMarca);
		String reqAno = req.getParameter("ano");
		ano = Integer.parseInt(reqAno);
		cor = req.getParameter("cor"); 
		String[] lista = req.getParameterValues("adicionais");
		
		adicionais = getLista(lista);
		
		//criar o bean spec
		Spec spec = new Spec();
		spec.setModelo(modelo);
		spec.setMarca(marca);
		spec.setAno(ano);
		spec.setCor(cor);
		spec.setAdicionais(adicionais);
		
		return spec;
	}
	
	private Carro criarCarro(HttpServletRequest req){
		
		//recuperar os parametros do automovel 
		placa = req.getParameter("placa");
		String reqValor = req.getParameter("valor");
		valor = Integer.parseInt(reqValor);
		String reqKm = req.getParameter("km");
		km = Integer.parseInt(reqKm);
		status = Status.VENDA;
		Spec spec = criarSpec(req);
		
		//criar automovel 
		Carro carro = new Carro();
		carro.setPlaca(placa);
		carro.setValor(valor);
		carro.setKm(km);
		carro.setStatus(status);
		carro.setSpec(spec);
		
		return carro;
	}
	private String getLista(String[] lista) {
		String adicionais = "";
		if(lista == null) return "";
		for(String s: lista){
			adicionais = adicionais.concat(s+", ");
		}
		
		adicionais = adicionais.substring(0, adicionais.length() - 2);
		return adicionais;
	}

}
