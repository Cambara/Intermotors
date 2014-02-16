package IM.br.com.controller.auto.reserva;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.reserva.Reserva;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAO;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAOJBDC;

public class BuscaReservaServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pId = req.getParameter("id");
		int id = Integer.parseInt(pId);
		
		ReservaDAO dao = new ReservaDAOJBDC();
		Reserva reserva = dao.procuraId(id);
		req.setAttribute("reserva", reserva);
		RequestDispatcher rd = req.getRequestDispatcher("finalizareserva.jsp");
		rd.forward(req, resp);
		
	
	}

}
