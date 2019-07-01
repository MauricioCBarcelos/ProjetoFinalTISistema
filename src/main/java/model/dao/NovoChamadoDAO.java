package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import model.vo.ChamadoVO;
import model.vo.ProdutoVO;

public class NovoChamadoDAO {

	public boolean inserirDAO(ChamadoVO chamadoVO, ProdutoVO produtoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		String query = "INSERT INTO chamado (idChamado,observacao, problema_relatado,idCliente,idtecnico,servico_idservico) "
				+ "VALUES ('"+produtoVO.getIdproduto()+"','" + chamadoVO.getObservacao() + "','" + chamadoVO.getProblema_relatado() + "',"
				+ chamadoVO.getIdcliente().getIdcliente() + ","
						+ chamadoVO.getIdtecnico().getIdtecnico()+","+chamadoVO.getIdservico().getIdservico()+")";
		try {
			resultado = stmt.executeUpdate(query);

			if (resultado == 1) {
			//	chamadoVO.setIdChamado(consultaIDInserido(chamadoVO));
				chamadoVO.setIdChamado(produtoVO.getIdproduto());
				insertChamadoProdutoDAO(chamadoVO,produtoVO);
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Cadastro do Cliente. Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;

	}

	private void insertChamadoProdutoDAO(ChamadoVO chamadoVO, ProdutoVO produtoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		float total = produtoVO.getValor_custo() + produtoVO.getValor_venda();
		String query = "insert into chamado_produtos (idchamado_produto,idchamado,idprodutos,valor_produto,quantidade_produto)"
				+ "values ("+chamadoVO.getIdChamado()+","+chamadoVO.getIdChamado()+","+produtoVO.getIdproduto()+","+total+","+produtoVO.getQuantidade()+")";
		try {
			resultado = stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Cadastro do Cliente. Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		
	}

	private int consultaIDInserido(ChamadoVO chamadoVO) {
		String sql = "SELECT idChamado  FROM chamado where observacao='"+chamadoVO.getObservacao()+"' and problema_relatado='"
	    +chamadoVO.getProblema_relatado()+"'";
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		int resultado = 0;
		try {
			ResultSet result = prepStmt.executeQuery();
			resultado = result.getInt("idChamado");
			return resultado;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
		
	}

}
