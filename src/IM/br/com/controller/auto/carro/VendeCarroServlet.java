package IM.br.com.controller.auto.carro;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.bean.auto.historico.Historico;
import IM.br.com.model.bean.auto.venda.Venda;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;
import IM.br.com.model.dao.auto.carro.venda.VendaDAO;
import IM.br.com.model.dao.auto.carro.venda.VendaDAOJBDC;
import IM.br.com.model.dao.usuario.UsuarioDao;
import IM.br.com.model.dao.usuario.UsuarioDaoJBDC;

public class VendeCarroServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pId = req.getParameter("id");
		int id = Integer.parseInt(pId);
		
		CarroDAO dao = new CarroDAOJBDC();
		Carro carro = dao.procuraId(id);
		
		req.setAttribute("carro", carro);
		RequestDispatcher rd = req.getRequestDispatcher("vendacarro.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String cpf = req.getParameter("cpf");
		String pIdCarro = req.getParameter("idCarro");
		int idCarro = Integer.parseInt(pIdCarro);
		String pNPrestacoes = req.getParameter("nPrestacoes");
		int nPrestacoes = Integer.parseInt(pNPrestacoes);
		String pValor = req.getParameter("valorTotal");
		double valor = Double.parseDouble(pValor);
		
		
		
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		Usuario usuario = daoUsuario.bucarCPF(cpf);
		CarroDAO daoCarro = new CarroDAOJBDC();
		Carro carro = daoCarro.procuraId(idCarro);
		
		
		String msg = "CPF Invalido";
		boolean resposta = false;
		if(usuario != null && carro != null){
			HttpSession session = req.getSession();
			Funcionario func = (Funcionario) session.getAttribute("funcionarioLogado");
			
			
			Historico hist = new Historico();
			hist.setAuto(carro);
			hist.setCliente(usuario); 
			hist.setFunc(func);
			
			Venda venda = new Venda();
			venda.setnPrestacoes(nPrestacoes);
			venda.setValor(valor);
			venda.setHist(hist);
			venda.setData(new Date());
			
			VendaDAO daoVenda = new VendaDAOJBDC();
			
			resposta = daoVenda.adiciona(venda);
			if(resposta)
				resp.sendRedirect("listaCarro"); 
			else
				msg ="Erro ao vender o carro";
		}
		if(!resposta){
			
			req.setAttribute("msg", msg);
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp); 
		}
		
		
	}
}
