package controller;

import java.util.List;

import model.bo.ListaChamadoBO;
import model.bo.TecnicoBO;
import model.dto.ListaChamadoDTO;
import model.vo.TecnicoVO;

public class ListaChamadoController {
	
	public List<ListaChamadoDTO> ConsultaChamados() {

		ListaChamadoBO listachamado = new ListaChamadoBO();
		List<ListaChamadoDTO> retorno = listachamado.consultachamadoBO();
		return retorno;
	}

	public List<ListaChamadoDTO> ConsultaChamados(String consulta, String comboBoxPesquisa) {
		ListaChamadoBO listaChamadoBO = new ListaChamadoBO();

		List<ListaChamadoDTO> retorno = listaChamadoBO.consultachamadoBO(consulta, comboBoxPesquisa);

		return retorno;
	}


}
