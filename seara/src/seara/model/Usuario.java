package seara.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import seara.util.constantes.TipoDoUsuario;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	private String senha;

	private String nome;
	
	private TipoDoUsuario tipoUsuario;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.trim();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha.trim();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}

	public TipoDoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoDoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}