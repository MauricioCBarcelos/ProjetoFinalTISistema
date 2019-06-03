package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ChamadoDAO;
import model.vo.ChamadoVO;
import model.vo.viewVO.V_telaInicial;

public class ChamadosBO {

	public List<V_telaInicial> consultachamadoBO() {
		ChamadoDAO chamadodao = new ChamadoDAO();
		ArrayList<V_telaInicial> v_telaInicial = chamadodao.consultarChamados();
		
		return v_telaInicial;
		
	}

}
