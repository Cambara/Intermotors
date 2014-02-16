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

public class EditaCarroServlet extends HttpServlet {
	// atributos para criar a classe spec
	private String modelo;
	private Marca marca;
	private int ano;
	private String cor;
	private String adicionais;
	// atributos para criar a classe automovel
	private int id;
	private String placa;
	private float valor;
	private Status status;
	private double km;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pId = req.getParameter("id");
		int id = Integer.parseInt(pId);

		// cria a DAO
		CarroDAO dao = new CarroDAOJBDC();

		Carro carro = dao.procuraId(id);// receber o automovel de acordo com o
										// id passado

		if (carro != null) {
			// enviar pra uma jsp
			req.setAttribute("carro", carro);
			RequestDispatcher rd = req
					.getRequestDispatcher("/view/usuario/estoquista/alteracadastrocarro.jsp");
			rd.forward(req, resp);
		} else {

			// enviar para uma pg q ira tratar o erro
			resp.sendRedirect("index.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		CarroDAO dao = new CarroDAOJBDC();
		Carro auto = criarAutomovel(req);
		Carro carroAntigo = dao.procuraId(id);
		Carro validaPlaca = null;

		if (!carroAntigo.getPlaca().equals(auto.getPlaca()))
			validaPlaca = dao.procurarPlaca(placa);
		
		String msg = "Placa já cadastrado"; 
		String caminho = "alteracadastrocarro.jsp";
		
		int resultado = 0;
		if (validaPlaca == null) {

			resultado = dao.alterar(auto);
			if(resultado > 0 ){
				resp.sendRedirect("listaCarro");
			}else{
				msg = "Erro ao alterar carro";
			}

		}
		if(resultado <= 0){
			req.setAttribute("msg", msg);
			req.setAttribute("carro", carroAntigo);
			RequestDispatcher rd = req.getRequestDispatcher(caminho);
			rd.forward(req, resp);
		}
	}

	private Spec criarSpec(HttpServletRequest req) {

		// recuperar os parametros do spec
		modelo = req.getParameter("modelo");
		String reqMarca = req.getParameter("marca");
		marca = Marca.valueOf(reqMarca);
		String reqAno = req.getParameter("ano");
		ano = Integer.parseInt(reqAno);
		cor = req.getParameter("cor");
		String[] lista = req.getParameterValues("adicionais");
		adicionais = getLista(lista);

		// criar o bean spec
		Spec spec = new Spec();
		spec.setModelo(modelo);
		spec.setMarca(marca);
		spec.setAno(ano);
		spec.setCor(cor);
		spec.setAdicionais(adicionais);

		return spec;
	}

	private Carro criarAutomovel(HttpServletRequest req) {

		// recuperar os parametros do automovel
		String reqId = req.getParameter("id");
		id = Integer.parseInt(reqId);
		placa = req.getParameter("placa");
		String reqValor = req.getParameter("valor");
		valor = Float.parseFloat(reqValor);
		String reqKm = req.getParameter("km");
		km = Double.parseDouble(reqKm);
		status = Status.VENDA;
		Spec spec = criarSpec(req);

		// criar bean automovel
		Carro auto = new Carro();
		auto.setIdAuto(id);
		auto.setPlaca(placa);
		auto.setValor(valor);
		auto.setStatus(status);
		auto.setKm(km);
		auto.setSpec(spec);

		return auto;
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
