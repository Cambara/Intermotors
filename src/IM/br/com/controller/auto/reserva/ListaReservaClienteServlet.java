package IM.br.com.controller.auto.reserva;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.reserva.Reserva;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.dao.usuario.UsuarioDao;
import IM.br.com.model.dao.usuario.UsuarioDaoJBDC;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAO;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAOJBDC;

public class ListaReservaClienteServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String cpf = req.getParameter("cpf");
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		
		Usuario cliente = daoUsuario.bucarCPF(cpf);
		
		if(cliente != null){
			
			ReservaDAO dao = new ReservaDAOJBDC();
			
			List<Reserva> reservas = dao.listarCliente(cliente);
			req.setAttribute("reservas", reservas);
			RequestDispatcher rq  = req.getRequestDispatcher("listareserva.jsp");
			rq.forward(req, resp);
		}
		
	}
}
