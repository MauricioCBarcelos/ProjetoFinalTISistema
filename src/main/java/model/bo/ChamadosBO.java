package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ChamadoDAO;
import model.dto.telaInicialDTO;
import model.vo.ChamadoVO;

public class ChamadosBO {

	public List<telaInicialDTO> consultachamadoBO() {
		ChamadoDAO chamadodao = new ChamadoDAO();
		ArrayList<telaInicialDTO> v_telaInicial = chamadodao.consultarChamados();
		
		return v_telaInicial;
		
	}

}
