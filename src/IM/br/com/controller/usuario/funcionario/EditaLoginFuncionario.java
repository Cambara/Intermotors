package IM.br.com.controller.usuario.funcionario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.usuario.funcionario.login.Login;
import IM.br.com.model.dao.usuario.funcionario.login.LoginDao;
import IM.br.com.model.dao.usuario.funcionario.login.LoginDaoJBDC;

public class EditaLoginFuncionario extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String reqId = req.getParameter("id");
		int id = Integer.parseInt(reqId);
		
		LoginDao dao = new LoginDaoJBDC();
		Login login = dao.buscarId(id);
		
		req.setAttribute("login", login );
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String resposta = "";
		String reqId = req.getParameter("id");
		int id = Integer.parseInt(reqId);
		String nomeUsuario = req.getParameter("nomeUsuario");
		String senha = req.getParameter("senha");
		Login login = new Login();
		login.setIdFunc(id);
		login.setNome(nomeUsuario);
		login.setSenha(senha);
		
		LoginDao dao = new LoginDaoJBDC();
		Login loginAntigo = dao.buscarId(id);
		Login validaNome = null;
		if(login.getNome() != loginAntigo.getNome()) validaNome = dao.buscarNomeLogin(nomeUsuario);
		
		if(validaNome == null){
			if(dao.adicionar(login)) resposta = ""; 
		}
		resp.sendRedirect(resposta);
	}
}
