package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ClienteDAO;
import model.dao.TecnicoDAO;
import model.seletor.Seletor;
import model.vo.ClienteVO;

public class ClienteBO {
	Validador validaCPF = new Validador();
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

		if (clienteVO.getNome().length() >= 29 || clienteVO.getNome().length() <= 3) {

			return "Nome é maior que 29 caracteres ou menor que 3 caracteres";

		} else if (clienteVO.getTelefone().length() > 11|| clienteVO.getTelefone().length() < 8) {

			return "Telefone é maior que 11 caracteres ou menor que 8 caracteres";
		} else if (validaCPF.isCPF(clienteVO.getCpf().trim()) == false) {
			
			return "CPF invalido";
		}

		else if (clienteDAO.inserirClienteDAO(clienteVO)) {

			return "Cliente inserido com sucesso";
		} else

			return "Cliente nao inserido pois já existe um cliente com o mesmo CPF";
	}

	public String excluirClienteBO(String cpf) {

		ClienteDAO clienteDAO = new ClienteDAO();

		if (clienteDAO.excluirDAO(cpf)) {

			return "cliente excluido com sucesso";

		}

		return "cliente não excluido pois o mesmo está atrelado a uma chamado";

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

			return "Cliente não atualizado pois já este CPF já existe";

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
	
	public int countLinhasTotalBO() {

		ClienteDAO clienteDAO = new ClienteDAO();

		int totalLinhas = clienteDAO.countLinhasTotalDAO();

		return totalLinhas;
	}
	

}
