package seara.dao;

import java.io.Serializable;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import seara.model.Senha;
import seara.util.Formatador;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class SenhaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Session session;

	public SenhaDAO(Session session) {
		this.session = session;
	}

	public void adiciona(Senha t) {
		Transaction tx = session.beginTransaction();
		session.save(t);
		tx.commit();
	}

	public void atualiza(Senha t) {
		Transaction tx = session.beginTransaction();
		session.update(t);
		tx.commit();
	}

	public Senha busca(Long idTratamento) {
		Calendar hoje = Formatador.getDataHoje();
		Criterion c1 = Restrictions.eq("idTratamento", idTratamento);
		Criterion c2 = Restrictions.between("data", hoje, hoje);
		return (Senha) session.createCriteria(Senha.class).add(c1).add(c2).uniqueResult();
	}
		
	public Senha buscaSeNaoAcharInventa(Long idTratamento) {
		Calendar hoje = Formatador.getDataHoje();
		Criterion c1 = Restrictions.eq("idTratamento", idTratamento);
		Senha senha = (Senha) session.createCriteria(Senha.class).add(c1).uniqueResult();

		if (senha == null) {
			senha = new Senha(hoje, idTratamento);
			adiciona(senha);
		} else if (!Formatador.ehHoje(senha.getData())) {
			senha.setData(hoje);
			senha.setUltimaChamada(0);
			senha.setUltimaEmitida(0);
			atualiza(senha);
		}

		return senha;
	}
}
