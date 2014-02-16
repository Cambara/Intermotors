package IM.br.com.controller.auto.reserva;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.reserva.Reserva;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAO;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAOJBDC;

public class ListaReservaServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//DAO
		ReservaDAO dao = new ReservaDAOJBDC();
		
		List<Reserva> reservas = dao.listar();
		req.setAttribute("reservas", reservas);
		RequestDispatcher rq  = req.getRequestDispatcher("");
		rq.forward(req, resp);
		
		
	}
}
