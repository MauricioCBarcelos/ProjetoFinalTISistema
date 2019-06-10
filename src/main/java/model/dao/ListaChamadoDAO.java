package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ListaChamadoDTO;

public class ListaChamadoDAO {

	public ArrayList<ListaChamadoDTO> consultarChamados() {
		String sql ="SELECT \r\n" + 
				"    c.idchamado,\r\n" + 
				"    cli.nome,\r\n" + 
				"    cli.telefone,\r\n" + 
				"    c.status,\r\n" + 
				"    eq.modelo,\r\n" + 
				"    p.valor_custo + p.valor_venda\r\n" + 
				"FROM\r\n" + 
				"    chamado c\r\n" + 
				"        INNER JOIN\r\n" + 
				"    cliente cli ON c.idChamado = cli.idcliente\r\n" + 
				"        INNER JOIN\r\n" + 
				"    equipamento_manutencao eq ON eq.idequipamento_manutencao = c.idChamado\r\n" + 
				"        INNER JOIN\r\n" + 
				"    chamado_produtos cp ON cp.idchamado = c.idChamado\r\n" + 
				"        INNER JOIN\r\n" + 
				"    produtos p ON c.idchamado = p.idprodutos\r\n";
				
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<ListaChamadoDTO> listaChamadosDTO = new ArrayList<ListaChamadoDTO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				ListaChamadoDTO listaChamado = new ListaChamadoDTO();
				listaChamado.setIdchamado(result.getInt("idchamado" ));
				listaChamado.setNome(result.getString("nome" ));
				listaChamado.setTelefone(result.getString("telefone"));
				listaChamado.setStatus(result.getInt("status" ));
				listaChamado.setModelo(result.getString("modelo"));
				listaChamado.setTotal(result.getFloat("total"));
				
				listaChamadosDTO.add(listaChamado);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaChamadosDTO;

		
	}

}
