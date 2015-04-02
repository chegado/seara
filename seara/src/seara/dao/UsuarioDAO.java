package seara.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import seara.model.Usuario;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Session session;

	public UsuarioDAO(Session session) {
		this.session = session;
	}

	public boolean existeUsuario(Usuario usuario) {
		Usuario encontrado = (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("login", usuario.getLogin())).uniqueResult();
		return encontrado != null;
	}

	public void adiciona(Usuario usuario) {
		Transaction tx = this.session.beginTransaction();
		this.session.save(usuario);
		tx.commit();
	}

	public Usuario busca(Usuario usuario) {
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("login", usuario.getLogin()))
				.add(Restrictions.eq("senha", usuario.getSenha())).uniqueResult();
	}

	public Usuario busca(String login) {
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("login", login)).uniqueResult();
	}

	public void remove(String login) {
		Transaction tx = session.beginTransaction();
		session.delete(busca(login));
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listaTodos() {
		return session.createCriteria(Usuario.class).list();
	}
}