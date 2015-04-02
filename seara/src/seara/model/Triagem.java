package seara.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import seara.util.constantes.GravidadeDoProblema;
import seara.util.constantes.FeedBack;

@Entity
public class Triagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private Long idAssistido;
	private String nomeAtendente;

	@Temporal(TemporalType.DATE)
	private Calendar data;

	// informações sobre os problemas
	private boolean ehEspiritual;
	private boolean ehFisico;
	private boolean ehFamiliar;
	private boolean ehPsicologico;
	private boolean ehMaterial;
	private boolean ehConjugal;
	private boolean ehMediunidade;
	private GravidadeDoProblema gravidade;
	private FeedBack status;
	private String descricao;

	@Transient
	public HashSet<Long> idTratamentosAgendamentos;

	public boolean isTratamentoAgendado(Long idTratamento) {
		return idTratamentosAgendamentos.contains(idTratamento);
	}

	public boolean isEhMediunidade() {
		return ehMediunidade;
	}

	public void setEhMediunidade(boolean ehMediunidade) {
		this.ehMediunidade = ehMediunidade;
	}

	public boolean isEhConjugal() {
		return ehConjugal;
	}

	public void setEhConjugal(boolean ehConjugal) {
		this.ehConjugal = ehConjugal;
	}

	public boolean isEhMaterial() {
		return ehMaterial;
	}

	public void setEhMaterial(boolean ehMaterial) {
		this.ehMaterial = ehMaterial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAssistido() {
		return idAssistido;
	}

	public void setIdAssistido(Long idAssistido) {
		this.idAssistido = idAssistido;
	}

	public String getNomeAtendente() {
		return nomeAtendente;
	}

	public void setNomeAtendente(String nomeAtendente) {
		this.nomeAtendente = nomeAtendente;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public boolean isEhEspiritual() {
		return ehEspiritual;
	}

	public void setEhEspiritual(boolean ehEspiritual) {
		this.ehEspiritual = ehEspiritual;
	}

	public boolean isEhFisico() {
		return ehFisico;
	}

	public void setEhFisico(boolean ehFisico) {
		this.ehFisico = ehFisico;
	}

	public boolean isEhFamiliar() {
		return ehFamiliar;
	}

	public void setEhFamiliar(boolean ehFamiliar) {
		this.ehFamiliar = ehFamiliar;
	}

	public boolean isEhPsicologico() {
		return ehPsicologico;
	}

	public void setEhPsicologico(boolean ehPsicologico) {
		this.ehPsicologico = ehPsicologico;
	}

	public GravidadeDoProblema getGravidade() {
		return gravidade;
	}

	public void setGravidade(GravidadeDoProblema gravidade) {
		this.gravidade = gravidade;
	}

	public FeedBack getStatus() {
		return status;
	}

	public void setStatus(FeedBack status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getShowFeedBack() {
		StringBuffer str = new StringBuffer("<select name=\"triagem.status\">");

		str.append("<option value=" + FeedBack.Não_Se_Aplica_Não_é_Retorno
				+ (FeedBack.Não_Se_Aplica_Não_é_Retorno.equals(this.status) ? " selected" : "") + ">"
				+  "Não Se Aplica (Não é Retorno)</option>");

		str.append("<option value=" + FeedBack.Continua_Igual
				+ (FeedBack.Continua_Igual.equals(this.status) ? " selected" : "") + ">"
				+ FeedBack.Continua_Igual.toString().replaceAll("_", " ") + "</option>");

		str.append("<option value=" + FeedBack.Melhorou + (FeedBack.Melhorou.equals(this.status) ? " selected" : "") + ">"
				+ FeedBack.Melhorou.toString() + "</option>");

		str.append("<option value=" + FeedBack.Piorou + (FeedBack.Piorou.equals(this.status) ? " selected" : "") + ">"
				+ FeedBack.Piorou.toString() + "</option>");

		str.append("<option value=" + FeedBack.Problema_Resolvido
				+ (FeedBack.Problema_Resolvido.equals(this.status) ? " selected" : "") + ">"
				+ FeedBack.Problema_Resolvido.toString().replaceAll("_", " ") + "</option>");
		str.append("</select>");
		return str.toString();
	}

	public String getShowGravidade() {
		StringBuffer str = new StringBuffer("<select name=\"triagem.gravidade\">");
		str.append("<option value=" + GravidadeDoProblema.Baixa
				+ (GravidadeDoProblema.Baixa.equals(this.gravidade) ? " selected" : "") + ">" + GravidadeDoProblema.Baixa
				+ "</option>");

		str.append("<option value=" + GravidadeDoProblema.Média
				+ (GravidadeDoProblema.Média.equals(this.gravidade) ? " selected" : "") + ">" + GravidadeDoProblema.Média
				+ "</option>");

		str.append("<option value=" + GravidadeDoProblema.Alta
				+ (GravidadeDoProblema.Alta.equals(this.gravidade) ? " selected" : "") + ">" + GravidadeDoProblema.Alta
				+ "</option>");

		str.append("<option value=" + GravidadeDoProblema.Altíssima
				+ (GravidadeDoProblema.Altíssima.equals(this.gravidade) ? " selected" : "") + ">"
				+ GravidadeDoProblema.Altíssima + "</option>");

		str.append("</select>");
		return str.toString();
	}

}
