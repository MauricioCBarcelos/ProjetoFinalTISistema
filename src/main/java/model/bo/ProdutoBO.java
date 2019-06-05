package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ProdutoDAO;
import model.dao.TecnicoDAO;
import model.vo.ProdutoVO;
import model.vo.TecnicoVO;

public class ProdutoBO {

	public List<ProdutoVO> consultarprodutoBO() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<ProdutoVO> retorno = produtoDAO.consultarTecnicosDAO();

		return retorno;
	}

	public List<ProdutoVO> consultarprodutoBO(String consulta, String comboBoxPesquisa) {
		ProdutoDAO produtoDAO = new ProdutoDAO();

		ArrayList<ProdutoVO> retorno;
		if (comboBoxPesquisa.equalsIgnoreCase("Codigo")) {

			comboBoxPesquisa = "idprodutos";
			retorno = produtoDAO.consultarTecnicosDAO(consulta, comboBoxPesquisa);

		} else {

			retorno = produtoDAO.consultarTecnicosDAO(consulta, comboBoxPesquisa);
		}

		return retorno;
	}

	public String inserirprodutoBO(String marca, int quantidade, float valorCusto, float valorVenda, String modelo,
			String observacao) {
		String retorno = "";

		String marcaTrim = marca.trim();
		String modeloTrim = modelo.trim();
		String observacaoTrim = observacao.trim();
		ProdutoDAO produtoDAO = new ProdutoDAO();

		if (modeloTrim.length() >= 25) {

			return "modelo é maior que 25 caracteres";

		} else if (marcaTrim.length() >= 11) {

			return "marca é maior que 11 caracteres";

		} else if (observacaoTrim.length() >= 200) {
			return "Descricao é maior que 199 caracteres";

		} else if (produtoDAO.inserirProdutoDAO(marcaTrim, quantidade, valorCusto, valorVenda, modeloTrim,
				observacaoTrim)) {

			return "Produto inserido com sucesso";

		} else

			return "Produto nao inserido";

	}

}
