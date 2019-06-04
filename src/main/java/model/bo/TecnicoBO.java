package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ChamadoDAO;
import model.dao.TecnicoDAO;
import model.dto.telaInicialDTO;
import model.vo.TecnicoVO;

public class TecnicoBO {

	public List<TecnicoVO> consultarTecnicosBO() {
		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		ArrayList<TecnicoVO> retorno = tecnicoDAO.consultarTecnicosDAO();
		
		return retorno;
	}
	
	public List<TecnicoVO> consultarTecnicosBO(String consulta, String comboBoxPesquisa) {
		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		ArrayList<TecnicoVO> retorno = tecnicoDAO.consultarTecnicosDAO(consulta,comboBoxPesquisa);
		
		return retorno;
	}

}
