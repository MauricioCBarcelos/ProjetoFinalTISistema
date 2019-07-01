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

	public String inserirProdutoController(ProdutoVO produtoVO) {
		String retorno = "";
		ProdutoBO produtoBO = new ProdutoBO();
		if (produtoVO.getMarca().equals("") || produtoVO.getMarca() == null) {
			return "Campo Marca vazio ou nulo";
		} else if (produtoVO.getQuantidade() < 1) {
			return "Campo quantidade é menor que 1";
		} else if (produtoVO.getValor_custo() < 00.10) {
			return "Valor de custo menor que 10 centavos";
		} else if (produtoVO.getValor_venda() < 0.10) {
			return "Valor de venda menor que 10 centavos";
		} else if (produtoVO.getModelo().equals("") || produtoVO.getModelo().trim().length() < 4) {
			return "Campo Modelo vazio ou com tamanho invalido";
		} else
			retorno = produtoBO.inserirprodutoBO(produtoVO);

		return retorno;
	}

	public String excluirController(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();


		if (produtoVO.getIdproduto() <= 0) {

			return "Valor não selecionado";

		}

		return produtoBO.excluirprodutoBO(produtoVO);
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
	
	public int countLinhasTotalController() {
		ProdutoBO produtoBO = new ProdutoBO();


		int totalLinhas = produtoBO.countLinhasTotalBO();

		return totalLinhas;
	}


	public List<ProdutoVO> consultaProdutoController() {
		ProdutoBO produtoBO = new ProdutoBO();
		List<ProdutoVO> retorno = produtoBO.consultarprodutoBO();

		return retorno;
	}
}
