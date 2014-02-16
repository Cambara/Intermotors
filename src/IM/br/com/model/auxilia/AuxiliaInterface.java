package IM.br.com.model.auxilia;

import java.util.List;

import javax.servlet.http.HttpSession;

import IM.br.com.model.bean.usuario.funcionario.Funcionario;
import IM.br.com.model.bean.usuario.funcionario.TipoFuncionario;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDao;
import IM.br.com.model.dao.usuario.funcionario.FuncionarioDaoJBDC;




public class AuxiliaInterface {
	
	private FuncionarioDao daoFuncionario;
	
	public AuxiliaInterface(){
		daoFuncionario = new FuncionarioDaoJBDC();
	}
	
	public List<Funcionario> getMecanicos(){
		
		List<Funcionario> mecanicos = daoFuncionario.buscarTipo(TipoFuncionario.MECANICO);
		
		return mecanicos;
		
	}
	
	
}
