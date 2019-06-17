package controller;

import java.util.List;

import model.bo.ListaChamadoBO;
import model.dto.ListaChamadoDTO;

public class ListaChamadoController {
	
	public List<ListaChamadoDTO> ConsultaChamados() {

		ListaChamadoBO listachamado = new ListaChamadoBO();
		List<ListaChamadoDTO> retorno = listachamado.consultachamadoBO();
		return retorno;
	}


}
