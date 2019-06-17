package controller;

import java.util.List;

import model.bo.ChamadosBO;
import model.dto.TelaInicialDTO;
import model.vo.ChamadoVO;

public class ChamadoController {

	public List<TelaInicialDTO> ConsultaChamados() {

		ChamadosBO chamadobo = new ChamadosBO();
		List<TelaInicialDTO> retorno = chamadobo.consultachamadoBO();
		return retorno;
	}

}
