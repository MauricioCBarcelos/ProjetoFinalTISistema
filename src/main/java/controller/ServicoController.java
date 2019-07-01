package controller;

import java.util.List;

import model.bo.ServicoBO;
import model.seletor.Seletor;
import model.vo.ServicoVO;
import model.vo.ServicoVO;

public class ServicoController {

	public List<ServicoVO> consultaController(Seletor seletor) {
		ServicoBO servicoBO = new ServicoBO();
		List<ServicoVO> retorno = servicoBO.consultarServicosBO(seletor);
		return retorno;
	}

	public List<ServicoVO> consultaController(String consulta, String comboBoxPesquisa, Seletor seletor) {
		ServicoBO servicoBO = new ServicoBO();

		List<ServicoVO> retorno = servicoBO.consultarServicosBO(consulta, comboBoxPesquisa, seletor);

		return retorno;
	}

	public String inserirController(ServicoVO servicoVO) {
		ServicoBO servicoBO = new ServicoBO();
		if (servicoVO.getValor() <= 0) {

			return "Campo Valor vazio ou recebeu um valor igual ou abaixo de 0";

		} else if (servicoVO.getNome().contentEquals("") || servicoVO.getNome() == null) {
			return "Campo nome vazio ou nulo";
		}

		return servicoBO.inserirServicoBO(servicoVO);
	}

	public String updateController(ServicoVO servicoVO) {
		if (servicoVO.getValor() <= 0) {

			return "Campo Valor vazio ou recebeu um valor igual ou abaixo de 0";

		} else if (servicoVO.getNome().contentEquals("") || servicoVO.getNome() == null) {
			return "Campo nome vazio ou nulo";
		}

		ServicoBO servicoBO = new ServicoBO();

		return servicoBO.updateBO(servicoVO);
	}

	public String excluirController(ServicoVO servicoVO) {
		ServicoBO servicoBO = new ServicoBO();



		if (servicoVO.getIdservico() <= 0) {

			return "Valor não selecionado";

		}

		return servicoBO.excluirServicoBO(servicoVO);
	}
	
	public int countLinhasTotalController() {
		ServicoBO servicoBO = new ServicoBO();

		int totalLinhas = servicoBO.countLinhasTotalBO();

		return totalLinhas;
	}

}
