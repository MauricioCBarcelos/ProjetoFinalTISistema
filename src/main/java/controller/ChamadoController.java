package controller;

import java.util.List;

import model.bo.ChamadosBO;
import model.dto.telaInicialDTO;
import model.vo.ChamadoVO;

public class ChamadoController {

	public List<telaInicialDTO> ConsultaChamados() {

		ChamadosBO chamadobo = new ChamadosBO();
		List<telaInicialDTO> retorno = chamadobo.consultachamadoBO();
		return retorno;
	}

}
