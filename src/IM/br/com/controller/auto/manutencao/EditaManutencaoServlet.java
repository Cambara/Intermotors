package IM.br.com.controller.auto.manutencao;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import IM.br.com.model.bean.auto.manutencao.Manutencao;
import IM.br.com.model.bean.auto.manutencao.StatusManutencao;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDao;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDaoJBDC;

public class EditaManutencaoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pId = req.getParameter("id");
		int id = Integer.parseInt(pId);
		
		
		ManutencaoDao daoManutencao = new ManutencaoDaoJBDC();
		Manutencao manutencao = daoManutencao.buscaId(id);
		
		req.setAttribute("manutencao", manutencao);
		RequestDispatcher rd = req.getRequestDispatcher("manutencao.do");
		rd.forward(req, resp); 
	}
	 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pId = req.getParameter("id");
		int id = Integer.parseInt(pId);
		String status = req.getParameter("status");
		String descricao = req.getParameter("descricao");
		String pValor = req.getParameter("valor");
		float valor = 0;
		if(pValor != null && !pValor.equals(" ")) valor = Float.parseFloat(pValor); 
		ManutencaoDao dao = new ManutencaoDaoJBDC();
		Manutencao manutencao = dao.buscaId(id);
		
		if(manutencao != null){
			if( descricao != null) manutencao.setDescricao(descricao);
			manutencao.setStatusManutencao(StatusManutencao.valueOf(status));
			manutencao.setData(new Date());
			manutencao.setValor(valor); 
			
			if(dao.alterar(manutencao)){
				
				HttpSession session = req.getSession();
				Funcionario funcionario = (Funcionario) session.getAttribute("funcionarioLogado");
				if(funcionario.getTipo().getChave() != "MECANICO")
					resp.sendRedirect("listamanutencaocliente.do?cpf="+manutencao.getHist().getCliente().getCpf());
				else 
					resp.sendRedirect("listamanutencaomecanico.do");
			}
			
			
		}else{
			resp.sendRedirect("index.jsp");
		}
		
		
		
		
		
	}
}
