package model.vo;

public class ServicoVO {
	private int idservico;
	private float valor;
	private String nome;
	
	public int getIdservico() {
		return idservico;
	}
	public void setIdservico(int idservico) {
		this.idservico = idservico;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return valor + "\\" + nome;
	}
	
	

}
