package IM.br.com.controller.usuario.funcionario.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.bean.usuario.funcionario.TipoFuncionario;
import IM.br.com.model.bean.usuario.funcionario.login.Login;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDao;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDaoJBDC;
import IM.br.com.model.dao.usuario.funcionario.login.LoginDao;
import IM.br.com.model.dao.usuario.funcionario.login.LoginDaoJBDC;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String redireciona = "login.jsp";
		
		String nomeUsuario = req.getParameter("nomeUsuario");
		String senha = req.getParameter("senha");
		
		LoginDao daoLogin = new LoginDaoJBDC();
		Login login = daoLogin.buscarNomeLogin(nomeUsuario);
		
		if(login.getSenha().equals(senha)){
			FuncionarioDao daoFuncionario = new FuncionarioDaoJBDC(); 
			Funcionario funcionario = daoFuncionario.buscarId(login.getIdFunc());
			HttpSession session = req.getSession();
			session.setAttribute("funcionarioLogado", funcionario);
			
			
			redireciona = req.getContextPath()+ "/view/usuario/"+funcionario.getTipo().getChave().toLowerCase()+"/index.jsp";
			
		}
		resp.sendRedirect(redireciona);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		resp.sendRedirect(req.getContextPath() +"/view/login.jsp");
	
	}

}
