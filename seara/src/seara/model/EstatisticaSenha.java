package seara.model;

import java.io.Serializable;

public class EstatisticaSenha implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeTratamento;
	private String ultimaSenhaChamada;
	private String ultimaSenhaEmitida;
	private Long idTratamento;

	public EstatisticaSenha() {
	}

	public EstatisticaSenha(String nomeTratamento, Long idTratamento, String ultimaSenhaChamada, String ultimaSenhaEmitida) {
		this.nomeTratamento = nomeTratamento;
		this.ultimaSenhaChamada = ultimaSenhaChamada;
		this.ultimaSenhaEmitida = ultimaSenhaEmitida;
		this.idTratamento = idTratamento;
	}

	public Long getIdTratamento() {
		return idTratamento;
	}

	public void setIdTratamento(Long idTratamento) {
		this.idTratamento = idTratamento;
	}

	public String getNomeTratamento() {
		return nomeTratamento;
	}

	public void setNomeTratamento(String nomeTratamento) {
		this.nomeTratamento = nomeTratamento;
	}

	public String getUltimaSenhaChamada() {
		return ultimaSenhaChamada;
	}

	public void setUltimaSenhaChamada(String ultimaSenhaChamada) {
		this.ultimaSenhaChamada = ultimaSenhaChamada;
	}

	public String getUltimaSenhaEmitida() {
		return ultimaSenhaEmitida;
	}

	public void setUltimaSenhaEmitida(String ultimaSenhaEmitida) {
		this.ultimaSenhaEmitida = ultimaSenhaEmitida;
	}
}
