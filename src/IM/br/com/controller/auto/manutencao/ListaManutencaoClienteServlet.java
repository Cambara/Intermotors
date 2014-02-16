package IM.br.com.controller.auto.manutencao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.manutencao.Manutencao;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDao;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDaoJBDC;
import IM.br.com.model.dao.usuario.UsuarioDao;
import IM.br.com.model.dao.usuario.UsuarioDaoJBDC;

public class ListaManutencaoClienteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest pReq, HttpServletResponse pResp)
			throws ServletException, IOException {
		
		String cpf = pReq.getParameter("cpf");
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		Usuario cliente = daoUsuario.bucarCPF(cpf);
		
		
		if(cliente != null){
			ManutencaoDao daoManutencao = new ManutencaoDaoJBDC();
			List<Manutencao> manutencoes = daoManutencao.buscaCliente(cliente);
			
			pReq.setAttribute("manutencoes", manutencoes);
			RequestDispatcher rd = pReq.getRequestDispatcher("listamanutencao.jsp");
			rd.forward(pReq, pResp); 
			
		}else{
			pResp.sendRedirect("index.jsp");
		}
	}

}
