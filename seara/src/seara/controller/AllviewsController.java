package seara.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import seara.dao.AgendamentoDAO;
import seara.dao.AssistidoDAO;
import seara.dao.EnderecoDAO;
import seara.dao.PlantaoDAO;
import seara.dao.TratamentoDAO;
import seara.dao.TriagemDAO;
import seara.dao.UsuarioDAO;
import seara.model.Agendamento;
import seara.model.Assistido;
import seara.model.AssistidoSession;
import seara.model.EstatisticaSenha;
import seara.model.EventoPainelSenha;
import seara.model.ItemRelatorioAgendamentos;
import seara.model.Tratamento;
import seara.model.Triagem;
import seara.model.Usuario;
import seara.model.UsuarioSession;
import seara.util.Formatador;
import seara.util.GerenciadorDeSenhas;
import seara.util.constantes.StatusDoAgendamento;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class AllviewsController implements Serializable {

	private static final long serialVersionUID = 1L;
	private final UsuarioDAO usuarioDAO;
	private final Result result;
	private final Validator validator;
	private final UsuarioSession usuarioSession;
	private final AgendamentoDAO agendamentoDAO;
	private final TratamentoDAO tratamentoDAO;
	private final AssistidoDAO assistidoDAO;
	private final EnderecoDAO enderecoDAO;
	private final PlantaoDAO plantaoDAO;
	private final GerenciadorDeSenhas gerenciadorDeSenhas;
	private final AssistidoSession assistidoSession;
	private final TriagemDAO triagemDAO;

	public AllviewsController(TriagemDAO triagemDAO, AssistidoSession assistidoSession,
			GerenciadorDeSenhas gerenciadorDeSenhas, PlantaoDAO plantaoDAO, EnderecoDAO enderecoDAO, UsuarioDAO usuarioDAO,
			Validator validator, UsuarioSession usuarioWeb, Result result, AgendamentoDAO agendamentoDAO,
			TratamentoDAO tratamentoDAO, AssistidoDAO assistidoDAO) {
		this.assistidoSession = assistidoSession;
		this.result = result;
		this.agendamentoDAO = agendamentoDAO;
		this.tratamentoDAO = tratamentoDAO;
		this.assistidoDAO = assistidoDAO;
		this.usuarioSession = usuarioWeb;
		this.validator = validator;
		this.usuarioDAO = usuarioDAO;
		this.enderecoDAO = enderecoDAO;
		this.plantaoDAO = plantaoDAO;
		this.gerenciadorDeSenhas = gerenciadorDeSenhas;
		this.triagemDAO = triagemDAO;
	}

	public void proximoDia() {
		Formatador.TIRA_ESSA_GAMBI_PELAMOR++;
		result.forwardTo(this).sair();
	}

	public void diaAnterior() {
		Formatador.TIRA_ESSA_GAMBI_PELAMOR--;
		result.forwardTo(this).sair();
	}

	public void vazia() {
	}

	// --- CHAMADOR DE SENHAS ---
	@RestritoAuxiliarExterno
	public void filaDeEspera() {
		List<EstatisticaSenha> estatisticasSenhas = new LinkedList<EstatisticaSenha>();
		List<Tratamento> tratamentosDeHoje = tratamentoDAO.listaDeHoje();

		for (Tratamento tratamento : tratamentosDeHoje) {
			estatisticasSenhas
					.add(new EstatisticaSenha(tratamento.getNome(), tratamento.getId(), gerenciadorDeSenhas
							.getUltimaSenhaChamada(tratamento.getId()), gerenciadorDeSenhas.getUltimaSenhaEmitida(tratamento
							.getId())));
		}
		result.include("estatisticasSenhas", estatisticasSenhas);
	}

	public void chamarProximo(Long idTratamento, Integer qtdSenhas) {
		tratamentoDAO.busca(idTratamento).getAbreviacao();
		gerenciadorDeSenhas.chamaProximasSenhas(idTratamento, qtdSenhas);
		result.forwardTo(this).filaDeEspera();
	}

	public void atualizaPainel() {
		EventoPainelSenha evento = gerenciadorDeSenhas.consomeProximoEvento();

		if (evento == null) {
			result.include("nomeTratamento", "Seara Esperança");
			result.include("senha", "Oi!");
			result.include("ehUmNumeroSoh", true);
		} else {
			result.include("nomeTratamento", tratamentoDAO.busca(evento.getIdTratamento()).getAbreviacao());

			if (evento.isUmNumeroSoh()) {
				result.include("senha", evento.getSenha());
			} else {
				result.include("senhaDe", evento.getSenha().substring(0, 2));
				result.include("senhaAo", evento.getSenha().substring(6, 8));
			}
			result.include("ehUmNumeroSoh", evento.isUmNumeroSoh());
		}
	}

	public void painel() {
	}

	// -------------------------

	// --- FUNÇÕES AUXILIARES ---
	public void imprimir() {
		result.forwardTo(this).logoutAssistido1("listaAgendamentos");
	}

	public void mostraMensagem(String mensagem, String urlRetorno) {
		result.include("mensagem", mensagem);
		result.include("urlRetorno", urlRetorno);
	}

	private boolean colocaNovoAssistidoNaSession(String rg, String nomeCompleto) {
		result.include("rg", rg);
		result.include("nomeCompleto", rg);

		if (rg.trim().equals("")) {
			rg = null;
		}

		if (nomeCompleto.trim().equals("")) {
			nomeCompleto = null;
		}

		if (rg == null && nomeCompleto == null) {
			validator.add(new ValidationMessage("Informe o RG ou o nome completo.", ""));
			return false;
		}

		if (rg != null) {
			rg = Formatador.removeCaracteresNaoAlphaNumericos(rg);
			Assistido assistido = assistidoDAO.buscaPorRg(rg);

			if (assistido == null) {
				validator.add(new ValidationMessage("O RG " + rg + " não está cadastrado.", ""));
				return false;
			} else {
				assistidoSession.setAssistido(assistido);
				return true;
			}
		} else {
			List<Assistido> assistidos = assistidoDAO.buscaPorNome(Formatador.formataNome(nomeCompleto));

			if (assistidos.size() == 0) {
				validator.add(new ValidationMessage(
						"O assistido não foi encontrado na base de dados. <br> Verifique se houve erro de digitação.", ""));
				return false;
			} else if (assistidos.size() == 1) {
				assistidoSession.setAssistido(assistidos.get(0));
				return true;
			} else {
				validator.add(new ValidationMessage("Há mais do que 1 assistido cadastrado com o nome de <b>"
						+ assistidos.get(0).getNomeCompleto() + "</b>.", ""));
				return false;
			}
		}
	}

	// --------------------------

	// --- LOGIN OPERADOR ---
	public void loginOperador() {
		agendamentoDAO.atualizaStatus();
	}

	public void sair() {
		usuarioSession.logout();
		assistidoSession.setAssistido(null);
		result.redirectTo(this).loginOperador();
	}

	public void entrar(Usuario usuario) {
		Usuario carregado = usuarioDAO.busca(usuario);

		if (carregado == null) {
			validator.add(new ValidationMessage("Usuário Não Cadastrado ou Senha Inválida", ""));
			validator.onErrorUsePageOf(this).loginOperador();
			return;
		}

		usuarioSession.login(carregado);

		result.redirectTo(this)
				.mostraMensagem(
						"Olá, "
								+ carregado.getNome()
								+ "!<br />Obrigado por fazer parte da família<br /> <b>Lar Seara Esperaça Irmã Matilde!</b> <br />Bom Trabalho!",
						"vazia");
	}

	// -------------

	// --- LOGIN ASSISTIDO ---
	@RestritoRecepcao
	public void logoutAssistido1(String urlNao) {
		result.include("urlNao", urlNao);
	}

	@RestritoRecepcao
	public void logoutAssistido2(String origemRequest) {
		result.include("origemRequest", origemRequest);
		assistidoSession.setAssistido(null);
		result.redirectTo(this).vazia();
	}

	@RestritoRecepcao
	public void confirmarNomeAssistido(String urlCorreto, String origemRequest) {
		if (assistidoSession.getAssistido() == null) {
			result.forwardTo(this).loginAssistido1(origemRequest);
		} else {
			result.include("urlCorreto", urlCorreto);
			result.include("origemRequest", origemRequest);
		}
	}

	@RestritoRecepcao
	public void loginAssistido1(String origemRequest) {
		result.include("origemRequest", origemRequest);
	}

	@RestritoRecepcao
	public void loginAssistido2(String action, String rg, String nomeCompleto, String origemRequest) {
		if ("Iniciar Atendimento".equals(action)) {
			if (colocaNovoAssistidoNaSession(rg, nomeCompleto)) {
				if (origemRequest == null || origemRequest.trim().equals("")) {
					result.forwardTo(this).confirmarNomeAssistido("vazia", origemRequest);
				} else if ("Triagem".equals(origemRequest)) {
					// result.forwardTo(this).novaTriagem1(null);
					result.forwardTo(this).confirmarNomeAssistido("novaTriagem1", origemRequest);
				} else if ("Agendamento".equals(origemRequest)) {
					// result.forwardTo(this).listaAgendamentos();
					result.forwardTo(this).confirmarNomeAssistido("listaAgendamentos", origemRequest);
				} else {
					result.forwardTo(this).confirmarNomeAssistido("vazia", origemRequest);
				}
			} else {
				result.include("origemRequest", origemRequest);
				validator.onErrorUsePageOf(this).loginAssistido1(origemRequest);
			}
		} else {
			result.redirectTo(this).novoAssistido1(null, null, null, null);
		}
	}

	// ----------------------

	// --- RELATÓRIO ---
	@RestritoRecepcao
	public void listaTodosAgendamentos1() {
	}

	@RestritoRecepcao
	public void listaTodosAgendamentos2(String data) {
		if (data == null || data.trim().length() == 0) {
			validator.add(new ValidationMessage("Escolha uma data.", ""));
			validator.onErrorUsePageOf(this).listaTodosAgendamentos1();
			return;
		}

		List<ItemRelatorioAgendamentos> itens = new LinkedList<ItemRelatorioAgendamentos>();
		List<Agendamento> agendamentos = agendamentoDAO.listaTodosAgendamentosNaData(Formatador.fromStringToCalendar(data));
		HashMap<Long, ItemRelatorioAgendamentos> map = new HashMap<Long, ItemRelatorioAgendamentos>();

		for (Agendamento a : agendamentos) {
			if (!map.containsKey(a.getIdTratamento())) {
				ItemRelatorioAgendamentos item = new ItemRelatorioAgendamentos();
				Tratamento tratamento = tratamentoDAO.busca(a.getIdTratamento());
				item.setNomeTratamento(tratamento.getNome() + " (" + tratamento.getAbreviacao() + ")");
				item.setVagas(tratamento.getVagas() == -1 ? "Ilimitado" : tratamento.getVagas() + "");
				item.setQtdAgendamentos(1);
				map.put(a.getIdTratamento(), item);
			} else {
				ItemRelatorioAgendamentos item = map.get(a.getIdTratamento());
				item.setQtdAgendamentos(item.getQtdAgendamentos() + 1);
			}
		}

		for (Iterator<Long> it = map.keySet().iterator(); it.hasNext();) {
			itens.add(map.get(it.next()));
		}

		result.include("itens", itens);
		result.include("data", data);
	}

	// -------------

	// --- CADASTRO DE ASSISTIDOS ---
	@RestritoRecepcao
	public void listaAssistidos1(String action, String parteDoNome, String ehTrabalhador, Long idTratamento, String data) {
		if ("Limpar".equals(action)) {
			parteDoNome = ehTrabalhador = data = null;
			idTratamento = null;
		}

		result.include("tratamentos", tratamentoDAO.listaTodos());
		result.include("assistidos", assistidoDAO.lista(parteDoNome, ehTrabalhador, idTratamento, data));
		result.include("parteDoNome", parteDoNome);

		if (ehTrabalhador == null) {
			result.include("ehTrabalhador", "tanto faz");
		} else {
			result.include("ehTrabalhador", ehTrabalhador);
		}

		result.include("idTratamento", idTratamento);
		result.include("data", data);
	}

	@RestritoRecepcao
	public void novoAssistido1(Assistido assistido, String diaNiver, String mesNiver, String anoNiver) {
		result.include("assistido", assistido);
		result.include("diaNiver", diaNiver);
		result.include("mesNiver", mesNiver);
		result.include("anoNiver", anoNiver);
	}

	@RestritoRecepcao
	public void novoAssistido2(Assistido assistido, String diaNiver, String mesNiver, String anoNiver, String action) {
		if ("Pesquisar RG".equals(action)) {
			pesquisaRG(assistido, diaNiver, mesNiver, anoNiver);
			return;
		} else if ("Pesquisar CEP".equals(action)) {
			pesquisaCEP(assistido, diaNiver, mesNiver, anoNiver);
			return;
		} else {
			if (assistido.getNomeCompleto() == null || assistido.getNomeCompleto().trim().equals("")) {
				validator.add(new ValidationMessage("Favor preencher o campo <b>Nome Completo</b>.", ""));
				validator.onErrorUsePageOf(this).novoAssistido1(assistido, diaNiver, mesNiver, anoNiver);
				return;
			}
			assistido.setDataNascimento(Formatador.fromStringToCalendar(diaNiver + "/" + mesNiver + "/" + anoNiver));
		}

		if ("Atualizar Cadastro Existente".equals(action)) {
			assistidoDAO.atualiza(assistido);
			result.forwardTo(this).mostraMensagem("Cadastro atualizado com sucesso.", "vazia");
		} else {
			if (identificadorAssistidoEhUnico(assistido)) {
				assistidoDAO.adiciona(assistido);
				result.forwardTo(this).mostraMensagem("Cadastramento efetuado com sucesso.", "vazia");
			} else {
				validator
						.add(new ValidationMessage(
								"Identificador já existe. <br> Favor verificar os campos <b>RG</b>, <b>Nome Completo</b> e <b>Data de Nascimento</b>",
								""));
				validator.onErrorUsePageOf(this).novoAssistido1(assistido, diaNiver, mesNiver, anoNiver);
			}
		}
	}

	private void pesquisaCEP(Assistido assistido, String diaNiver, String mesNiver, String anoNiver) {
		if (assistido.getCep() != null && !assistido.getCep().equals("")) {
			assistido.setEndereco(enderecoDAO.busca(assistido.getCep()));
		} else {
			assistido.setEndereco(null);
		}

		result.redirectTo(this).novoAssistido1(assistido, diaNiver, mesNiver, anoNiver);
	}

	private void pesquisaRG(Assistido assistido, String diaNiver, String mesNiver, String anoNiver) {
		if (assistido.getRg() == null || assistido.getRg().trim().equals("")) {
			validator.add(new ValidationMessage("O RG " + assistido.getRg() + " não está cadastrado.", ""));
			assistido.setId(null);
			validator.onErrorUsePageOf(this).novoAssistido1(assistido, diaNiver, mesNiver, anoNiver);
		}

		Assistido a = assistidoDAO.buscaPorRg(assistido.getRg());

		if (a == null) {
			validator.add(new ValidationMessage("O RG " + assistido.getRg() + " não está cadastrado.", ""));
			assistido.setId(null);
			validator.onErrorUsePageOf(this).novoAssistido1(a, diaNiver, mesNiver, anoNiver);
		} else {
			diaNiver = a.getDataNascimento().get(Calendar.DATE) + "";
			mesNiver = (a.getDataNascimento().get(Calendar.MONTH) + 1) + "";
			anoNiver = a.getDataNascimento().get(Calendar.YEAR) + "";
			result.redirectTo(this).novoAssistido1(a, diaNiver, mesNiver, anoNiver);
		}
	}

	boolean identificadorAssistidoEhUnico(Assistido assistido) {
		Assistido a;

		if (assistido.getRg() != null && !assistido.getRg().trim().equals("")) {
			a = assistidoDAO.buscaPorRg(assistido.getRg());
		} else {
			a = assistidoDAO.buscaPorNomeDataNascimento(assistido.getNomeCompleto(), assistido.getDataNascimento());
		}

		return a == null;
	}

	// ---------------

	// --- TRIAGEM ---
	@RestritoTriagem
	public void novaTriagem1(Integer indiceTriagem) {
		if (assistidoSession.getAssistido() != null) {
			Triagem triagem = triagemDAO.buscaDataHoje(assistidoSession.getAssistido().getId());

			if (triagem == null) {
				triagem = new Triagem();
				triagem.setNomeAtendente(usuarioSession.getNome());
				triagem.setData(Formatador.getDataHoje());
				triagem.setIdAssistido(assistidoSession.getAssistido().getId());
				triagem = triagemDAO.busca(triagemDAO.adiciona(triagem));
			}

			result.include("tratamentos", tratamentoDAO.listaTodos());
			List<Triagem> triagens = triagemDAO.listaTodasDoAssistido(assistidoSession.getAssistido().getId());
			result.include("totalTriagens", triagens.size());

			if (indiceTriagem == null) {
				result.include("triagem", triagem);
				result.include("indiceTriagem", triagens.size() - 1);
			} else {
				if (indiceTriagem > triagens.size() - 1) {
					indiceTriagem = triagens.size() - 1;
				} else if (indiceTriagem < 0) {
					indiceTriagem = 0;
				}

				result.include("triagem", triagens.get(indiceTriagem));
				result.include("indiceTriagem", indiceTriagem);
			}
		} else {
			validator.onErrorUsePageOf(this).loginAssistido1("Triagem");
		}

		// ... ?
	}

	private Long adicionaAgendamento(Tratamento tratamento) {
		Agendamento agendamento = new Agendamento();
		agendamento.setIdTratamento(tratamento.getId());
		agendamento.setNomeTratamento(tratamento.getNome());
		agendamento.setIdAssistido(assistidoSession.getAssistido().getId());
		agendamento.setDataAgendamento(Formatador.getDataHoje());
		agendamento.setDataAtendimento(null);
		agendamento.setStatus(StatusDoAgendamento.Data_Não_Marcada);
		Triagem triagem = triagemDAO.buscaMaisRecente(agendamento.getIdAssistido());

		if (triagem != null) {
			agendamento.setIdTriagem(triagem.getId());
		}

		agendamentoDAO.adiciona(agendamento);
		return agendamento.getId();
	}

	@RestritoTriagem
	public void novaTriagem2(Triagem triagem, String action, Long[] tratamentosAgendados) {
		if ("Salvar".equals(action)) {
			triagem.setData(Formatador.getDataHoje());
			triagem.setNomeAtendente(usuarioSession.getNome());
			triagem.setIdAssistido(assistidoSession.getAssistido().getId());
			triagemDAO.atualiza(triagem);

			// preenche em 'triagem' os tratamentos já agendados
			triagemDAO.preencheTratamentos(triagem);

			// agenda novos tratamentos, se houve alteração
			if (tratamentosAgendados != null) {
				for (Long idTratamento : tratamentosAgendados) {
					if (!triagem.idTratamentosAgendamentos.contains(idTratamento)) {
						adicionaAgendamento(tratamentoDAO.busca(idTratamento));
					}
				}
			}

			result.forwardTo(this).logoutAssistido1("novaTriagem1");
		}
	}

	// ---------------

	// --- ATENDIMENTO RECEPÇÃO ---
	@RestritoRecepcao
	public void novoAtendimento1() {
		if (assistidoSession.getAssistido() != null) {
			result.redirectTo(this).listaAgendamentos();
		} else {
			result.redirectTo(this).mostraMensagem("Digitar primeiro os dados do assistido.", "loginAssistido1");
		}
	}

	@RestritoRecepcao
	public void listaAgendamentos() {
		Long idAssistido = assistidoSession.getAssistido().getId();
		result.include("pendentes", agendamentoDAO.listaPendentes(idAssistido));
		result.include("agendadas", agendamentoDAO.listaAgendadas(idAssistido));
		result.include("finalizadas", agendamentoDAO.listaFinalizados(idAssistido));
		result.include("tratamentos", tratamentoDAO.listaTodos());
	}

	@RestritoRecepcao
	public void novoAgendamento1(Long idTratamento) {
		result.include("datasDisponiveis", plantaoDAO.listaDatasDisponiveis(idTratamento));
		result.include("tratamento", tratamentoDAO.busca(idTratamento));
	}

	private Long adicionaAgendamento(Agendamento agendamento_, String dataAtendimento) {
		Agendamento agendamento = new Agendamento();
		agendamento.setIdTratamento(agendamento_.getIdTratamento());
		agendamento.setIdAssistido(assistidoSession.getAssistido().getId());
		agendamento.setNomeTratamento(agendamento_.getNomeTratamento());
		agendamento.setDiaDaSemana(agendamento_.getDiaDaSemana());
		agendamento.setDataAgendamento(Formatador.getDataHoje());

		if (dataAtendimento != null) {
			agendamento.setDataAtendimento(Formatador.fromStringToCalendar(dataAtendimento));
			agendamento.setStatus(StatusDoAgendamento.Data_Marcada);
			agendamento.setDiaDaSemana(Formatador.getDiaDaSemana(agendamento.getDataAtendimento()));
		} else {
			agendamento.setDataAtendimento(null);
			agendamento.setStatus(StatusDoAgendamento.Data_Não_Marcada);
		}

		Tratamento tratamento = tratamentoDAO.busca(agendamento.getIdTratamento());
		agendamento.setNomeTratamento(tratamento.getNome());
		Triagem triagem = triagemDAO.buscaMaisRecente(agendamento.getIdAssistido());

		if (triagem != null) {
			agendamento.setIdTriagem(triagem.getId());
		}

		agendamentoDAO.adiciona(agendamento);
		return agendamento.getId();
	}

	@RestritoRecepcao
	public void novoAgendamento2(int nSessoes, Agendamento agendamento, String dataAtendimento, String action) {
		if ("Voltar".equalsIgnoreCase(action)) {
			result.redirectTo(this).listaAgendamentos();
		} else {
			long idAgendamento = adicionaAgendamento(agendamento, dataAtendimento);

			for (int i = 2; i <= nSessoes; i++) {
				adicionaAgendamento(agendamento, null);
			}

			result.forwardTo(this).imprimeComprovante(idAgendamento);
		}
	}

	@RestritoRecepcao
	public void imprimeComprovante(Long idAgendamento) {
		Agendamento agendamento = agendamentoDAO.busca(idAgendamento);
		result.include("agendamento", agendamento);
		result.include("tratamento", tratamentoDAO.busca(agendamento.getIdTratamento()));
	}

	@RestritoRecepcao
	public void imprimeSenha(long idAgendamento, boolean ehPrioritario) {
		Agendamento agendamento = agendamentoDAO.busca(idAgendamento);

		if (ehPrioritario && !agendamento.getStatus().equals(StatusDoAgendamento.Senha_Emitida)) {
			agendamento.setSenha("PRIORITÁRIA");
			agendamento.setStatus(StatusDoAgendamento.Senha_Emitida);
			agendamentoDAO.atualiza(agendamento);
		} else if (!ehPrioritario && !agendamento.getStatus().equals(StatusDoAgendamento.Senha_Emitida)) {
			agendamento.setSenha(gerenciadorDeSenhas.geraProximaSenha(agendamento.getIdTratamento()));
			agendamento.setStatus(StatusDoAgendamento.Senha_Emitida);
			agendamentoDAO.atualiza(agendamento);
		}

		result.include("agendamento", agendamento);
	}

	@RestritoRecepcao
	public void marcarDataAgendamento1(long idAgendamento) {
		Agendamento agendamento = agendamentoDAO.busca(idAgendamento);
		result.include("tratamento", tratamentoDAO.busca(agendamento.getIdTratamento()));
		result.include("agendamento", agendamento);
		result.include("datasDisponiveis", plantaoDAO.listaDatasDisponiveis(agendamento.getIdTratamento()));
	}

	@RestritoRecepcao
	public void marcarDataAgendamento2(long idAgendamento, String dataAtendimento, String action) {
		Agendamento agendamento = agendamentoDAO.busca(idAgendamento);

		if ("Voltar".equalsIgnoreCase(action)) {
			result.redirectTo(this).listaAgendamentos();
		} else {
			agendamento.setDataAtendimento(Formatador.fromStringToCalendar(dataAtendimento));
			agendamento.setStatus(StatusDoAgendamento.Data_Marcada);
			agendamento.setDiaDaSemana(Formatador.getDiaDaSemana(agendamento.getDataAtendimento()));
			agendamentoDAO.atualiza(agendamento);
			result.forwardTo(this).imprimeComprovante(idAgendamento);
		}
	}

	@RestritoRecepcao
	public void cancelarAgendamento(long idAgendamento) {
		agendamentoDAO.remove(idAgendamento);
		result.forwardTo(this).mostraMensagem("Agendamento removido com sucesso.", "listaAgendamentos");
	}

	// ---------------

	// --- CADASTRO USUÁRIO ---
	@RestritoADM
	public void listaUsuarios() {
		result.include("usuarios", usuarioDAO.listaTodos());
	}

	@RestritoADM
	public void novoUsuario1(String action, String login) {
		if (action != null && action.contains("remover")) {
			usuarioDAO.remove(login);
			result.forwardTo(this).mostraMensagem("Usuário removido com sucesso.", "listaUsuarios");
		}
	}

	@RestritoADM
	public void novoUsuario2(Usuario usuario, String senhaAgain, String action) {
		if ("Voltar".equals(action)) {
			result.redirectTo(this).listaUsuarios();
		} else {
			if (usuario.getNome().length() < 3 || usuario.getLogin().length() < 3 || usuario.getSenha().length() < 3) {
				validator.add(new ValidationMessage("Cada campo deve ter no mínimo 3 caracteres.", ""));
			} else if (usuarioDAO.existeUsuario(usuario)) {
				validator.add(new ValidationMessage("Já existe um usuário '" + usuario.getLogin() + "' cadastrado.", ""));
			} else if (!usuario.getSenha().equals(senhaAgain)) {
				validator.add(new ValidationMessage("A senha não confere.", ""));
			} else {
				usuarioDAO.adiciona(usuario);
				result.redirectTo(this).mostraMensagem("Usuário cadastrado com sucesso.", "listaUsuarios");
			}
		}
		validator.onErrorUsePageOf(this).novoUsuario1(null, null);
	}

	// ------------------------

	// --- CADASTRO TRATAMENTO ---
	@RestritoADM
	public void listaTratamentos() {
		result.include("tratamentos", tratamentoDAO.listaTodos());
	}

	@RestritoADM
	public void novoTratamento1() {
	}

	@RestritoADM
	public void novoTratamento2(Tratamento tratamento, int[] diasDaSemana, String action) {
		if ("Voltar".equals(action)) {
			result.redirectTo(this).listaTratamentos();
		} else {

			if (diasDaSemana == null) {
				validator.add(new ValidationMessage("Escolha os dias da semana em que <br /> o tratamento será realizado.",
						""));
				validator.onErrorUsePageOf(this).novoTratamento1();

			} else if (tratamento.getNome() == null || tratamento.getNome().trim().length() == 0) {
				validator.add(new ValidationMessage("Digite o nome do tratamento.", ""));
				validator.onErrorUsePageOf(this).novoTratamento1();

			} else {
				for (int i = 0; i < diasDaSemana.length; i++) {
					tratamento.adicionaDia(diasDaSemana[i]);
				}

				tratamentoDAO.adiciona(tratamento);
				result.redirectTo(this).mostraMensagem("Tratamento cadastrado com sucesso.", "listaTratamentos");
			}
		}
	}

	@RestritoADM
	public void removeTratamento(long idTratamento) {
		tratamentoDAO.remove(idTratamento);
		result.redirectTo(this).mostraMensagem("Tratamento removido com sucesso.", "listaTratamentos");
	}

	@RestritoADM
	public void editaTratamento1(long idTratamento) {
		result.include("tratamento", tratamentoDAO.busca(idTratamento));
	}

	@RestritoADM
	public void editaTratamento2(Tratamento tratamento, String action, int[] diasDaSemana) {
		if ("Voltar".equalsIgnoreCase(action)) {
			result.redirectTo(this).listaTratamentos();
		} else {
			if (diasDaSemana == null) {
				validator.add(new ValidationMessage("Escolha os dias da semana em que <br /> o tratamento será realizado.",
						""));
				validator.onErrorUsePageOf(this).editaTratamento1(tratamento.getId());

			} else if (tratamento.getNome() == null || tratamento.getNome().trim().length() == 0) {
				validator.add(new ValidationMessage("Digite o nome do tratamento.", ""));
				validator.onErrorUsePageOf(this).editaTratamento1(tratamento.getId());
			} else {
				for (int i = 0; i < diasDaSemana.length; i++) {
					tratamento.adicionaDia(diasDaSemana[i]);
				}

				tratamentoDAO.atualiza(tratamento);
				result.redirectTo(this).mostraMensagem("Tratamento atualizado com sucesso.", "listaTratamentos");
			}
		}
	}
	// ------------------------
}