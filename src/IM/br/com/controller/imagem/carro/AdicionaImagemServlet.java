package IM.br.com.controller.imagem.carro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdicionaImagemServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		String resposta = req.getContextPath()+"/index.jsp";
		try{
			
			SalvaImagem si = new SalvaImagem();
			boolean resultado = si.upload(req, resp);
			if(resultado){
				resp.sendRedirect("listaCarro");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("msg", "Erro ao adicionar");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}
		
	}
	
	
}
