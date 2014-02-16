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

public class AdicionaFuncionarioServlet  extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String msg = "CPF ou E-mail já existe";
		
		String nome = req.getParameter("nome");
		String cpf = req.getParameter("cpf");
		String endereco = req.getParameter("endereco");
		String email = req.getParameter("email");
		
		String tipo = req.getParameter("tipo");
		String nomeUsuario = req.getParameter("nomeUsuario");
		String senha = req.getParameter("senha");
		String reqSalario = req.getParameter("salario"); 
		double salario = Double.parseDouble(reqSalario);
		Funcionario funcionario = new Funcionario();
		Login login = new Login();
		funcionario.setLogin(login);
		
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setEndereco(endereco);
		funcionario.setEmail(email);
		
		funcionario.setTipo(TipoFuncionario.valueOf(tipo));
		funcionario.setSalario(salario);
		funcionario.getLogin().setNome(nomeUsuario);
		funcionario.getLogin().setSenha(senha);
		
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		Usuario validaEmail = daoUsuario.buscarEmail(email);
		Usuario validaCPF = daoUsuario.bucarCPF(cpf);
		
		
		LoginDao daoLogin = new LoginDaoJBDC();
		Login validaNomeUsuario = daoLogin.buscarNomeLogin(nomeUsuario);
		
		boolean resposta = false;
		if(validaEmail == null && validaCPF == null && validaNomeUsuario == null){
			
			FuncionarioDao dao = new FuncionarioDaoJBDC();
			resposta = dao.adicionar(funcionario); 
			if(resposta) resp.sendRedirect("listafuncionario.do");
			else msg = "Erro ao adiciona Funcionario";
				
		}
		
		if(!resposta){
			
			req.setAttribute("msg", msg); 
			RequestDispatcher rd = req.getRequestDispatcher("adicionafuncionario.jsp");
			rd.forward(req, resp); 
		}
		
	} 
}
