package seara.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import seara.model.Agendamento;
import seara.model.Triagem;
import seara.util.Formatador;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class TriagemDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Session session;
	private final AgendamentoDAO agendamentoDAO;

	public TriagemDAO(Session session, AgendamentoDAO agendamentoDAO) {
		this.session = session;
		this.agendamentoDAO = agendamentoDAO;
	}

	public Long adiciona(Triagem a) {
		Transaction tx = session.beginTransaction();
		session.save(a);
		tx.commit();
		return a.getId();
	}

	public void atualiza(Triagem a) {
		Transaction tx = session.beginTransaction();
		session.update(a);
		tx.commit();
	}

	public void remove(long id) {
		Transaction tx = session.beginTransaction();
		session.delete(busca(id));
		tx.commit();
	}

	public Triagem preencheTratamentos(Triagem triagem) {
		if (triagem == null) {
			return null;
		}

		triagem.idTratamentosAgendamentos = new HashSet<Long>();

		for (Agendamento agendamento : agendamentoDAO.listaAgendamentosDaTriagem(triagem.getId())) {
			triagem.idTratamentosAgendamentos.add(agendamento.getIdTratamento());
		}

		return triagem;
	}

	public Triagem busca(long id) {
		return preencheTratamentos((Triagem) session.createCriteria(Triagem.class).add(Restrictions.eq("id", id))
				.uniqueResult());
	}

	public Triagem buscaDataHoje(Long idAssistido) {
		Calendar hoje = Formatador.getDataHoje();
		Criterion c1 = Restrictions.eq("idAssistido", idAssistido);
		Criterion c2 = Restrictions.between("data", hoje, hoje);
		return preencheTratamentos((Triagem) session.createCriteria(Triagem.class).add(c1).add(c2).uniqueResult());
	}

	@SuppressWarnings("unchecked")
	public Triagem buscaMaisRecente(Long idAssistido) {
		Triagem triagem = buscaDataHoje(idAssistido);

		if (triagem != null) {
			return preencheTratamentos(triagem);
		}

		Criterion c1 = Restrictions.eq("idAssistido", idAssistido);
		List<Triagem> list = (List<Triagem>) session.createCriteria(Triagem.class).add(c1).addOrder(Order.desc("data"))
				.setMaxResults(1).list();

		if (list != null && list.size() != 0) {
			return preencheTratamentos(list.get(0));
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Triagem> listaTodasDoAssistido(Long idAssistido) {
		Criterion c1 = Restrictions.eq("idAssistido", idAssistido);
		List<Triagem> triagens = session.createCriteria(Triagem.class).add(c1).list();

		for (Triagem triagem : triagens) {
			preencheTratamentos(triagem);
		}

		Collections.sort(triagens, new Comparator<Triagem>() {
			@Override
			public int compare(Triagem t1, Triagem t2) {
				return t1.getData().compareTo(t2.getData());
			}
		});

		return triagens;
	}
}
