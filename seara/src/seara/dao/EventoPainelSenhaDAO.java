package seara.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import seara.model.EventoPainelSenha;
import seara.util.Formatador;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class EventoPainelSenhaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Session session;

	public EventoPainelSenhaDAO(Session session) {
		this.session = session;
	}

	public void adiciona(EventoPainelSenha t) {
		Transaction tx = session.beginTransaction();
		session.save(t);
		tx.commit();
	}

	public void atualiza(EventoPainelSenha t) {
		Transaction tx = session.beginTransaction();
		session.update(t);
		tx.commit();
	}

	public EventoPainelSenha buscaSeNaoAcharInventa(Long idTratamento) {
		Calendar hoje = Formatador.getDataHoje();
		Criterion c1 = Restrictions.eq("idTratamento", idTratamento);
		EventoPainelSenha senha = (EventoPainelSenha) session.createCriteria(EventoPainelSenha.class).add(c1).uniqueResult();

		if (senha == null) {
			senha = new EventoPainelSenha(hoje, idTratamento, true);
			adiciona(senha);
		} else if (!Formatador.ehHoje(senha.getData())) {
			senha.setData(hoje);
			senha.setConsumido(true);
			senha.setSenha("00");
			atualiza(senha);
		}

		return senha;
	}

	public EventoPainelSenha busca(Long idTratamento) {
		Criterion c1 = Restrictions.eq("idTratamento", idTratamento);
		Criterion c2 = Restrictions.between("data", Formatador.getDataHoje(), Formatador.getDataHoje());
		return (EventoPainelSenha) session.createCriteria(EventoPainelSenha.class).add(c1).add(c2).uniqueResult();
	}

	public EventoPainelSenha getPrimeiroDaFila() {
		Criterion c1 = Restrictions.eq("consumido", false);
		@SuppressWarnings("unchecked")
		List<EventoPainelSenha> eventos = session.createCriteria(EventoPainelSenha.class).add(c1).list();

		if (eventos.size() == 0) {
			return null;
		}

		EventoPainelSenha evento = Collections.min(eventos, new Comparator<EventoPainelSenha>() {
			@Override
			public int compare(EventoPainelSenha o1, EventoPainelSenha o2) {
				return o1.getIndiceNaFila().compareTo(o2.getIndiceNaFila());
			}
		});

		return evento;
	}

	public int getUltimoIndiceDaFila() {
		Criterion c1 = Restrictions.eq("consumido", false);
		@SuppressWarnings("unchecked")
		List<EventoPainelSenha> eventos = session.createCriteria(EventoPainelSenha.class).add(c1).list();

		if (eventos.size() == 0) {
			return -1;
		}

		EventoPainelSenha evento = Collections.max(eventos, new Comparator<EventoPainelSenha>() {
			@Override
			public int compare(EventoPainelSenha o1, EventoPainelSenha o2) {
				return o1.getIndiceNaFila().compareTo(o2.getIndiceNaFila());
			}
		});

		return evento.getIndiceNaFila();
	}
}
