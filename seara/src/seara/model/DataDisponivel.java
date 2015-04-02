package seara.model;

import java.io.Serializable;

import seara.util.constantes.DiaDaSemana;

public class DataDisponivel implements Serializable {

	private static final long serialVersionUID = 1L;
	private DiaDaSemana diaDaSemana;
	private String data;
	private int vagas;
	private int nAgendamentos;

	public DataDisponivel(DiaDaSemana diaDaSemana, String data, int vagas, int nAgendamentos) {
		this.diaDaSemana = diaDaSemana;
		this.data = data;
		this.vagas = vagas;
		this.nAgendamentos = nAgendamentos;
	}

	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public int getnAgendamentos() {
		return nAgendamentos;
	}

	public void setnAgendamentos(int nAgendamentos) {
		this.nAgendamentos = nAgendamentos;
	}

	@Override
	public String toString() {
		if (vagas == -1) {
			return data + " - " + diaDaSemana;

		} else {
			return data + " - " + diaDaSemana + " (vagas:" + vagas + " agendamentos:" + nAgendamentos + ")";
		}
	}
}
