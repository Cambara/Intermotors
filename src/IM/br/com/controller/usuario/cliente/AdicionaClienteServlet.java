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

public class AdicionaClienteServlet extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String msg = "CPF ou E-mail já cadastrado"; 
		String nome = req.getParameter("nome");
		String cpf = req.getParameter("cpf");
		String endereco = req.getParameter("endereco");
		String email = req.getParameter("email").toLowerCase();
		
		Cliente cliente = new Cliente();
		
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco);
		
		ClienteDao dao = new ClienteDaoJBDC();
		Usuario validaEmail =  dao.buscarEmail(email);
		Usuario validaCPF =    dao.buscarEmail(cpf);
		int resposta = 0;
		if(validaCPF == null && validaEmail == null){
			resposta = dao.adicionar(cliente) ;
			if(resposta> 0){
				resp.sendRedirect("listacliente.do"); 
			}else{
				msg = "Erro ao adicionar Cliente"; 
			}
		}
		
		if(resposta <= 0){
			req.setAttribute("msg", msg); 
			RequestDispatcher rd = req.getRequestDispatcher("adicionacliente.jsp");
			rd.forward(req, resp); 
		}
	}
}
