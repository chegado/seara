package seara.util;

import java.io.Serializable;

import seara.dao.EventoPainelSenhaDAO;
import seara.dao.SenhaDAO;
import seara.model.EventoPainelSenha;
import seara.model.Senha;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class GerenciadorDeSenhas implements Serializable {

	private static final long serialVersionUID = 1L;
	private final SenhaDAO senhaDAO;
	private final EventoPainelSenhaDAO eventoPainelSenhaDAO;

	public GerenciadorDeSenhas(SenhaDAO senhaDAO, EventoPainelSenhaDAO eventoPainelSenhaDAO) {
		this.senhaDAO = senhaDAO;
		this.eventoPainelSenhaDAO = eventoPainelSenhaDAO;
	}

	public String geraProximaSenha(Long idTratamento) {
		Senha senha = senhaDAO.buscaSeNaoAcharInventa(idTratamento);
		int proximaSenha = senha.getUltimaEmitida() + 1;
		senha.setUltimaEmitida(proximaSenha);
		senhaDAO.atualiza(senha);
		return formata(proximaSenha);
	}

	private String formata(int senha) {
		return senha < 10 ? "0" + senha : senha + "";
	}

	public String getUltimaSenhaEmitida(Long idTratamento) {
		return formata(senhaDAO.buscaSeNaoAcharInventa(idTratamento).getUltimaEmitida());
	}

	public EventoPainelSenha consomeProximoEvento() {
		EventoPainelSenha evento = eventoPainelSenhaDAO.getPrimeiroDaFila();

		if (evento == null) {
			return null;
		}

		evento.setQuantasVezesPiscou(evento.getQuantasVezesPiscou() + 1);

		if (evento.getQuantasVezesPiscou() >= EventoPainelSenha.QUANTAS_VEZES_TEM_QUE_PISCAR) {
			if (evento.getQuantasVezesChamou() >= EventoPainelSenha.QUANTAS_VEZES_TEM_QUE_CHAMAR) {
				evento.setConsumido(true);
			} else {
				evento.setQuantasVezesChamou(evento.getQuantasVezesChamou() + 1);
				evento.setQuantasVezesPiscou(0);
				evento.setIndiceNaFila(eventoPainelSenhaDAO.getUltimoIndiceDaFila() + 1);
			}
		}

		eventoPainelSenhaDAO.atualiza(evento);
		return evento;
	}

	public String getUltimaSenhaChamada(Long idTratamento) {
		return eventoPainelSenhaDAO.buscaSeNaoAcharInventa(idTratamento).getSenha();
	}

	public void chamaProximasSenhas(Long idTratamento, int qtd) {
		EventoPainelSenha evento = eventoPainelSenhaDAO.buscaSeNaoAcharInventa(idTratamento);
		Senha senha = senhaDAO.buscaSeNaoAcharInventa(idTratamento);
		qtd = Math.min(senha.getUltimaEmitida() - senha.getUltimaChamada(), qtd);

		System.out.println("QTD: " + qtd);
		
		if (qtd < 0) {
			senha.setUltimaChamada(Math.max(0, senha.getUltimaChamada() + qtd));
			evento.setConsumido(true);
			evento.setSenha(formata(senha.getUltimaChamada()));
			evento.setUmNumeroSoh(true);
			senhaDAO.atualiza(senha);
			eventoPainelSenhaDAO.atualiza(evento);
			return;
		}

		if (qtd > 0) {
			int de = senha.getUltimaChamada() + 1;
			int ao = senha.getUltimaChamada() + qtd;

			// atualiza senha
			senha.setUltimaChamada(ao);
			senhaDAO.atualiza(senha);

			// cria evento para mostrar a senha no painel
			if (de == ao) {
				evento.setSenha(formata(de));
				evento.setUmNumeroSoh(true);
			} else {
				evento.setSenha(formata(de) + " ao " + formata(ao));
				evento.setUmNumeroSoh(false);
			}
		}

		// if (qtd == 0) {
		// apenas cria o evento com a mesma senha de antes
		// }

		evento.setIndiceNaFila(eventoPainelSenhaDAO.getUltimoIndiceDaFila() + 1);
		evento.setConsumido(false);
		evento.setQuantasVezesPiscou(0);
		evento.setQuantasVezesChamou(0);
		eventoPainelSenhaDAO.atualiza(evento);
	}
}
