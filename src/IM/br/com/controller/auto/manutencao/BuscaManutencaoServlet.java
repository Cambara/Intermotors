package IM.br.com.controller.auto.manutencao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.manutencao.Manutencao;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDao;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDaoJBDC;

public class BuscaManutencaoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pId = req.getParameter("id");
		int id = Integer.parseInt(pId);
		
		
		ManutencaoDao daoManutencao = new ManutencaoDaoJBDC();
		Manutencao manutencao = daoManutencao.buscaId(id);
		
		req.setAttribute("manutencao", manutencao);
		RequestDispatcher rd = req.getRequestDispatcher("manutencao.jsp");
		rd.forward(req, resp); 
	}
	
}
