package seara.model;

import java.io.Serializable;

import seara.util.Formatador;
import seara.util.constantes.TipoDoUsuario;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class UsuarioSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean logado;

	private String nome = "";
	
	private TipoDoUsuario tipoUsuario;

	public void login(Usuario usuario) {
		this.logado = true;
		nome = usuario.getNome();
		tipoUsuario = usuario.getTipoUsuario();
	}

	public void logout() {
		this.logado = false;
		this.nome = "";
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoDoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public String getHoje() {
		return Formatador.fromCalendarToString(Formatador.getDataHoje());
	}
}