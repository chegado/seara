package seara.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import seara.model.Agendamento;
import seara.model.Tratamento;
import seara.util.Formatador;
import seara.util.constantes.StatusDoAgendamento;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class AgendamentoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Session session;
	private static final int QTD_MAX_FINALIZADOS = 20;

	public AgendamentoDAO(Session session) {
		this.session = session;
	}

	public Long adiciona(Agendamento a) {
		Transaction tx = session.beginTransaction();
		session.save(a);
		tx.commit();
		return a.getId();
	}

	public void atualiza(Agendamento a) {
		Transaction tx = session.beginTransaction();
		session.update(a);
		tx.commit();
	}

	public void remove(long idAgendamento) {
		Transaction tx = session.beginTransaction();
		session.delete(busca(idAgendamento));
		tx.commit();
	}

	public Agendamento busca(long idAgendamento) {
		return (Agendamento) session.createCriteria(Agendamento.class).add(Restrictions.eq("id", idAgendamento))
				.uniqueResult();
	}

	private List<Agendamento> ordena(List<Agendamento> agendamentos) {
		Collections.sort(agendamentos, new Comparator<Agendamento>() {
			@Override
			public int compare(Agendamento a0, Agendamento a1) {
				if (a0.getDataAtendimento() == null && a1.getDataAtendimento() != null) {
					return 1;
				}

				if (a0.getDataAtendimento() != null && a1.getDataAtendimento() == null) {
					return -1;
				}

				if ((a0.getDataAtendimento() == null && a1.getDataAtendimento() == null)
						|| a0.getDataAtendimento().compareTo(a1.getDataAtendimento()) == 0) {
					if (a0.getDataAgendamento().compareTo(a1.getDataAgendamento()) == 0) {
						return a0.getNomeTratamento().compareTo(a1.getNomeTratamento());
					} else {
						return a0.getDataAgendamento().compareTo(a1.getDataAgendamento());
					}
				}

				if (a0.getDataAtendimento().compareTo(a1.getDataAtendimento()) == 0) {
					return a0.getNomeTratamento().compareTo(a1.getNomeTratamento());
				} else {
					return a0.getDataAtendimento().compareTo(a1.getDataAtendimento());
				}
			}
		});

		return agendamentos;
	}

	@SuppressWarnings("unchecked")
	public List<Agendamento> listaTodosAgendamentosNaData(Calendar data) {
		Criterion c1 = Restrictions.between("dataAtendimento", data, data);
		return session.createCriteria(Agendamento.class).add(c1).list();
	}

	@SuppressWarnings("unchecked")
	public List<Agendamento> listaTodosAgendamentosDoAssistido(Long idAssistido) {
		Criterion c1 = Restrictions.eq("idAssistido", idAssistido);
		return session.createCriteria(Agendamento.class).add(c1).list();
	}

	@SuppressWarnings("unchecked")
	public List<Agendamento> listaAgendamentosDaTriagem(Long idTriagem) {
		Criterion c1 = Restrictions.eq("idTriagem", idTriagem);
		return ordena(session.createCriteria(Agendamento.class).add(c1).list());
	}

	@SuppressWarnings("unchecked")
	public int getNumAgendamentos(Calendar data, Tratamento tratamento) {
		Criterion c1 = Restrictions.eq("dataAtendimento", data);
		Criterion c2 = Restrictions.eq("idTratamento", tratamento.getId());
		List<Agendamento> agendamentos = session.createCriteria(Agendamento.class).add(c1).add(c2).list();
		return agendamentos.size();
	}

	private void addAcaoRemarcarData(Agendamento a) {
		a.getListaAcoes().addLast(
				"<a href=\"/seara/allviews/marcarDataAgendamento1?idAgendamento=" + a.getId() + "\">REMARCAR DATA</a>");
	}

	private void addAcaoReemitirComprovante(Agendamento a) {
		a.getListaAcoes().addLast(
				"<a href=\"/seara/allviews/imprimeComprovante?idAgendamento=" + a.getId() + "\">REIMPRIMIR COMPROVANTE</a>");
	}

	private void addAcaoEmitirSenha(Agendamento a, boolean ehPrioritaria) {
		if (ehPrioritaria) {
			a.getListaAcoes().addLast(
					"<a href=\"/seara/allviews/imprimeSenha?idAgendamento=" + a.getId() + "&ehPrioritario=true"
							+ "\">EMITIR SENHA PRIORITÁRIA</a>");
		} else {
			a.getListaAcoes().addLast(
					"<a href=\"/seara/allviews/imprimeSenha?idAgendamento=" + a.getId() + "&ehPrioritario=false"
							+ "\">EMITIR SENHA</a>");
		}
	}

	private void addAcaoReimprimirSenha(Agendamento a) {
		a.getListaAcoes().addLast(
				"<a href=\"/seara/allviews/imprimeSenha?idAgendamento=" + a.getId() + "\">REIMPRIMIR SENHA</a>");
	}

	private void addAcaoCancelar(Agendamento a) {
		a.getListaAcoes().addLast(
				"<a href=\"/seara/allviews/cancelarAgendamento?idAgendamento=" + a.getId() + "\">CANCELAR</a>");
	}

	private void addAcaoMarcarData(Agendamento a) {
		a.getListaAcoes().addLast(
				"<a href=\"/seara/allviews/marcarDataAgendamento1?idAgendamento=" + a.getId() + "\">MARCAR DATA</a>");
	}

	public boolean existeAgendamento(Long idTratamento, String data, Long idAssistido) {
		Calendar dt = Formatador.fromStringToCalendar(data);
		Criteria criteria = session.createCriteria(Agendamento.class);
		criteria.add(Restrictions.between("dataAtendimento", dt, dt));
		criteria.add(Restrictions.eq("idTratamento", idTratamento));
		criteria.add(Restrictions.eq("idAssistido", idAssistido));
		return criteria.setMaxResults(1).list().size() > 0;
	}

	private boolean marcadaPraHoje(Agendamento a) {
		Calendar hoje = Formatador.getDataHoje();
		return a.getStatus().equals(StatusDoAgendamento.Data_Marcada)
				&& a.getDataAtendimento().get(Calendar.DAY_OF_MONTH) == hoje.get(Calendar.DAY_OF_MONTH)
				&& a.getDataAtendimento().get(Calendar.MONTH) == hoje.get(Calendar.MONTH)
				&& a.getDataAtendimento().get(Calendar.YEAR) == hoje.get(Calendar.YEAR);
	}

	public void atualizaStatus() {
		Criteria criteria = session.createCriteria(Agendamento.class);
		Criterion c1 = Restrictions.eq("status", StatusDoAgendamento.Data_Marcada);
		Criterion c2 = Restrictions.eq("status", StatusDoAgendamento.Senha_Emitida);
		criteria.add(Restrictions.or(c1, c2));
		criteria.add(Restrictions.lt("dataAtendimento", Formatador.getDataHoje()));

		@SuppressWarnings("unchecked")
		List<Agendamento> agendamentos = (List<Agendamento>) criteria.list();

		for (Agendamento a : agendamentos) {
			if (a.getStatus().equals(StatusDoAgendamento.Senha_Emitida)) {
				a.setStatus(StatusDoAgendamento.Finalizado);
				atualiza(a);
			} else if (a.getStatus().equals(StatusDoAgendamento.Data_Marcada)) {
				a.setStatus(StatusDoAgendamento.Assistido_Faltou);
				atualiza(a);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Agendamento> listaFinalizados(Long idAssistido) {
		Criterion c1 = Restrictions.eq("idAssistido", idAssistido);
		Criterion c2 = Restrictions.eq("status", StatusDoAgendamento.Finalizado);
		List<Agendamento> finalizados = session.createCriteria(Agendamento.class).add(c1).add(c2).list();
		ordena(finalizados);
		Collections.reverse(finalizados);
		return finalizados.subList(0, Math.min(finalizados.size(), QTD_MAX_FINALIZADOS));
	}

	public List<Agendamento> listaAgendadas(Long idAssistido) {
		Criterion c1 = Restrictions.eq("idAssistido", idAssistido);
		Criterion c2 = Restrictions.eq("status", StatusDoAgendamento.Data_Marcada);
		Criterion c3 = Restrictions.eq("status", StatusDoAgendamento.Senha_Emitida);
		Criterion c4 = Restrictions.or(c2, c3);

		@SuppressWarnings("unchecked")
		List<Agendamento> agendadas = session.createCriteria(Agendamento.class).add(c1).add(c4).list();

		for (Agendamento a : agendadas) {
			if (a.getStatus().equals(StatusDoAgendamento.Senha_Emitida)) {
				addAcaoReimprimirSenha(a);
			} else {
				if (marcadaPraHoje(a)) {
					addAcaoEmitirSenha(a, false);
					addAcaoEmitirSenha(a, true);
				}

				addAcaoReemitirComprovante(a);
				addAcaoRemarcarData(a);
				addAcaoCancelar(a);
			}
		}

		return ordena(agendadas);
	}

	public List<Agendamento> listaPendentes(Long idAssistido) {
		Criterion c1 = Restrictions.eq("idAssistido", idAssistido);
		Criterion c2 = Restrictions.eq("status", StatusDoAgendamento.Assistido_Faltou);
		Criterion c3 = Restrictions.eq("status", StatusDoAgendamento.Data_Não_Marcada);
		Criterion c4 = Restrictions.or(c2, c3);

		@SuppressWarnings("unchecked")
		List<Agendamento> pendentes = session.createCriteria(Agendamento.class).add(c1).add(c4).list();

		for (Agendamento a : pendentes) {
			if (a.getStatus().equals(StatusDoAgendamento.Data_Não_Marcada)) {
				addAcaoMarcarData(a);
			} else if (a.getStatus().equals(StatusDoAgendamento.Assistido_Faltou)) {
				addAcaoRemarcarData(a);
			}

			addAcaoCancelar(a);
		}

		return ordena(pendentes);
	}
}
