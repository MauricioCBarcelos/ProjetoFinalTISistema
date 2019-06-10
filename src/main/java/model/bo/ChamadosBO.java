package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ChamadoDAO;
import model.dto.TelaInicialDTO;
import model.vo.ChamadoVO;

public class ChamadosBO {

	public List<TelaInicialDTO> consultachamadoBO() {
		ChamadoDAO chamadodao = new ChamadoDAO();
		ArrayList<TelaInicialDTO> v_telaInicial = chamadodao.consultarChamados();
		
		return v_telaInicial;
		
	}

}
