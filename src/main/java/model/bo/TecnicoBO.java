package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.TecnicoDAO;

import model.seletor.Seletor;

import model.vo.TecnicoVO;

public class TecnicoBO {
	Validador validaCPF = new Validador();

	public List<TecnicoVO> consultarTecnicosBO(Seletor seletor) {
		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		ArrayList<TecnicoVO> retorno = tecnicoDAO.consultarTecnicosDAO(seletor);

		return retorno;
	}

	public List<TecnicoVO> consultarTecnicosBO(String consulta, String comboBoxPesquisa, Seletor seletor) {
		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		ArrayList<TecnicoVO> retorno;

		retorno = tecnicoDAO.consultarTecnicosDAO(consulta, comboBoxPesquisa, seletor);

		return retorno;
	}

	public String inserirTecnicoBO(TecnicoVO tecnicoVO) {

		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		
		if (tecnicoVO.getNome().trim().length() >= 30 || tecnicoVO.getNome().trim().length() <= 3) {

			return "Nome é maior que 25 caracteres";

		} else if (tecnicoVO.getTelefone().trim().length() >= 12 || tecnicoVO.getTelefone().trim().length() <= 8) {

			return "Telefone é invalido";
		} else if (validaCPF.isCPF(tecnicoVO.getCpf().trim()) == false) {
			
			return "CPF invalido";
		} else  if (tecnicoDAO.inserirTecnicoDAO(tecnicoVO)) {

			return "Tecnico inserido com sucesso";
		} else {

			return "Tecnico nao inserido pois ele já existe";
		}
	}

	public String excluirTecnicoBO(TecnicoVO tecnicoVO) {

		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		if (tecnicoDAO.excluirDAO(tecnicoVO)) {

			return "Valor excluido com sucesso";

		}

		return "Valor não excluido";

	}

	public String updateBO(TecnicoVO tecnicoVO) {
		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		if (tecnicoVO.getNome().trim().length() >= 30 || tecnicoVO.getNome().trim().length() <= 3) {

			return "Nome é maior que 25 caracteres";

		} else if (tecnicoVO.getTelefone().trim().length() >= 12 || tecnicoVO.getTelefone().trim().length() <= 8) {

			return "Telefone é invalido";
		} else if (validaCPF.isCPF(tecnicoVO.getCpf().trim()) == false) {
			
			return "CPF invalido";
		}  else if (tecnicoDAO.updateDAO(tecnicoVO)) {

			return "Tecnico atualizado com Sucesso";
		} else

			return "Tecnico nao atualizado pois já existe um mesmo CPF cadastrado";

	}

	public int countLinhasTotalBO() {

		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		int totalLinhas = tecnicoDAO.countLinhasTotalDAO();

		return totalLinhas;
	}

}
