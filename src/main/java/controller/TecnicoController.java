package controller;

import java.util.List;

import model.bo.TecnicoBO;
import model.seletor.Seletor;
import model.vo.TecnicoVO;

public class TecnicoController {

	public List<TecnicoVO> consultaTecnicosController(Seletor seletor) {
		TecnicoBO tecnicoBO = new TecnicoBO();
		List<TecnicoVO> retorno = tecnicoBO.consultarTecnicosBO(seletor);
		return retorno;
	}

	public List<TecnicoVO> consultaTecnicosController(String consulta, String comboBoxPesquisa,Seletor seletor) {
		TecnicoBO tecnicoBO = new TecnicoBO();

		List<TecnicoVO> retorno = tecnicoBO.consultarTecnicosBO(consulta, comboBoxPesquisa,seletor);

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

		int idInteiro = Integer.parseInt(id.toString());

		if (idInteiro <= 0) {

			return "Valor não selecionado";

		}

		return tecnicoBO.excluirTecnicoBO(idInteiro);
	}

	public String updateController(TecnicoVO tecnicoVO) {

		if (tecnicoVO.getNome().contentEquals("") || tecnicoVO.getNome() == null) {

			return "Campo nome Nulo ou vazio";

		} else if (tecnicoVO.getTelefone().contentEquals("") || tecnicoVO.getTelefone() == null) {
			return "Campo telefone Nulo ou vazio";
		}

		TecnicoBO tecnicoBO = new TecnicoBO();

		return tecnicoBO.updateBO(tecnicoVO);
	}

}
