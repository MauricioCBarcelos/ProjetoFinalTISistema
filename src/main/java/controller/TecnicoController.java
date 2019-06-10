package controller;

import java.util.List;

import model.bo.ChamadosBO;
import model.bo.TecnicoBO;
import model.dto.TelaInicialDTO;
import model.vo.TecnicoVO;

public class TecnicoController {

	public List<TecnicoVO> consultaTecnicosController() {
		TecnicoBO tecnicoBO = new TecnicoBO();
		List<TecnicoVO> retorno = tecnicoBO.consultarTecnicosBO();
		return retorno;
	}

	public List<TecnicoVO> consultaTecnicosController(String consulta, String comboBoxPesquisa) {
		TecnicoBO tecnicoBO = new TecnicoBO();

		List<TecnicoVO> retorno = tecnicoBO.consultarTecnicosBO(consulta, comboBoxPesquisa);

		return retorno;
	}

	public String inserirTecnicoController(String nome, String telefone) {
		TecnicoBO tecnicoBO = new TecnicoBO();
		if (nome.contentEquals("") || nome == null) {

			return "Campo nome Nulo ou vazio";

		} else if (telefone.contentEquals("") || telefone == null) {
			return "Campo telefone Nulo ou vazio";
		}

		return tecnicoBO.inserirTecnicoBO(nome, telefone);
	}

	public String excluirController(Object id) {
		TecnicoBO tecnicoBO = new TecnicoBO();
		String retorno = "";

		int idInteiro = Integer.parseInt(id.toString());

		if (idInteiro <= 0) {

			return "Valor não selecionado";

		}

		return tecnicoBO.excluirTecnicoBO(idInteiro);
	}



}
