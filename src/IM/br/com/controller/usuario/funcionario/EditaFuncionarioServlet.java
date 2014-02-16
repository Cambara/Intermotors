package IM.br.com.controller.usuario.funcionario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.bean.usuario.funcionario.TipoFuncionario;
import IM.br.com.model.bean.usuario.funcionario.login.Login;
import IM.br.com.model.dao.usuario.UsuarioDao;
import IM.br.com.model.dao.usuario.UsuarioDaoJBDC;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDao;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDaoJBDC;
import IM.br.com.model.dao.usuario.funcionario.login.LoginDao;
import IM.br.com.model.dao.usuario.funcionario.login.LoginDaoJBDC;

public class EditaFuncionarioServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String reqid = req.getParameter("id");
		int id = Integer.parseInt(reqid);
		
		FuncionarioDao dao = new FuncionarioDaoJBDC();
		
		Funcionario funcionario = dao.buscarId(id);
		req.setAttribute("funcionario", funcionario);
		
		RequestDispatcher rd = req.getRequestDispatcher("alterafuncionario.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String msg = "CPF ou E-mail já existe";
		
		String reqIdUsuario = req.getParameter("idUsuario");
		int idUsuario = Integer.parseInt(reqIdUsuario);
		String nome = req.getParameter("nome");
		String cpf = req.getParameter("cpf");
		String endereco = req.getParameter("endereco");
		String email = req.getParameter("email");
		
		String reqIdFunc = req.getParameter("idFunc");
		int idFunc = Integer.parseInt(reqIdFunc);
		String nomeUsuario = req.getParameter("nomeUsuario");
		String senha = req.getParameter("senha");
		String tipo = req.getParameter("tipo");
		String reqSalario = req.getParameter("salario"); 
		double salario = Double.parseDouble(reqSalario);
		
		
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setEndereco(endereco);
		funcionario.setEmail(email);
		funcionario.setId(idUsuario);
		funcionario.setIdFunc(idFunc);
		funcionario.setTipo(TipoFuncionario.valueOf(tipo));
		funcionario.setSalario(salario);
		
		Login login = new Login();
		login.setNome(nomeUsuario);
		login.setSenha(senha);
		login.setIdFunc(idFunc);
		
		funcionario.setLogin(login);
		
		
		
		FuncionarioDao dao = new FuncionarioDaoJBDC();
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		LoginDao daoLogin = new LoginDaoJBDC();
		Funcionario usuarioAntigo = dao.buscarId(idFunc);  
		Usuario validaEmail = null; 
		Usuario validaCPF =  null; 
		Login validaLogin = null;
		if(!usuarioAntigo.getCpf().equals(funcionario.getCpf())) validaCPF = daoUsuario.bucarCPF(cpf);
		if(!usuarioAntigo.getEmail().equals(funcionario.getEmail())) validaEmail = daoUsuario.buscarEmail(email);
		if(!usuarioAntigo.getLogin().getNome().equals(funcionario.getLogin().getNome())) validaLogin = daoLogin.buscarNomeLogin(nomeUsuario); 
		
		
		boolean resposta = false;
		if(validaCPF == null && validaEmail == null && validaLogin == null){
			resposta = dao.alterar(funcionario);
			if(resposta) resp.sendRedirect("listafuncionario.do");
			else msg = "Erro ao alterar Funcionario";
		}
			
		
		if(!resposta){
			req.setAttribute("msg", msg); 
			req.setAttribute("funcionario", usuarioAntigo);
			RequestDispatcher rd = req.getRequestDispatcher("adicionafuncionario.jsp");
			rd.forward(req, resp); 
			
		}
		
		
		
		
	}
}
