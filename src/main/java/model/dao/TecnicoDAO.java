package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.seletor.Seletor;


import model.vo.TecnicoVO;

public class TecnicoDAO {

	public ArrayList<TecnicoVO> consultarTecnicosDAO() {
		String sql = "select idtecnico,nome,telefone from tecnico";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<TecnicoVO> tecnicosVO = new ArrayList<TecnicoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				TecnicoVO tecnicoVO = new TecnicoVO();
				tecnicoVO.setIdtecnico(result.getInt("idtecnico"));
				tecnicoVO.setNome(result.getString("nome"));
				tecnicoVO.setTelefone(result.getString("telefone"));
				tecnicosVO.add(tecnicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tecnicosVO;
	}

	public ArrayList<TecnicoVO> consultarTecnicosDAO(String consulta, String comboBoxPesquisa) {
		String sql = "select idtecnico,nome,telefone from tecnico where ";
		if (comboBoxPesquisa.equals("id")) {
			
			sql += " idtecnico = " + consulta + "";
		
		} else {
			
			sql += comboBoxPesquisa + " like '" + consulta.trim() + "%'";
		}

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<TecnicoVO> tecnicosVO = new ArrayList<TecnicoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				TecnicoVO tecnicoVO = new TecnicoVO();
				tecnicoVO.setIdtecnico(result.getInt("idtecnico"));
				tecnicoVO.setNome(result.getString("nome"));
				tecnicoVO.setTelefone(result.getString("telefone"));
				tecnicosVO.add(tecnicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tecnicosVO;
	}

	public boolean inserirTecnicoDAO(String nomeComTrim, String telefoneComTrim) {
		int retorno = 0;
		String sql = "INSERT INTO tecnico (nome,telefone) values('" + nomeComTrim + "','" + telefoneComTrim + "')";
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

	public boolean excluirDAO(int idInteiro) {
		int retorno = 0;
		String sql = "DELETE FROM tecnico WHERE idtecnico =" + idInteiro;
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

	public boolean updateDAO(String nomeComTrim, String telefoneComTrim, int id) {
		int retorno = 0;
		String sql = "UPDATE tecnico set nome='" + nomeComTrim + "', telefone='" + telefoneComTrim + "'"
				+ " where idtecnico = " + id;
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

	public List<TecnicoVO> listarComSeletor(Seletor seletor) {
		String sql = "select idtecnico,nome,telefone from tecnico";
		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		if (seletor.temPaginacao()) {
			// TODO continuar...
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<TecnicoVO> tecnicosVO = new ArrayList<TecnicoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				TecnicoVO tecnicoVO = new TecnicoVO();
				tecnicoVO.setIdtecnico(result.getInt("idtecnico"));
				tecnicoVO.setNome(result.getString("nome"));
				tecnicoVO.setTelefone(result.getString("telefone"));
				tecnicosVO.add(tecnicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tecnicosVO;
	}

	private String criarFiltros(Seletor seletor, String sql) {

		// Tem pelo menos UM filtro
		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.getIdProduto() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.id = " + seletor.getIdProduto();
			primeiro = false;
		}

		if ((seletor.getNomeProduto() != null) && (seletor.getNomeProduto().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.nome LIKE '%" + seletor.getNomeProduto() + "%'";
			primeiro = false;
		}

		if ((seletor.getCorProduto() != null) && (seletor.getCorProduto().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.cor = '" + seletor.getCorProduto() + "'";
			primeiro = false;
		}

		if (seletor.getPesoProduto() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.peso = " + seletor.getPesoProduto();
			primeiro = false;
		}

		if ((seletor.getDataInicioCadastro() != null) && (seletor.getDataFimCadastro() != null)) {
			// Regra composta, olha as 3 opções de preenchimento do período

			// Todo o período preenchido (início E fim)
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.dataCadastro BETWEEN" + seletor.getDataInicioCadastro() + " AND " + seletor.getDataFimCadastro();
			primeiro = false;
		} else if (seletor.getDataInicioCadastro() != null) {
			// só o início
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.dataCadastro >= " + seletor.getDataInicioCadastro();
			primeiro = false;
		} else if (seletor.getDataFimCadastro() != null) {
			// só o fim
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.dataCadastro <= " + seletor.getDataFimCadastro();
			primeiro = false;
		}

		return sql;

	}
}
