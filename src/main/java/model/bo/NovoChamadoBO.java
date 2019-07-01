package model.bo;

import model.dao.NovoChamadoDAO;
import model.vo.ChamadoVO;
import model.vo.ProdutoVO;

public class NovoChamadoBO {

	public String inserirBO(ChamadoVO chamadoVO, ProdutoVO produtoVO) {
		String retorno = "";
		NovoChamadoDAO novoChamadoDAO = new NovoChamadoDAO();
			if (novoChamadoDAO.inserirDAO(chamadoVO, produtoVO)) {
				retorno = "Chamado inserido com sucesso";
			}
			
			retorno = "Não foi inserido";
		
		return retorno;
	}

}
