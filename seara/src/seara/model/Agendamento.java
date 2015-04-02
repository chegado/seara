package seara.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import seara.util.Formatador;
import seara.util.constantes.DiaDaSemana;
import seara.util.constantes.StatusDoAgendamento;

@Entity
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private Long idTratamento;

	private Long idTriagem;

	private Long idAssistido;

	private String nomeTratamento;

	private DiaDaSemana diaDaSemana;

	private String senha;

	private StatusDoAgendamento status;

	@Temporal(TemporalType.DATE)
	private Calendar dataAtendimento;

	@Temporal(TemporalType.DATE)
	private Calendar dataAgendamento;

	@Transient
	private LinkedList<String> listaAcoes = new LinkedList<String>();

	public Agendamento() {
	}

	public String getPendencia() {
		if (this.status.equals(StatusDoAgendamento.Assistido_Faltou)) {
			return "<font color=red><b>Assistido Faltou (" + Formatador.fromCalendarToString(dataAtendimento)
					+ ")</b></font>";
		} else {
			return "Marcar Data Para o Atendimento";
		}
	}

	public String getDataAtendimentoFormatada() {
		if (dataAtendimento == null) {
			return "";
		}

		String data = Formatador.fromCalendarToString(dataAtendimento);

		if (StatusDoAgendamento.Senha_Emitida.equals(status)) {
			return Formatador.ehHoje(dataAtendimento) ? "<font color=green><b>" + data + " (Hoje)</b></font>" : data + " ("
					+ diaDaSemana + ")";
		} else {
			return Formatador.ehHoje(dataAtendimento) ? "<font color=red><b>" + data + " (Hoje)</b></font>" : data + " ("
					+ diaDaSemana + ")";

		}
	}

	public LinkedList<String> getListaAcoes() {
		return listaAcoes;
	}

	public void setListaAcoes(LinkedList<String> listaAcoes) {
		this.listaAcoes = listaAcoes;
	}

	public Long getIdAssistido() {
		return idAssistido;
	}

	public void setIdAssistido(Long idAssistido) {
		this.idAssistido = idAssistido;
	}

	public Long getIdTriagem() {
		return idTriagem;
	}

	public void setIdTriagem(Long idTriagem) {
		this.idTriagem = idTriagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdTratamento() {
		return idTratamento;
	}

	public void setIdTratamento(Long idTratamento) {
		this.idTratamento = idTratamento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public StatusDoAgendamento getStatus() {
		return status;
	}

	public void setStatus(StatusDoAgendamento status) {
		this.status = status;
	}

	public Calendar getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Calendar dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Calendar getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Calendar dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public String getNomeTratamento() {
		return nomeTratamento;
	}

	public void setNomeTratamento(String nomeTratamento) {
		this.nomeTratamento = nomeTratamento;
	}
}
