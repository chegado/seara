package seara.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import seara.model.Endereco;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EnderecoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Session session;

	public EnderecoDAO(Session session) {
		this.session = session;
	}

	public Endereco busca(String cep) {
		return (Endereco) session.createCriteria(Endereco.class).add(Restrictions.eq("cep", Integer.parseInt(cep))).uniqueResult();
	}
}
