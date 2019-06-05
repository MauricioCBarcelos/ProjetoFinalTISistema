package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.telaInicialDTO;
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
		if (comboBoxPesquisa.equals("Nome")) {

			sql += comboBoxPesquisa + " like '" + consulta.trim() + "%'";
		} else {
			sql += comboBoxPesquisa + " = " + consulta + "";
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

}
