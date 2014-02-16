package IM.br.com.controller.imagem.carro;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuscaImagemServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String fotoLink = req.getParameter("fotolink");
		File file = new File(fotoLink);
		if(file.exists()){
			ServletContext context = getServletConfig().getServletContext();
			String mimetype = context.getMimeType(file.getName());
			
			
			resp.setContentType(mimetype);
			resp.setHeader("Content-Disposition", "attachment; filename=\""+file.getName()+"\"");
			
			OutputStream out = resp.getOutputStream();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[4096];
			int length;
			while((length = in.read(buffer))>0){
				out.write(buffer,0,length);
			}
			in.close();
			out.flush();
		}
		
	}
}
