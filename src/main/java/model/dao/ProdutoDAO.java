package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.seletor.Seletor;
import model.vo.ProdutoVO;
import model.vo.TecnicoVO;

public class ProdutoDAO {

	public ArrayList<ProdutoVO> consultarProdutosDAO(Seletor seletor) {
		String sql = "SELECT idprodutos,marca,modelo,quantidade,valor_custo,valor_venda, descricao FROM produtos";
		sql += " limit " + seletor.getLimite() + " offset " + seletor.getOffset();
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
				produtoVO.setQuantidade((result.getInt("quantidade")));
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

	public ArrayList<ProdutoVO> consultarTecnicosDAO(String consulta, String comboBoxPesquisa, Seletor seletor) {
		String sql = "SELECT idprodutos,marca,modelo,quantidade,valor_custo,valor_venda, descricao FROM produtos where "
				+ comboBoxPesquisa + " like '" + consulta + "%'";
		sql += " limit " + seletor.getLimite() + " offset " + seletor.getOffset();
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
				produtoVO.setQuantidade((result.getInt("quantidade")));
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

	public boolean inserirProdutoDAO(ProdutoVO produtoVO) {
		int retorno = 0;
		String sql = "INSERT INTO produtos (marca, quantidade, valor_custo, valor_venda, modelo, descricao) values('"
				+ produtoVO.getMarca() + "' , " + produtoVO.getQuantidade() + "," + produtoVO.getValor_custo() + ","
				+ produtoVO.getValor_venda() + ", '" + produtoVO.getModelo() + "','" + produtoVO.getObservacao() + "')";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir Tecnico. Causa: \n: " + e.getMessage());
		} catch (Exception e) {
			e.getMessage();
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}

	public boolean excluirDAO(ProdutoVO produtoVO) {
		int retorno = 0;
		String sql = "DELETE FROM produtos WHERE idprodutos =" + produtoVO.getIdproduto();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			e.getMessage();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}

	public boolean updateDAO(ProdutoVO produtoVO) {
		int retorno = 0;
		String sql = "UPDATE produtos set marca='" + produtoVO.getMarca().trim() + "', modelo='"
				+ produtoVO.getModelo().trim() + "'" + ", descricao= '" + produtoVO.getObservacao() + "', quantidade="
				+ produtoVO.getQuantidade() + ", valor_custo=" + produtoVO.getValor_custo() + ", valor_venda="
				+ produtoVO.getValor_venda() + " where idprodutos = " + produtoVO.getIdproduto();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			System.out.println("Erro ao excluir Tecnico. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}
	
	public int countLinhasTotalDAO() {
		String sql = "SELECT COUNT(*) as totalLinhas FROM produtos";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		int resultado = 0;

		try {
			ResultSet result = prepStmt.executeQuery();

			if (result.next()) {
				resultado = result.getInt("totalLinhas");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<ProdutoVO> consultarProdutosDAO() {
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
				produtoVO.setQuantidade((result.getInt("quantidade")));
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

}
