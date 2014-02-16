package IM.br.com.controller.auto.manutencao;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IM.br.com.model.bean.auto.Automovel;
import IM.br.com.model.bean.auto.Status;
import IM.br.com.model.bean.auto.carro.Carro;
import IM.br.com.model.bean.auto.historico.HistoricoManutencao;
import IM.br.com.model.bean.auto.manutencao.Manutencao;
import IM.br.com.model.bean.auto.manutencao.StatusManutencao;
import IM.br.com.model.bean.usuario.Usuario;
import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.dao.auto.carro.CarroDAO;
import IM.br.com.model.dao.auto.carro.CarroDAOJBDC;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDao;
import IM.br.com.model.dao.auto.carro.manutencao.ManutencaoDaoJBDC;
import IM.br.com.model.dao.usuario.UsuarioDao;
import IM.br.com.model.dao.usuario.UsuarioDaoJBDC;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDao;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDaoJBDC;

public class AdicionaManutencaoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest pReq, HttpServletResponse pResp)
			throws ServletException, IOException {
		// pega os parametros
		String cpf = pReq.getParameter("cpf");
		String placa = pReq.getParameter("placa");
		String pMecanico = pReq.getParameter("idMecanico");
		int idMecanico = Integer.parseInt(pMecanico);
		
		UsuarioDao daoUsuario = new UsuarioDaoJBDC();
		FuncionarioDao daoFunc = new FuncionarioDaoJBDC();
		CarroDAO daoCarro = new CarroDAOJBDC();
		ManutencaoDao daoManutencao = new ManutencaoDaoJBDC();
		
		Usuario cliente = daoUsuario.bucarCPF(cpf);
		Automovel carro = daoCarro.procurarPlaca(placa);
		Funcionario func = daoFunc.buscarId(idMecanico);
		
		String msg = "CPF ou Placa do Carro Invalido";
		boolean resposta = false;
		if(cliente != null && carro != null && carro.getStatus().getChave().equals("VENDIDO") ){
			HistoricoManutencao hist = new HistoricoManutencao();
			hist.setAuto(carro);
			hist.setFunc(func);
			hist.setCliente(cliente);
			
			Manutencao manutencao = new Manutencao();
			manutencao.setStatusManutencao(StatusManutencao.ATIVA);
			manutencao.setData(new Date());
			manutencao.setHist(hist);
			
			// adicionar uma revisao no banco

			resposta = daoManutencao.adicionar(manutencao);
			if(resposta) pResp.sendRedirect("index.jsp");
			else
				msg = "Erro ao criar uma manutenção";
			
		}
		if(!resposta){
			pReq.setAttribute("msg", msg);
			RequestDispatcher rd = pReq.getRequestDispatcher("adicionamanutencao.jsp");
			rd.forward(pReq, pResp);
		}
		
		
		
		
	}
}