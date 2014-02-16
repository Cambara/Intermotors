package IM.br.com.controller.auto.reserva;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.historico.HistoricoVenda;
import IM.br.com.model.bean.auto.reserva.Reserva;
import IM.br.com.model.bean.auto.reserva.StatusReserva;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;
import IM.br.com.model.dao.usuario.UsuarioDao;
import IM.br.com.model.dao.usuario.UsuarioDaoJBDC;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAO;
import IM.br.com.model.db.dao.auto.reserva.ReservaDAOJBDC;

public class AdicionaReservaServlet extends HttpServlet{
	
	private int idCarro;
	private int idCliente;
	private int idVendedor;
	private float sinal;
	private StatusReserva status;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String cpf = req.getParameter("cpf");
		String pIdCarro = req.getParameter("idCarro");
		idCarro = Integer.parseInt(pIdCarro);
		String pSinal = req.getParameter("sinal");
		sinal = Float.parseFloat(pSinal);
		status = StatusReserva.ATIVA;
		
	 
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		Usuario usuario = daoUsuario.bucarCPF(cpf);
		String msg = "Erro ao adiciona reserva";
		
		
		if(usuario != null){
			HttpSession session = req.getSession();
			Funcionario vendedor = (Funcionario) session.getAttribute("funcionarioLogado");
			idVendedor = vendedor.getIdFunc();
			idCliente = usuario.getId();
			Reserva reserva = criarReserva();
			ReservaDAO dao = new ReservaDAOJBDC();
			
			if(dao.adicionar(reserva)) msg = "Reserva adicionada";
			
		}
		
 
		req.setAttribute("msg", msg);
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
	
		
	
	}
	
	private Reserva criarReserva(){
		CarroDAO daoCarro = new CarroDAOJBDC();
		Automovel carro = daoCarro.procuraId(idCarro);
		
		
		Usuario cliente = new Usuario();
		cliente.setId(idCliente);
		
		Funcionario vendedor = new Funcionario();
		vendedor.setIdFunc(idVendedor);
		
		HistoricoVenda historico = new HistoricoVenda();
		historico.setAuto(carro);
		historico.setCliente(cliente);
		historico.setFunc(vendedor);
		
		
		Reserva reserva = new Reserva();
		reserva.setValorSinal(sinal);
		reserva.setStatusReserva(status);
		reserva.setHist(historico);
		Date data = new Date();
		reserva.setData(data);
		
		
		
		
		
		return reserva;
	}

}
