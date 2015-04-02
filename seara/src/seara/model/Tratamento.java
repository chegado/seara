package seara.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import seara.util.Formatador;
import seara.util.constantes.DiaDaSemana;

@Entity
public class Tratamento implements Comparable<Tratamento>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String abreviacao;

	private Integer vagas;

	private boolean permitirOverbooking;

	private int diasDaSemana;

	private String nome;

	private String msgSenha;

	private String msgComprovante;

	public void adicionaDia(int diaDaSemana) {
		diasDaSemana |= (1 << diaDaSemana);
	}

	public boolean ehRealizadoNoDia(int diaDaSemana) {
		return (diasDaSemana & (1 << diaDaSemana)) > 0;
	}
	
	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	public boolean ehRealizadoHoje() {
		int hoje = Formatador.getDataHoje().get(Calendar.DAY_OF_WEEK);
		return ehRealizadoNoDia(hoje);
	}
	
	public boolean isRealizadoDom() {
		return ehRealizadoNoDia(1);
	}

	public boolean isRealizadoSeg() {
		return ehRealizadoNoDia(2);
	}

	public boolean isRealizadoTer() {
		return ehRealizadoNoDia(3);
	}

	public boolean isRealizadoQua() {
		return ehRealizadoNoDia(4);
	}

	public boolean isRealizadoQui() {
		return ehRealizadoNoDia(5);
	}

	public boolean isRealizadoSex() {
		return ehRealizadoNoDia(6);
	}

	public boolean isRealizadoSab() {
		return ehRealizadoNoDia(7);
	}

	public List<DiaDaSemana> getListaDias() {
		LinkedList<DiaDaSemana> dias = new LinkedList<DiaDaSemana>();

		for (int i = 1; i <= 7; i++) {
			if (ehRealizadoNoDia(i)) {
				dias.add(Formatador.diaDaSemana[i]);
			}
		}
		
		return dias;
	}

	public boolean isVagasIlimitadas() {
		System.out.println(vagas == -1);
		return vagas == -1;
	}
	
	public Integer getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) {
		this.vagas = vagas;
	}

	public boolean isPermitirOverbooking() {
		return permitirOverbooking;
	}

	public void setPermitirOverbooking(boolean permitirOverbooking) {
		this.permitirOverbooking = permitirOverbooking;
	}

	public int getDiasDaSemana() {
		return diasDaSemana;
	}

	public void setDiasDaSemana(int diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMsgSenha() {
		return msgSenha;
	}

	public void setMsgSenha(String msgSenha) {
		this.msgSenha = msgSenha;
	}

	public String getMsgComprovante() {
		return msgComprovante;
	}

	public void setMsgComprovante(String msgComprovante) {
		this.msgComprovante = msgComprovante;
	}

	public int compareTo(Tratamento tratamento) {
		return nome.compareTo(tratamento.nome);
	}
}
