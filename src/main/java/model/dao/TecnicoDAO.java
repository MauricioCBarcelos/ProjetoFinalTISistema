package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bo.TecnicoBO;
import model.seletor.Seletor;

import model.vo.TecnicoVO;

public class TecnicoDAO {

	public ArrayList<TecnicoVO> consultarTecnicosDAO(Seletor seletor) {
		String sql = "select idtecnico,nome,telefone,CPF from tecnico";
		sql += " limit " + seletor.getLimite() + " offset " + seletor.getOffset();
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
				tecnicoVO.setCpf(result.getString("CPF"));
				tecnicosVO.add(tecnicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return tecnicosVO;
	}

	public ArrayList<TecnicoVO> consultarTecnicosDAO(String consulta, String comboBoxPesquisa, Seletor seletor) {
		String sql = "select idtecnico,nome,telefone,CPF from tecnico where ";
		if (comboBoxPesquisa.equals("Id")) {

			sql += " idtecnico = " + consulta + "";

		} else {

			sql += comboBoxPesquisa + " like '" + consulta.trim() + "%'";
		}
		sql += " limit " + seletor.getLimite() + " offset " + seletor.getOffset();
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
				tecnicoVO.setCpf(result.getString("CPF"));
				tecnicosVO.add(tecnicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tecnicosVO;
	}

	public boolean inserirTecnicoDAO(TecnicoVO tecnicoVO) {
		int retorno = 0;
		String sql = "INSERT INTO tecnico (nome,telefone,CPF) values('" + tecnicoVO.getNome().trim() + "','"
				+ tecnicoVO.getTelefone().trim() + "','"+tecnicoVO.getCpf().trim()+"')";
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

	public boolean excluirDAO(TecnicoVO tecnicoVO) {
		int retorno = 0;
		String sql = "DELETE FROM tecnico WHERE idtecnico =" + tecnicoVO.getIdtecnico();
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

	public boolean updateDAO(TecnicoVO tecnicoVO) {
		int retorno = 0;
		String sql = "UPDATE tecnico set nome='" + tecnicoVO.getNome().trim() + "', telefone='" + tecnicoVO.getTelefone().trim() + "'"
				+ " where idtecnico = " + tecnicoVO.getIdtecnico();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			 e.getMessage();
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}

	public int countLinhasTotalDAO() {
		String sql = "SELECT COUNT(*) as totalLinhas FROM tecnico";
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

	public ArrayList<TecnicoVO> consultarTecnicosDAO() {
		String sql = "select idtecnico,nome,telefone,CPF from tecnico";
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
				tecnicoVO.setCpf(result.getString("CPF"));
				tecnicosVO.add(tecnicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return tecnicosVO;
	}

}
