package seara.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EventoPainelSenha implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int QUANTAS_VEZES_TEM_QUE_PISCAR = 10;
	public static final int QUANTAS_VEZES_TEM_QUE_CHAMAR = 3;

	@Id
	private Long idTratamento;

	@Temporal(TemporalType.DATE)
	private Calendar data;

	private String senha;
	private boolean umNumeroSoh;
	private boolean consumido;
	private Integer indiceNaFila;
	private int quantasVezesPiscou;
	private int quantasVezesChamou;

	public EventoPainelSenha() {
	}

	public EventoPainelSenha(Calendar data, Long idTratamento, boolean consumido) {
		this.idTratamento = idTratamento;
		this.data = data;
		this.consumido = consumido;
	}

	public int getQuantasVezesChamou() {
		return quantasVezesChamou;
	}

	public void setQuantasVezesChamou(int quantasVezesChamou) {
		this.quantasVezesChamou = quantasVezesChamou;
	}

	public Long getIdTratamento() {
		return idTratamento;
	}

	public void setIdTratamento(Long idTratamento) {
		this.idTratamento = idTratamento;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isUmNumeroSoh() {
		return umNumeroSoh;
	}

	public void setUmNumeroSoh(boolean umNumeroSoh) {
		this.umNumeroSoh = umNumeroSoh;
	}

	public boolean isConsumido() {
		return consumido;
	}

	public void setConsumido(boolean consumido) {
		this.consumido = consumido;
	}

	public Integer getIndiceNaFila() {
		return indiceNaFila;
	}

	public void setIndiceNaFila(Integer indiceNaFila) {
		this.indiceNaFila = indiceNaFila;
	}

	public int getQuantasVezesPiscou() {
		return quantasVezesPiscou;
	}

	public void setQuantasVezesPiscou(int quantasVezesPiscou) {
		this.quantasVezesPiscou = quantasVezesPiscou;
	}
}
