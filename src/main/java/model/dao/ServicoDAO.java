package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.seletor.Seletor;
import model.vo.ServicoVO;

public class ServicoDAO {

	public ArrayList<ServicoVO> consultarServicosDAO(Seletor seletor) {
		String sql = "select idservico,nome_servico,valor_servico from servico";
		sql += " limit " + seletor.getLimite() + " offset " + seletor.getOffset();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<ServicoVO> servicosVO = new ArrayList<ServicoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				ServicoVO servicoVO = new ServicoVO();
				servicoVO.setIdservico(result.getInt("idservico"));
				servicoVO.setNome(result.getString("nome_servico"));
				servicoVO.setValor(result.getFloat("valor_servico"));
				servicosVO.add(servicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return servicosVO;
	}

	public ArrayList<ServicoVO> consultarServicosDAO(String consulta, String comboBoxPesquisa, Seletor seletor) {
		String sql = "select idservico,nome_servico,valor_servico from servico where ";
		if (comboBoxPesquisa.equalsIgnoreCase("id")) {

			sql += " idservico = " + consulta + "";

		}
		if (comboBoxPesquisa.equalsIgnoreCase("nome")) {
			sql += "nome_servico like '" + consulta.trim() + "%'";
		}
		
		
		sql += " limit " + seletor.getLimite() + " offset " + seletor.getOffset();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<ServicoVO> servicosVO = new ArrayList<ServicoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				ServicoVO servicoVO = new ServicoVO();
				servicoVO.setIdservico(result.getInt("idservico"));
				servicoVO.setNome(result.getString("nome_servico"));
				servicoVO.setValor(result.getFloat("valor_servico"));
				servicosVO.add(servicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servicosVO;
	}

	public boolean inserirServicoDAO(ServicoVO servicoVO) {
		int retorno = 0;
		String sql = "INSERT INTO servico (nome_servico,valor_servico) values('" + servicoVO.getNome().trim() + "',"
				+ servicoVO.getValor() + ")";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir Servico. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}

	public boolean excluirDAO(ServicoVO servicoVO) {
		int retorno = 0;
		String sql = "DELETE FROM servico WHERE idservico =" + servicoVO.getIdservico();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			System.out.println("Erro ao excluir Servico. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}

	public boolean updateDAO(ServicoVO servicoVO) {
		int retorno = 0;
		String sql = "UPDATE servico set nome_servico='" + servicoVO.getNome().trim() + "', valor_servico="
				+ servicoVO.getValor()+ "" + " where idservico = " + servicoVO.getIdservico();
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
		String sql = "SELECT COUNT(*) as totalLinhas FROM servico";
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

	public ArrayList<ServicoVO> consultarServicosDAO() {
		String sql = "select idservico,nome_servico,valor_servico from servico";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<ServicoVO> servicosVO = new ArrayList<ServicoVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				ServicoVO servicoVO = new ServicoVO();
				servicoVO.setIdservico(result.getInt("idservico"));
				servicoVO.setNome(result.getString("nome_servico"));
				servicoVO.setValor(result.getFloat("valor_servico"));
				servicosVO.add(servicoVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return servicosVO;
	}

}
