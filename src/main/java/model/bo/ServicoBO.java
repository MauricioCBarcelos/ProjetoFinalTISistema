package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ServicoDAO;
import model.seletor.Seletor;
import model.vo.ServicoVO;
import model.vo.ServicoVO;

public class ServicoBO {

	Validador valida = new Validador();

	public List<ServicoVO> consultarServicosBO(Seletor seletor) {
		ServicoDAO servicoDAO = new ServicoDAO();
		ArrayList<ServicoVO> retorno = servicoDAO.consultarServicosDAO(seletor);

		return retorno;
	}

	public List<ServicoVO> consultarServicosBO(String consulta, String comboBoxPesquisa, Seletor seletor) {
		ServicoDAO servicoDAO = new ServicoDAO();

		ArrayList<ServicoVO> retorno;

		retorno = servicoDAO.consultarServicosDAO(consulta, comboBoxPesquisa, seletor);

		return retorno;
	}

	public String inserirServicoBO(ServicoVO servicoVO) {

		ServicoDAO servicoDAO = new ServicoDAO();
		
		if (servicoVO.getNome().trim().length() >= 30 || servicoVO.getNome().trim().length() <= 3) {

			return "Nome é maior que 25 caracteres";

		} else if (servicoVO.getValor() <= 00.99) {

			return "Campo valor abaixo de 1 real";
		} else if (servicoVO.getNome().trim().length() > 29 || servicoVO.getNome().trim().length() < 5 ) {
			
			return "Campo nome maior que 29 ou menor que 5";
		} else  if (servicoDAO.inserirServicoDAO(servicoVO)) {

			return "Servico inserido com sucesso";
		} else {

			return "Servico nao inserido pois ele já existe";
		}
	}

	public String excluirServicoBO(ServicoVO servicoVO) {

		ServicoDAO servicoDAO = new ServicoDAO();

		if (servicoDAO.excluirDAO(servicoVO)) {

			return "Valor excluido com sucesso";

		}

		return "Valor não excluido";

	}

	public String updateBO(ServicoVO servicoVO) {
		ServicoDAO servicoDAO = new ServicoDAO();

		if (servicoVO.getNome().trim().length() >= 30 || servicoVO.getNome().trim().length() <= 3) {

			return "Nome é maior que 25 caracteres";

		} else if (servicoVO.getValor() <= 00.99) {

			return "Campo valor abaixo de 1 real";
		} else if (servicoVO.getNome().trim().length() > 29 || servicoVO.getNome().trim().length() < 5 ) {
			
			return "Campo nome maior que 29 ou menor que 5";
		}  else if (servicoDAO.updateDAO(servicoVO)) {

			return "Servico atualizado com Sucesso";
		} else

			return "Servico nao atualizado pois já existe um mesmo CPF cadastrado";

	}

	public int countLinhasTotalBO() {

		ServicoDAO servicoDAO = new ServicoDAO();

		int totalLinhas = servicoDAO.countLinhasTotalDAO();

		return totalLinhas;
	}

}
