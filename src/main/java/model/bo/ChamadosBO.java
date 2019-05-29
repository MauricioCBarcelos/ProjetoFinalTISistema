package model.bo;

import java.util.List;

import model.dao.ChamadoDAO;
import model.vo.ChamadoVO;

public class ChamadosBO {

	public List<ChamadoVO> consultachamadoBO() {
		ChamadoVO chamadovo = new  ChamadoVO();
		
		ChamadoDAO chamadodao = new ChamadoDAO();
		
		
		return chamadodao.consultarChamados();
		
	}

}
