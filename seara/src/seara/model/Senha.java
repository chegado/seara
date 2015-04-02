package seara.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Senha implements Serializable {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Calendar data;

	@Id
	private Long idTratamento;

	private Integer ultimaEmitida;

	private Integer ultimaChamada;
	
	public Senha() {
	}

	public Senha(Calendar data, Long idTratamento) {
		this.data = data;
		this.idTratamento = idTratamento;
		this.ultimaEmitida = 0;
		this.ultimaChamada = 0;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Long getIdTratamento() {
		return idTratamento;
	}

	public void setIdTratamento(Long idTratamento) {
		this.idTratamento = idTratamento;
	}

	public Integer getUltimaEmitida() {
		return ultimaEmitida;
	}

	public void setUltimaEmitida(Integer ultimaEmitida) {
		this.ultimaEmitida = ultimaEmitida;
	}

	public Integer getUltimaChamada() {
		return ultimaChamada;
	}

	public void setUltimaChamada(Integer ultimaChamada) {
		this.ultimaChamada = ultimaChamada;
	}
}
