package controller;

import java.util.List;

import model.bo.ChamadosBO;
import model.vo.ChamadoVO;

public class ChamadoController {
	
	
	public List<ChamadoVO> ConsultaChamados() {
		String retorno = "";
		ChamadosBO chamadobo = new ChamadosBO(); 
		retorno = chamadobo.consultachamadoBO();		
		return null;
	}

}
