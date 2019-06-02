package controller;

import java.util.List;

import model.bo.ChamadosBO;
import model.vo.ChamadoVO;
import model.vo.viewVO.V_telaInicial;

public class ChamadoController {

	public List<V_telaInicial> ConsultaChamados() {

		ChamadosBO chamadobo = new ChamadosBO();
		List<V_telaInicial> retorno = chamadobo.consultachamadoBO();
		return retorno;
	}

}
