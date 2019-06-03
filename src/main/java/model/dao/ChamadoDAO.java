package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ChamadoVO;
import model.vo.viewVO.V_telaInicial;

public class ChamadoDAO {

	public ArrayList<V_telaInicial> consultarChamados() {
		String sql = "SELECT idChamado,nome_cliente,nome_servico,modelo,nome_Tecnico FROM telainical";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<V_telaInicial> v_telaInicial = new ArrayList<V_telaInicial>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				V_telaInicial v_telaInicialLinhas = new V_telaInicial();
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
