package model.dto;

public class TelaInicialDTO {

	private int idChamado;
	private String nome_cliente;
	private String nome_servico;
	private String modelo;
	private String nome_Tecnico;

	public int getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public String getNome_servico() {
		return nome_servico;
	}

	public void setNome_servico(String nome_servico) {
		this.nome_servico = nome_servico;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNome_Tecnico() {
		return nome_Tecnico;
	}

	public void setNome_Tecnico(String nome_Tecnico) {
		this.nome_Tecnico = nome_Tecnico;
	}

}
