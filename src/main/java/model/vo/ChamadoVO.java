package model.vo;

import java.sql.Date;

public class ChamadoVO {
	private EquipeManutencaoVO idequipamento_manutencao;
	private ClienteVO idcliente;
	private TecnicoVO idtecnico;
	private int idChamado;
	private Date dt_orcamento;
	private String problema_relatado;
	private String status;

	public EquipeManutencaoVO getIdequipamento_manutencao() {
		return idequipamento_manutencao;
	}

	public void setIdequipamento_manutencao(EquipeManutencaoVO idequipamento_manutencao) {
		this.idequipamento_manutencao = idequipamento_manutencao;
	}

	public ClienteVO getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(ClienteVO idcliente) {
		this.idcliente = idcliente;
	}

	public TecnicoVO getIdtecnico() {
		return idtecnico;
	}

	public void setIdtecnico(TecnicoVO idtecnico) {
		this.idtecnico = idtecnico;
	}

	public int getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}

	public Date getDt_orcamento() {
		return dt_orcamento;
	}

	public void setDt_orcamento(Date dt_orcamento) {
		this.dt_orcamento = dt_orcamento;
	}

	public String getProblema_relatado() {
		return problema_relatado;
	}

	public void setProblema_relatado(String problema_relatado) {
		this.problema_relatado = problema_relatado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
