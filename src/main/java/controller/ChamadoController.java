package controller;

import model.bo.ChamadosBO;

public class ChamadoController {
	
	
	public Arraylist<ChamadoVO> ConsultaChamados() {
		String retorno = "";
		ChamadosBO chamadobo = new ChamadosBO(); 
		retorno = chamadobo.consultachamadoBO();
		
		
		
		
		return retorno;
	}

}
