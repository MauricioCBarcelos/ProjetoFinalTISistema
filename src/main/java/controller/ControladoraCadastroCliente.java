package controller;

import java.util.List;

import model.bo.ClienteBO;
import model.seletor.Seletor;
import model.vo.ClienteVO;

public class ControladoraCadastroCliente {

	public List<ClienteVO> consultaClientesController(Seletor seletor) {
		ClienteBO clienteBO = new ClienteBO();
		List<ClienteBO> retorno = clienteBO.consultarClientesBO(seletor);
		return retorno;
	}

	public List<ClienteVO> consultaTecnicosController(String consulta, String comboBoxPesquisa, Seletor seletor) {
		ClienteBO clienteBO = new ClienteBO();

		List<ClienteVO> retorno = clienteBO.consultarClientesBO(consulta, comboBoxPesquisa, seletor);

		return retorno;
	}

	public String inserirClienteController(String nome, String telefone, String cpf) {
		ClienteBO clienteBO = new ClienteBO();
		if (nome.contentEquals("") || nome == null) {

			return "Campo nome Nulo ou vazio";

		} else if (telefone.contentEquals("") || telefone == null) {
			return "Campo telefone Nulo ou vazio";
		}

		return clienteBO.inserirClienteBO(nome, telefone);
	}

	public String excluirController(Object id) {
		ClienteBO clienteBO = new ClienteBO();

		int idInteiro = Integer.parseInt(id.toString());

		if (idInteiro <= 0) {

			return "Valor não selecionado";

		}

		return clienteBO.excluirClienteBO(idInteiro);
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
}
