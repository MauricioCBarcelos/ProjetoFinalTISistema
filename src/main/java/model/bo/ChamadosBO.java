package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ChamadoDAO;
import model.vo.ChamadoVO;
import model.vo.viewVO.V_telaInicial;

public class ChamadosBO {

	public List<V_telaInicial> consultachamadoBO() {
		ChamadoVO chamadovo = new  ChamadoVO();
		
		ChamadoDAO chamadodao = new ChamadoDAO();
		ArrayList<V_telaInicial> produtos = chamadodao.consultarChamados();
		
		return produtos;
		
	}

}
