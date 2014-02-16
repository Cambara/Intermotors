package IM.br.com.controller.usuario.cliente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.cliente.Cliente;
import IM.br.com.model.dao.usuario.cliente.ClienteDao;
import IM.br.com.model.dao.usuario.cliente.ClienteDaoJBDC;

public class EditaClienteServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String reqId = req.getParameter("id"); 
		int id = Integer.parseInt(reqId);
		
		ClienteDao dao = new ClienteDaoJBDC();
		
		Usuario cliente =  dao.buscarId(id);
		
		req.setAttribute("cliente", cliente);
		RequestDispatcher rd = req.getRequestDispatcher("alteracliente.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String msg = "CPF ou E-mail já existe";
		
		String reqId = req.getParameter("id");
		int id = Integer.parseInt(reqId);
		String nome = req.getParameter("nome");
		String cpf = req.getParameter("cpf");
		String email = req.getParameter("email").toLowerCase();
		String endereco = req.getParameter("endereco");
		
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEmail(email);
		cliente.setEndereco(endereco);
		
		ClienteDao dao = new ClienteDaoJBDC(); 
		Usuario clienteAntigo =  dao.buscarId(id);
		
		Usuario validaEmail = null;
		Usuario  validaCPF = null;
		if(!cliente.getEmail().equals(clienteAntigo.getEmail())) validaEmail = dao.buscarEmail(email);
		if(!cliente.getCpf().equals(clienteAntigo.getCpf())) validaCPF = dao.bucarCPF(cpf);
		
		boolean resposta = false;
		
		if(validaEmail == null && validaCPF == null)
		{	
			resposta = dao.alterar(cliente); 
			if(resposta) resp.sendRedirect("listacliente.do");
		}
		if(!resposta) {
			req.setAttribute("msg", msg); 
			req.setAttribute("cliente", clienteAntigo);
			RequestDispatcher rd = req.getRequestDispatcher("alteracliente.jsp");
			rd.forward(req, resp);
		}
	}
}
