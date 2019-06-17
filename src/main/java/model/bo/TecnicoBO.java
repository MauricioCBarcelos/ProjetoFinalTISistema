package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ChamadoDAO;
import model.dao.TecnicoDAO;
import model.dto.TelaInicialDTO;
<<<<<<< HEAD
import model.seletor.Seletor;
=======
>>>>>>> 2c59e7f6850601f32135fc81382bdc05250db515
import model.vo.TecnicoVO;

public class TecnicoBO {

	public List<TecnicoVO> consultarTecnicosBO() {
		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		ArrayList<TecnicoVO> retorno = tecnicoDAO.consultarTecnicosDAO();

		return retorno;
	}

	public List<TecnicoVO> consultarTecnicosBO(String consulta, String comboBoxPesquisa) {
		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		ArrayList<TecnicoVO> retorno;
		if (comboBoxPesquisa.equalsIgnoreCase("id")) {

			comboBoxPesquisa = "idtecnico";
			retorno = tecnicoDAO.consultarTecnicosDAO(consulta, comboBoxPesquisa);

		} else {

			retorno = tecnicoDAO.consultarTecnicosDAO(consulta, comboBoxPesquisa);
		}

		return retorno;
	}

	public String inserirTecnicoBO(String nome, String telefone) {

		String nomeComTrim = nome.trim();
		String telefoneComTrim = telefone.trim();
		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		if (nomeComTrim.length() >= 25) {

			return "Nome é maior que 25 caracteres";

		} else if (telefoneComTrim.length() >= 11) {

			return "Telefone é maior que 11 caracteres";
		} else if (tecnicoDAO.inserirTecnicoDAO(nomeComTrim, telefoneComTrim)) {

			return "Tecnico inserido com sucesso";
		} else

			return "Tecnico nao inserido";
	}

	public String excluirTecnicoBO(int idInteiro) {

		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		if (tecnicoDAO.excluirDAO(idInteiro)) {

			return "Valor inserido com sucesso";

		}
		;

		return "Valor não inserido";

	}

	public String updateBO(TecnicoVO tecnicoVO) {
		String nomeComTrim = tecnicoVO.getNome().trim();
		String telefoneComTrim = tecnicoVO.getTelefone().trim();
		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		if (nomeComTrim.length() >= 25) {

			return "Nome é maior que 25 caracteres";

		} else if (telefoneComTrim.length() >= 11) {

			return "Telefone é maior que 11 caracteres";
		} else if (tecnicoDAO.updateDAO(nomeComTrim, telefoneComTrim, tecnicoVO.getIdtecnico())) {

			return "Tecnico atualizado com Sucesso";
		} else

			return "Tecnico nao atualizado devido a outros problemas";

	}

	public List<TecnicoVO> consultarTecnicosBO(Seletor seletor) {
		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		return tecnicoDAO.listarComSeletor(seletor);
	}

}
