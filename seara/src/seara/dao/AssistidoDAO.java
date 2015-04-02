package seara.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import seara.model.Assistido;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class AssistidoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Session session;

	private final AgendamentoDAO agendamentoDAO;

	public AssistidoDAO(Session session, AgendamentoDAO agendamentoDAO) {
		this.session = session;
		this.agendamentoDAO = agendamentoDAO;
	}

	public void adiciona(Assistido a) {
		Transaction tx = this.session.beginTransaction();
		this.session.save(a);
		tx.commit();
	}

	public void atualiza(Assistido p) {
		Transaction tx = session.beginTransaction();
		session.update(p);
		tx.commit();
	}

	public void remove(Assistido p) {
		Transaction tx = session.beginTransaction();
		session.delete(p);
		tx.commit();
	}

	public void remove(Long idAssistido) {
		Transaction tx = session.beginTransaction();
		session.delete(buscaPorId(idAssistido));
		tx.commit();
	}

	public Assistido buscaPorId(Long id) {
		return (Assistido) session.createCriteria(Assistido.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public boolean jahExiste(Long id) {
		return ((Long) session.createQuery("select count(*) from Assistido where id=" + id).uniqueResult()) > 0;
	}

	public Assistido buscaPorRg(String rg) {
		return (Assistido) session.createCriteria(Assistido.class).add(Restrictions.eq("rg", rg)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Assistido> buscaPorNome(String nomeCompleto) {
		Criterion c1 = Restrictions.eq("nomeCompleto", nomeCompleto);
		return (List<Assistido>) session.createCriteria(Assistido.class).add(c1).list();
	}

	public Assistido buscaPorNomeDataNascimento(String nomeCompleto, Calendar dataNascimento) {
		Criterion c1 = Restrictions.between("dataNascimento", dataNascimento, dataNascimento);
		Criterion c2 = Restrictions.eq("nomeCompleto", nomeCompleto);
		return (Assistido) session.createCriteria(Assistido.class).add(c1).add(c2).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Assistido> listaTodos() {
		return (List<Assistido>) session.createCriteria(Assistido.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Assistido> lista(String parteDoNome, String ehTrabalhador, Long idTratamento, String data) {
		Criteria criteria = session.createCriteria(Assistido.class);

		if (parteDoNome != null && !parteDoNome.trim().equals("")) {
			criteria.add(Restrictions.like("nomeCompleto", "%" + parteDoNome.trim() + "%"));
		}

		if (ehTrabalhador != null && ehTrabalhador.equals("sim")) {
			criteria.add(Restrictions.eq("ehTrabalhador", true));
		}

		if (ehTrabalhador != null && ehTrabalhador.equals("nao")) {
			criteria.add(Restrictions.eq("ehTrabalhador", false));
		}

		List<Assistido> assistidos = (List<Assistido>) criteria.addOrder(Order.asc("nomeCompleto")).list();

		if (idTratamento != null && idTratamento != -1 && data != null && !data.trim().equals("")) {
			Iterator<Assistido> it = assistidos.iterator();

			while (it.hasNext()) {
				Assistido assistido = it.next();

				if (!agendamentoDAO.existeAgendamento(idTratamento, data, assistido.getId())) {
					it.remove();
				}
			}
		}

		return assistidos;
	}
}
