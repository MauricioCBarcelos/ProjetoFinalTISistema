package model.vo;

public class ChamadoProduto {
	
	private ProdutoVO idproduto; 
	private ChamadoVO idchamado;
	private int idchamado_produto;
	private float valor_produto;
	private int quantidade_produto;
	
	public ProdutoVO getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(ProdutoVO idproduto) {
		this.idproduto = idproduto;
	}
	public ChamadoVO getIdchamado() {
		return idchamado;
	}
	public void setIdchamado(ChamadoVO idchamado) {
		this.idchamado = idchamado;
	}
	public int getIdchamado_produto() {
		return idchamado_produto;
	}
	public void setIdchamado_produto(int idchamado_produto) {
		this.idchamado_produto = idchamado_produto;
	}
	public float getValor_produto() {
		return valor_produto;
	}
	public void setValor_produto(float valor_produto) {
		this.valor_produto = valor_produto;
	}
	public int getQuantidade_produto() {
		return quantidade_produto;
	}
	public void setQuantidade_produto(int quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}
	
	

}
