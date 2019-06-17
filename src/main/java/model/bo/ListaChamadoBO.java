package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ListaChamadoDAO;
import model.dto.ListaChamadoDTO;

public class ListaChamadoBO {
	
	public List<ListaChamadoDTO> consultachamadoBO() {
		ListaChamadoDAO listaChamadoDAO = new ListaChamadoDAO();
		ArrayList<ListaChamadoDTO> listachamado = listaChamadoDAO.consultarChamados();
		
		return listachamado;
		
	}


}
