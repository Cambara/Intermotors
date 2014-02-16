package IM.br.com.controller.auto.reserva;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.reserva.Reserva;
import IM.br.com.model.bean.auto.reserva.StatusReserva;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAO;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAOJBDC;

public class EditaReservaServlet extends HttpServlet{
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String pId = req.getParameter("id");
		int id = Integer.parseInt(pId);
		String statusReserva = req.getParameter("statusReserva");
		
		ReservaDAO dao = new ReservaDAOJBDC();
		Reserva reserva = dao.procuraId(id);
		
		if(reserva != null){
			
			reserva.setStatusReserva(StatusReserva.valueOf(statusReserva));
			dao.alterar(reserva);
			if(reserva.getStatusReserva().equals(StatusReserva.FINALIZADA)){
				
				req.setAttribute("carro", reserva.getHist().getAuto());
				req.setAttribute("cliente", reserva.getHist().getCliente());
				req.setAttribute("sinal", reserva.getValorSinal());
				RequestDispatcher rd = req.getRequestDispatcher("vendacarro.jsp");
				rd.forward(req, resp);
				
			}
			else{
				resp.sendRedirect("listaCarro");
			}
			
		}else{
			req.setAttribute("msg", "Erro Reserva não existe");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}
		
		
		
		
		
	}
	
	
}
