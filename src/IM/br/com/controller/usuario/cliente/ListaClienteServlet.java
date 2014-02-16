package IM.br.com.controller.usuario.cliente;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.cliente.Cliente;
import IM.br.com.model.dao.usuario.cliente.ClienteDao;
import IM.br.com.model.dao.usuario.cliente.ClienteDaoJBDC;

public class ListaClienteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ClienteDao dao = new ClienteDaoJBDC();
		
		List<Usuario> clientes =  dao.listar();
		req.setAttribute("clientes", clientes); 
		RequestDispatcher rd = req.getRequestDispatcher("listacliente.jsp");
		rd.forward(req, resp);
	}
}
