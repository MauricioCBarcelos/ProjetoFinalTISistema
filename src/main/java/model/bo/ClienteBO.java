package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ClienteDAO;
import model.dao.TecnicoDAO;
import model.seletor.Seletor;
import model.vo.ClienteVO;

public class ClienteBO {

	public List<ClienteVO> consultarClienteBO(Seletor seletor) {
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<ClienteVO> retorno = clienteDAO.consultarClienteDAO(seletor);

		return retorno;
	}

	public List<ClienteVO> consultarClientesBO(String consulta, String comboBoxPesquisa, Seletor seletor) {
		ClienteDAO ClienteDAO = new ClienteDAO();

		ArrayList<ClienteVO> retorno;

		retorno = ClienteDAO.consultarChamadosDAO(consulta, comboBoxPesquisa, seletor);

		return retorno;
	}

	public String inserirClienteBO(ClienteVO clienteVO) {

		ClienteDAO clienteDAO = new ClienteDAO();

		if (clienteVO.getNome().length() >= 25) {

			return "Nome é maior que 25 caracteres";

		} else if (clienteVO.getTelefone().length() >= 11) {

			return "Telefone é maior que 11 caracteres";
		} else if (clienteVO.getCpf().length() == 10) {
			return "CPF com menor de 11 digitos";
		}

		else if (clienteDAO.inserirClienteDAO(clienteVO)) {

			return "Cleinte inserido com sucesso";
		} else

			return "Cliente nao inserido";
	}

	public String excluirClienteBO(String cpf) {

		ClienteDAO clienteDAO = new ClienteDAO();

		if (clienteDAO.excluirDAO(cpf)) {

			return "cliente excluido com sucesso";

		}

		return "cliente não inserido";

	}

	public String updateBO(ClienteVO clienteVO) {
		String nomeComTrim = clienteVO.getNome().trim();
		String telefoneComTrim = clienteVO.getTelefone().trim();
		String cpfComTrim = clienteVO.getCpf().trim();
		ClienteDAO clienteDAO = new ClienteDAO();

		if (nomeComTrim.length() >= 25) {

			return "Nome é maior que 25 caracteres";

		} else if (telefoneComTrim.length() >= 11) {

			return "Telefone é maior que 11 caracteres";
		} else if (clienteDAO.updateDAO(nomeComTrim, telefoneComTrim, cpfComTrim)) {

			return "Tecnico atualizado com Sucesso";
		} else

			return "Cliente nao atualizado devido a outros problemas";

	}

	public List<ClienteVO> consultarClientesBO(Seletor seletor) {
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<ClienteVO> retorno = clienteDAO.consultarClienteDAO(seletor);

		return retorno;
	}

	public String cadastrar(ClienteVO novoCliente) {

		String mensagem = "";
		ClienteDAO cDAO = new ClienteDAO();
		if (cDAO.existeRegistroPorNome(novoCliente.getNome())) {
			mensagem = "Já existe cliente com esse nome: " + novoCliente.getNome();
		} else {
			int statusPersistencia = cDAO.cadastrarClienteDAO(novoCliente);

			if (statusPersistencia == 1) {
				mensagem = "Cliente salva com sucesso";
			} else if (statusPersistencia == 0) {
				mensagem = "Erro ao salvar Cliente";
			}
		}

		return mensagem;
	}

}
