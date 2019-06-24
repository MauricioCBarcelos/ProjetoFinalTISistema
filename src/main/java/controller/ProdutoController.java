package controller;

import java.util.List;

import model.bo.ProdutoBO;
import model.bo.TecnicoBO;
import model.seletor.Seletor;
import model.vo.ProdutoVO;
import model.vo.TecnicoVO;

public class ProdutoController {

	public static List<ProdutoVO> consultaProdutoController(Seletor seletor) {
		ProdutoBO produtoBO = new ProdutoBO();
		List<ProdutoVO> retorno = produtoBO.consultarprodutoBO(seletor);

		return retorno;
	}

	public static List<ProdutoVO> consultaProdutoController(String consulta, String comboBoxPesquisa, Seletor seletor) {
		ProdutoBO produtoBO = new ProdutoBO();

		List<ProdutoVO> retorno = produtoBO.consultarprodutoBO(consulta, comboBoxPesquisa,seletor);
		return retorno;
	}

	public String inserirProdutoController(String marca, int quantidade, float valorCusto, float valorVenda,
			String modelo, String observacao) {
		String retorno = "";
		ProdutoBO produtoBO = new ProdutoBO();
		if (marca.equals("") || marca == null) {
			return "Campo Marca vazio ou nulo";
		} else if (quantidade < 1 || marca == null) {
			return "Campo Marca vazio ou nulo";
		} else if (valorCusto < 0.01 || marca == null) {
			return "Campo Marca vazio ou nulo";
		} else if (valorVenda < 0.01 || marca == null) {
			return "Campo Marca vazio ou nulo";
		} else if (modelo.equals("") || marca == null) {
			return "Campo Marca vazio ou nulo";
		} else
			retorno = produtoBO.inserirprodutoBO(marca, quantidade, valorCusto, valorVenda, modelo, observacao);

		return retorno;
	}

	public String excluirController(Object id) {
		ProdutoBO produtoBO = new ProdutoBO();

		int idInteiro = Integer.parseInt(id.toString());

		if (idInteiro <= 0) {

			return "Valor não selecionado";

		}

		return produtoBO.excluirprodutoBO(idInteiro);
	}

	public String updateController(ProdutoVO produtoVO) {
		if (produtoVO.getMarca().contentEquals("") || produtoVO.getMarca() == null) {

			return "Campo nome Nulo ou vazio";

		} else if (produtoVO.getModelo().contentEquals("") || produtoVO.getModelo() == null) {
			return "Campo telefone Nulo ou vazio";
		}
		ProdutoBO produtoBO = new ProdutoBO();

		return produtoBO.updateBO(produtoVO);
	}

}
