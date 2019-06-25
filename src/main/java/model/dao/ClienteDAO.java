package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.seletor.Seletor;
import model.vo.ClienteVO;

public class ClienteDAO {

	public ArrayList<ClienteVO> consultarClienteDAO(Seletor seletor) {
		String sql = "select nome,telefone,cpf from cliente";
		sql += " limit " + seletor.getLimite() + " offset " + seletor.getOffset();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<ClienteVO> clientesVO = new ArrayList<ClienteVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				ClienteVO clienteVO = new ClienteVO();
				clienteVO.setNome(result.getString("nome"));
				clienteVO.setTelefone(result.getString("telefone"));
				clienteVO.setCpf(result.getString("cpf"));

				clientesVO.add(clienteVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return clientesVO;
	}

	public ArrayList<ClienteVO> listar() {
		PreparedStatement stmt;
		ArrayList<ClienteVO> listClientes = new ArrayList<>();
		try {
			Connection conexao = null;
			stmt = conexao.prepareStatement("SELECT nome,telefone,cpf FROM cliente");
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				ClienteVO c = new ClienteVO();
				c.setNome(res.getString("nome"));
				c.setCpf(res.getString("cpf"));
				c.setTelefone(res.getString("Telefone"));
				listClientes.add(c);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listClientes;
	}

	public ArrayList<ClienteVO> consultarTecnicosDAO(String consulta, Seletor seletor) {
		String sql = "selectnome,telefone,cpf from cliente where ";
			sql += " nome = " + consulta + "";
			sql +=" like '" + consulta.trim() + "%'";	
			sql += " limit " + seletor.getLimite() + " offset " + seletor.getOffset();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<ClienteVO> clientesVO = new ArrayList<ClienteVO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				ClienteVO clienteVO = new ClienteVO();				
				clienteVO.setNome(result.getString("nome"));
				clienteVO.setTelefone(result.getString("telefone"));
				clienteVO.setCpf(result.getString("cpf"));
				clientesVO.add(clienteVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientesVO;
	}

	public boolean inserirClienteDAO(String nomeComTrim, String telefoneComTrim, String cpfComTrim) {
		int retorno = 0;
		String sql = "INSERT INTO cliente (nome,telefone,cpf) values('" + nomeComTrim + "','" 
		+ telefoneComTrim + "','" + cpfComTrim + ")'";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir Cliente. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}

	public boolean existeRegistroPorNome(String nome) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT * FROM cliente WHERE UPPER(nome) = '" + nome.toUpperCase() + "'";
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que "
					+ "verifica existência de Cliente por Nome. Erro:"
					+ e.getMessage());
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
}
	public int cadastrarClienteDAO(ClienteVO cliente) {
		Connection conn = Banco.getConnection();		
		Statement stmt = Banco.getStatement(conn);		
		int resultado = 0;
		String query = "INSERT INTO cliente (nome, telefone, cpf) "
				+ "VALUES ('" + cliente.getNome()+ "','" + cliente.getTelefone()+ "','"
								
				+ cliente.getCpf()+ ")'";
		try {
			resultado = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Cadastro do Cliente. Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;
	}

}