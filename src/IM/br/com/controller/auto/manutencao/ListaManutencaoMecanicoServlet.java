package IM.br.com.controller.auto.manutencao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import IM.br.com.model.bean.auto.manutencao.Manutencao;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDao;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDaoJBDC;

public class ListaManutencaoMecanicoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//busca pela sessao
		HttpSession session = req.getSession();
		Funcionario mecanico = (Funcionario) session.getAttribute("funcionarioLogado");
		
		
		
		
		if(mecanico != null){
			ManutencaoDao daoManutencao = new ManutencaoDaoJBDC();
			List<Manutencao> manutencoes = daoManutencao.listarMecanico(mecanico);
			
			req.setAttribute("manutencoes", manutencoes);
			RequestDispatcher rd = req.getRequestDispatcher("listamanutencao.jsp");
			rd.forward(req, resp); 
			
		}else{
			resp.sendRedirect("index.jsp"); 
		}
	}

}
