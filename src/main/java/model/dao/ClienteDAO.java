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
		String sql = "select nome,telefone,cpf,email from cliente";
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
				clienteVO.setEmail(result.getString("email"));
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
		ArrayList<ClienteVO> clientesVO = new ArrayList<>();
		try {
			Connection conexao = null;
			stmt = conexao.prepareStatement("SELECT nome,telefone,cpf FROM cliente");
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				ClienteVO clienteVO = new ClienteVO();
				clienteVO.setNome(result.getString("nome"));
				clienteVO.setTelefone(result.getString("telefone"));
				clienteVO.setCpf(result.getString("cpf"));
				clienteVO.setEmail(result.getString("email"));
				clientesVO.add(clienteVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientesVO;
	}

	public ArrayList<ClienteVO> consultarChamadosDAO(String consulta, String comboBoxPesquisa, Seletor seletor) {
		String sql = "select nome,telefone,cpf from cliente where ";
			sql += comboBoxPesquisa+"  like '" + consulta + "%'";
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

	public boolean inserirClienteDAO(ClienteVO clienteVO) {
		int retorno = 0;
		String sql = "INSERT INTO cliente (nome,telefone,cpf) values('" + clienteVO.getNome().trim()+ "','" 
		+ clienteVO.getTelefone().trim() + "','" + clienteVO.getCpf().trim() + "')";
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

	public boolean updateDAO(ClienteVO clienteVO) {
		int retorno = 0;
		String sql = "UPDATE cliente set nome='" + clienteVO.getNome() + "', telefone='" + clienteVO.getTelefone() + "',cpf='" +  clienteVO.getCpf() + "',email='"+clienteVO.getEmail()+"'"
				+ "where cpf = '"+clienteVO.getCpf()+"'";
				
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}

	public boolean excluirDAO(String cpf) {
		int retorno = 0;
		String sql = "DELETE FROM cliente WHERE cpf =" + cpf;
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		try {

			retorno = prepStmt.executeUpdate();
			if (retorno == 1) {

				return true;

			}

		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return false;
	}
	
	public int countLinhasTotalDAO() {
		String sql = "SELECT COUNT(*) as totalLinhas FROM cliente";
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

}