package IM.br.com.controller.auto.carro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;

public class BuscaCarroReservaServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pId = req.getParameter("id"); 
		int id = Integer.parseInt(pId); 
		
		CarroDAO dao = new CarroDAOJBDC();
		Automovel carro = dao.procuraId(id); 
		req.setAttribute("carro", carro);
		RequestDispatcher rd = req.getRequestDispatcher("adicionacarroreserva.jsp");
		rd.forward(req, resp);
	
	}
	
}
