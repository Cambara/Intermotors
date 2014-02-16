package IM.br.com.controller.usuario.funcionario;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.bean.usuario.funcionario.TipoFuncionario;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDao;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDaoJBDC;

public class ListaFuncionarioTipoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String reqTipo = req.getParameter("tipo");
		FuncionarioDao dao = new FuncionarioDaoJBDC();
		List<Funcionario> funcionarios = dao.buscarTipo(TipoFuncionario.valueOf(reqTipo));
		req.setAttribute("funcionarios", funcionarios);
		RequestDispatcher rd = req.getRequestDispatcher("");
		rd.forward(req, resp);
	}
}
