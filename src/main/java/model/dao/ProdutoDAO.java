package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.ProdutoVO;
import model.vo.TecnicoVO;

public class ProdutoDAO {

	public ArrayList<ProdutoVO> consultarTecnicosDAO() {
		String sql = "SELECT idprodutos,marca,modelo,quantidade,valor_custo,valor_venda, descricao FROM produtos";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				ProdutoVO produtoVO = new ProdutoVO();
				produtoVO.setIdproduto(result.getInt("idprodutos"));
				produtoVO.setMarca(result.getString("marca"));
				produtoVO.setModelo(result.getString("modelo"));
				produtoVO.setIdproduto(result.getInt("quantidade"));
				produtoVO.setValor_custo(result.getFloat("valor_custo"));
				produtoVO.setValor_venda(result.getFloat("valor_venda"));
				produtoVO.setObservacao(result.getString("descricao"));
				produtosVO.add(produtoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtosVO;
	}

	public ArrayList<ProdutoVO> consultarTecnicosDAO(String consulta, String comboBoxPesquisa) {
		String sql = "SELECT idprodutos,marca,modelo,quantidade,valor_custo,valor_venda, descricao FROM produtos where "
				+ comboBoxPesquisa + " like '" + consulta + "%'";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				ProdutoVO produtoVO = new ProdutoVO();
				produtoVO.setIdproduto(result.getInt("idprodutos"));
				produtoVO.setMarca(result.getString("marca"));
				produtoVO.setModelo(result.getString("modelo"));
				produtoVO.setIdproduto(result.getInt("quantidade"));
				produtoVO.setValor_custo(result.getFloat("valor_custo"));
				produtoVO.setValor_venda(result.getFloat("valor_venda"));
				produtoVO.setObservacao(result.getString("descricao"));
				produtosVO.add(produtoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtosVO;
	}

	public boolean inserirProdutoDAO(String marcaTrim, int quantidade, float valorCusto, float valorVenda,
			String modeloTrim, String observacaoTrim) {
		int retorno = 0;
		String sql = "INSERT INTO produtos (marca, quantidade, valor_custo, valor_venda, modelo, descricao) values('"
				+ marcaTrim + "' , " + quantidade + "," + valorCusto + "," + valorVenda + ", '" + modeloTrim + "','"
				+ observacaoTrim + "')";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir Tecnico. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}

}
