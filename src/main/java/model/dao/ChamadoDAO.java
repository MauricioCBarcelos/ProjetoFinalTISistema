package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.TelaInicialDTO;
import model.vo.ChamadoVO;

public class ChamadoDAO {

	public ArrayList<TelaInicialDTO> consultarChamados() {
		String sql = "SELECT \n" + 
				"        t_chamado.idChamado,\n" + 
				"        t_cliente.nome as nome_cliente,\n" + 
				"        t_servico.nome_servico,\n" + 
				"        t_produtos.modelo,\n" + 
				"        t_tecnico.nome as nome_Tecnico\n" + 
				"    FROM\n" + 
				"        chamado t_chamado\n" + 
				"            INNER JOIN\n" + 
				"        cliente t_cliente ON t_cliente.`idcliente` = t_chamado.`idCliente`\n" + 
				"            INNER JOIN\n" + 
				"        servico t_servico ON t_servico.`idservico` = t_chamado.`servico_idservico`\n" + 
				"            INNER JOIN\n" + 
				"        chamado_produtos t_chamado_produtos ON t_chamado_produtos.idchamado_produto = t_chamado.idChamado\n" + 
				"            INNER JOIN\n" + 
				"        produtos t_produtos ON t_produtos.idprodutos = t_chamado_produtos.idprodutos\n" + 
				"            INNER JOIN\n" + 
				"        tecnico t_tecnico ON t_tecnico.idtecnico = t_chamado.idtecnico;";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<TelaInicialDTO> v_telaInicial = new ArrayList<TelaInicialDTO>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				TelaInicialDTO v_telaInicialLinhas = new TelaInicialDTO();
				v_telaInicialLinhas.setIdChamado(result.getInt("idChamado"));
				v_telaInicialLinhas.setNome_cliente(result.getString("nome_cliente"));
				v_telaInicialLinhas.setNome_servico(result.getString("nome_servico"));
				v_telaInicialLinhas.setModelo(result.getString("modelo"));
				v_telaInicialLinhas.setNome_Tecnico(result.getString("nome_Tecnico"));
				v_telaInicial.add(v_telaInicialLinhas);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return v_telaInicial;

	}

}
