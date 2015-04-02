package seara.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import seara.util.Formatador;

@Entity
public class Assistido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String rg;

	private String nomeCompleto;

	private Calendar dataNascimento;

	private String email;

	private String telefone;
	
	private boolean ehTrabalhador;

	// --- endereço ---
	private String numero;
	private String complemento;
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	// ----------------
	
	public void setEndereco(Endereco endereco) {
		if (endereco != null) {
			this.logradouro = endereco.getLogradouro();
			this.cep = endereco.getCep() + "";
			this.bairro = endereco.getBairro();
			this.cidade = endereco.getCidade();
			this.estado = endereco.getEstado();
		} else {
			this.logradouro = "";
			this.cep = "";
			this.bairro = "";
			this.cidade = "";
			this.estado = "";
		}
	}

	public boolean isEhTrabalhador() {
		return ehTrabalhador;
	}

	public void setEhTrabalhador(boolean ehTrabalhador) {
		this.ehTrabalhador = ehTrabalhador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = Formatador.removeCaracteresNaoAlphaNumericos(rg);

	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nome) {
		this.nomeCompleto = Formatador.formataNome(nome);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = Formatador.removeCaracteresNaoNumericos(cep);
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
