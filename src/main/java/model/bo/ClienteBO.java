package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.ClienteDAO;
import model.dao.TecnicoDAO;
import model.seletor.Seletor;
import model.vo.ClienteVO;
import model.vo.TecnicoVO;

public class ClienteBO {
	
	public List<ClienteVO> consultarClienteBO(Seletor seletor) {
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<ClienteVO> retorno = clienteDAO.consultarClienteDAO(seletor);

		return retorno;
	}

	public List<ClienteVO> consultarClientesBO(String consulta, Seletor seletor) {
		ClienteDAO ClienteDAO = new ClienteDAO();

		ArrayList<ClienteVO> retorno;

		retorno = ClienteDAO.consultarClienteDAO(seletor);

		return retorno;
	}

	public String inserirCliente(String nome, String telefone,String cpf) {

		String nomeComTrim = nome.trim();
		String telefoneComTrim = telefone.trim();
		String cpfcomTrim = cpf.trim();
		ClienteDAO clienteDAO = new ClienteDAO();

		if (nomeComTrim.length() >= 25) {

			return "Nome é maior que 25 caracteres";

		} else if (telefoneComTrim.length() >= 11) {

			return "Telefone é maior que 11 caracteres";
		} else if (clienteDAO.inserirClienteDAO(nomeComTrim, telefoneComTrim)) {

			return "Cleinte inserido com sucesso";
		} else

			return "Cliente nao inserido";
	}

	public String excluirClienteBO(int idInteiro) {

		TecnicoDAO tecnicoDAO = new TecnicoDAO();

		if (tecnicoDAO.excluirDAO(idInteiro)) {

			return "Valor inserido com sucesso";

		}

		return "Valor não inserido";

	}

	public String updateBO(ClienteVO clienteVO) {
		String nomeComTrim = clienteVO.getNome().trim();
		String telefoneComTrim = clienteVO.getTelefone().trim();
		ClienteDAO clienteDAO = new ClienteDAO();

		if (nomeComTrim.length() >= 25) {

			return "Nome é maior que 25 caracteres";

		} else if (telefoneComTrim.length() >= 11) {

			return "Telefone é maior que 11 caracteres";
		} else if (clienteDAO.updateDAO(nomeComTrim, telefoneComTrim, clienteVO.getIdcliente())) {

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
		if(cDAO.existeRegistroPorNome(novoCliente.getNome())){
			mensagem = "Já existe cliente com esse nome: " + novoCliente.getNome();
		}else {
			int statusPersistencia = cDAO.cadastrarClienteDAO(novoCliente);
			
			if(statusPersistencia == 1) {
				mensagem = "Cliente salva com sucesso";
			}else if(statusPersistencia == 0) {
				mensagem = "Erro ao salvar Cliente";
			}
		}
		
		return mensagem;
	}

	public String inserirClienteBO(String nome, String telefone,String cpf) {

		String nomeComTrim = nome.trim();
		String telefoneComTrim = telefone.trim();
		ClienteDAO clienteoDAO = new ClienteDAO();

		if (nomeComTrim.length() >= 25) {

			return "Nome é maior que 25 caracteres";

		} else if (telefoneComTrim.length() >= 11) {

			return "Telefone é maior que 11 caracteres";
		} else if (clienteoDAO.inserirClienteDAO(nomeComTrim, telefoneComTrim)) {

			return "cliente inserido com sucesso";
		} else

			return "Cliente nao inserido";
	}
}


