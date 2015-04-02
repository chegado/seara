package seara.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import seara.model.Tratamento;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class TratamentoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Session session;

	public TratamentoDAO(Session session) {
		this.session = session;
	}

	public void adiciona(Tratamento t) {
		Transaction tx = session.beginTransaction();
		session.save(t);
		tx.commit();
	}

	public void atualiza(Tratamento t) {
		Transaction tx = session.beginTransaction();
		session.update(t);
		tx.commit();
	}

	public Tratamento busca(Long id) {
		return (Tratamento) session.createCriteria(Tratamento.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public void remove(long idTratamento) {
		Transaction tx = session.beginTransaction();
		session.delete(busca(idTratamento));
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Tratamento> listaTodos() {
		return session.createCriteria(Tratamento.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tratamento> listaDeHoje() {
		List<Tratamento> tratamentos = session.createCriteria(Tratamento.class).list();
		Iterator<Tratamento> it = tratamentos.iterator();
		
		while(it.hasNext()) {
			Tratamento tratamento = it.next();
			
			if(!tratamento.ehRealizadoHoje()) {
				it.remove();
			}
		}
		
		return tratamentos;
	}
}
