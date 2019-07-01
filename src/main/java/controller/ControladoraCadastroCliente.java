package controller;

import java.util.List;

import model.bo.ClienteBO;
import model.dao.ClienteDAO;
import model.seletor.Seletor;
import model.vo.ClienteVO;

public class ControladoraCadastroCliente {
	
	public List<ClienteVO> consultaClientesController(Seletor seletor) {
		ClienteBO clienteBO = new ClienteBO();
		List<ClienteVO> retorno = clienteBO.consultarClientesBO(seletor);
		return retorno;
	}

	public List<ClienteVO> consultaClientesController(String consulta, String comboBoxPesquisa, Seletor seletor) {
		ClienteBO clienteBO = new ClienteBO();

		List<ClienteVO> retorno = clienteBO.consultarClientesBO(consulta, comboBoxPesquisa, seletor);

		return retorno;
	}


	public String excluirController(String cpf) {
		ClienteBO clienteBO = new ClienteBO();


		if (cpf.equals("")) {

			return "Nome não selecionado";

		}

		return clienteBO.excluirClienteBO(cpf);
	}

	public String updateController(ClienteVO clienteVO) {

		if (clienteVO.getNome().contentEquals("") || clienteVO.getNome() == null) {

			return "Campo nome Nulo ou vazio";

		} else if (clienteVO.getTelefone().contentEquals("") || clienteVO.getTelefone() == null) {
			return "Campo telefone Nulo ou vazio";
		} 

		ClienteBO clienteBO = new ClienteBO();

		return clienteBO.updateBO(clienteVO);
	}

	public String salvar(String nome, String telefone, String cpf) {
		String mensagem = "";

		mensagem = validarCampos(nome, telefone, cpf);
		
		if (mensagem == "") {
			ClienteVO novoCliente = new ClienteVO();
			novoCliente.setNome(nome);
			novoCliente.setCpf(cpf);
			novoCliente.setTelefone(telefone);

			ClienteBO bo = new ClienteBO();
			mensagem = bo.cadastrar(novoCliente);
		}

		return mensagem;
	}

	private String validarCampos(String nome, String telefone, String cpf) {
		String mensagem = "";
		if (nome == null || nome.trim().length() < 3) {
			mensagem += "- Nome deve possuir pelo menos 3 caracteres \n";
		}

		if (cpf == null || cpf.trim().length() < 3) {
			mensagem += "- Informe o cpf valido sem ponto e virgula (,)!";
		}
		return mensagem;
	}

	public String salvar(ClienteVO clienteVO) {
		String mensagem = "";
		if (clienteVO.getNome() == null ||  clienteVO.getNome().equals("") ) {
			mensagem = "campo nome nulo ou vazio"; 
			return mensagem;
			
		} else if (clienteVO.getCpf() == null ||  clienteVO.getCpf().equals("") ) {
			mensagem = "campo cpf nulo ou vazio"; 
			return mensagem;

		}
		else {
			ClienteBO bo = new ClienteBO();
			mensagem =  bo.inserirClienteBO(clienteVO);
			return mensagem;
		}
		
		
		
	}
	
	public int countLinhasTotalController() {
		ClienteBO clienteBO = new ClienteBO();

		int totalLinhas = clienteBO.countLinhasTotalBO();

		return totalLinhas;
	}
	
	
}
