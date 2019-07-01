package model.vo;

public class TecnicoVO {

	private int idtecnico;
	private String nome;
	private String telefone;
	private String cpf;
	
	

	public int getIdtecnico() {
		return idtecnico;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setIdtecnico(int idtecnico) {
		this.idtecnico = idtecnico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String toStringCPF() {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
				cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
	}

	@Override
	public String toString() {
		return nome + "\\" + toStringCPF();
	}

	
}
