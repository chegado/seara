package seara.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class CriadorDeSession implements ComponentFactory<Session>, Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * public static Session getSession() { AnnotationConfiguration
	 * configuration = new AnnotationConfiguration(); configuration.configure();
	 * 
	 * SessionFactory factory = configuration.buildSessionFactory(); Session
	 * session = factory.openSession(); return session; }
	 */

	private final SessionFactory factory;
	private Session session;

	public CriadorDeSession(SessionFactory factory) {
		this.factory = factory;
	}

	@PostConstruct
	public void abre() {
		this.session = factory.openSession();
	}

	public Session getInstance() {
		return this.session;
	}

	@PreDestroy
	public void fecha() {
		this.session.close();
	}

}
