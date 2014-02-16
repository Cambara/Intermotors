package IM.br.com.controller.auto.carro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;

public class ListaCarroServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CarroDAO dao = new CarroDAOJBDC();
		try {
			List<Carro> carros = dao.listar(); 
			req.setAttribute("carros", carros);
			
			RequestDispatcher rd = req.getRequestDispatcher("listacarros.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("index.jsp");
		}
	}

}
