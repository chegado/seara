package seara.model;

import java.io.Serializable;

public class ItemRelatorioAgendamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nomeTratamento;
	private String vagas;
	private int qtdAgendamentos;

	public String getNomeTratamento() {
		return nomeTratamento;
	}

	public void setNomeTratamento(String nomeTratamento) {
		this.nomeTratamento = nomeTratamento;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

	public int getQtdAgendamentos() {
		return qtdAgendamentos;
	}

	public void setQtdAgendamentos(int qtdAgendamentos) {
		this.qtdAgendamentos = qtdAgendamentos;
	}
}
