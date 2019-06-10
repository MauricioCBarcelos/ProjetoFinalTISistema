package controller;

import java.util.List;

import model.bo.ProdutoBO;
import model.bo.TecnicoBO;
import model.vo.ProdutoVO;
import model.vo.TecnicoVO;

public class ProdutoController {

	public static List<ProdutoVO> consultaProdutoController() {
		ProdutoBO produtoBO = new ProdutoBO();
		List<ProdutoVO> retorno = produtoBO.consultarprodutoBO();

		return retorno;
	}

	public static  List<ProdutoVO> consultaProdutoController(String consulta, String comboBoxPesquisa) {
		ProdutoBO produtoBO = new ProdutoBO();

		List<ProdutoVO> retorno = produtoBO.consultarprodutoBO(consulta, comboBoxPesquisa);
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

}
