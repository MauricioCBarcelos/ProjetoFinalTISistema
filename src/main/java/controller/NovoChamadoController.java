package controller;

import model.bo.NovoChamadoBO;
import model.vo.ChamadoVO;
import model.vo.ProdutoVO;

public class NovoChamadoController {

	public String inserirController(ChamadoVO chamadoVO, ProdutoVO produtoVO) {

		NovoChamadoBO novoChamadoBO = new NovoChamadoBO();

		return novoChamadoBO.inserirBO(chamadoVO,produtoVO);
	}

}
