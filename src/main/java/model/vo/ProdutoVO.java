package model.vo;


public class ProdutoVO {
	
	private int idproduto;
	private String marca;
	private String modelo;
	private int quantidade;
	private String observacao;
	private float valor_custo;
	private float valor_venda;
	
	public int getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public float getValor_custo() {
		return valor_custo;
	}
	public void setValor_custo(float valor_custo) {
		this.valor_custo = valor_custo;
	}
	public float getValor_venda() {
		return valor_venda;
	}
	public void setValor_venda(float valor_venda) {
		this.valor_venda = valor_venda;
	}
	@Override
	public String toString() {
		return  marca + "\\" + modelo;
	}
	
	
	

}
