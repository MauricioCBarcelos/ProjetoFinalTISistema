package model.vo;

public class ChamadoServio {
	
	private ChamadoVO idchamado;
	private ServicoVO idservico;
	private int idchamado_servico;
	private float valor_servico;
	private int quantidade_servico;
	
	public ChamadoVO getIdchamado() {
		return idchamado;
	}
	public void setIdchamado(ChamadoVO idchamado) {
		this.idchamado = idchamado;
	}
	public ServicoVO getIdservico() {
		return idservico;
	}
	public void setIdservico(ServicoVO idservico) {
		this.idservico = idservico;
	}
	public int getIdchamado_servico() {
		return idchamado_servico;
	}
	public void setIdchamado_servico(int idchamado_servico) {
		this.idchamado_servico = idchamado_servico;
	}
	public float getValor_servico() {
		return valor_servico;
	}
	public void setValor_servico(float valor_servico) {
		this.valor_servico = valor_servico;
	}
	public int getQuantidade_servico() {
		return quantidade_servico;
	}
	public void setQuantidade_servico(int quantidade_servico) {
		this.quantidade_servico = quantidade_servico;
	}
	
	
	

}
