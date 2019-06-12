package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ListaChamadoDAO;
import model.dao.TecnicoDAO;
import model.dto.ListaChamadoDTO;
import model.vo.TecnicoVO;

public class ListaChamadoBO {
	
	public List<ListaChamadoDTO> consultachamadoBO() {
		ListaChamadoDAO listaChamadoDAO = new ListaChamadoDAO();
		ArrayList<ListaChamadoDTO> listachamado = listaChamadoDAO.consultarChamados();
		
		return listachamado;
		
	}

	public List<ListaChamadoDTO> consultachamadoBO(String consulta, String comboBoxPesquisa) {
		ListaChamadoDAO listaChamadoDAO = new ListaChamadoDAO();

		ArrayList<ListaChamadoDTO> retorno;
		if (comboBoxPesquisa.equalsIgnoreCase("id")) {

			comboBoxPesquisa = "idtecnico";
			retorno = listaChamadoDAO.consultarChamados(consulta, comboBoxPesquisa);

		} else {

			retorno = listaChamadoDAO.consultarChamados(consulta, comboBoxPesquisa);
		}

		return retorno;
	}


}
