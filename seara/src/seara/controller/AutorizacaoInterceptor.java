package seara.controller;

import java.io.Serializable;

import seara.model.UsuarioSession;
import seara.util.constantes.TipoDoUsuario;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class AutorizacaoInterceptor implements Interceptor, Serializable {

	private static final long serialVersionUID = 1L;
	private final UsuarioSession usuario;
	private final Result result;

	public AutorizacaoInterceptor(UsuarioSession usuario, Result result) {
		this.usuario = usuario;
		this.result = result;
	}

	private boolean ehRestrito(ResourceMethod method) {
		return method.containsAnnotation(RestritoRecepcao.class) || method.containsAnnotation(RestritoADM.class)
				|| method.containsAnnotation(RestritoTriagem.class)
				|| method.containsAnnotation(RestritoAuxiliarExterno.class);
	}

	enum Motivo {
		NãoEstáLogado, PrecisaSerADM, PrecisaSerTriagem, PrecisaSerRecepção;
	}

	Motivo motivo = null;

	public boolean accepts(ResourceMethod method) {
		if (!usuario.isLogado() && ehRestrito(method)) {
			motivo = Motivo.NãoEstáLogado;
			return true;
		}

		// ADM, só quem tem acesso é ADM
		if (method.containsAnnotation(RestritoADM.class) && !usuario.getTipoUsuario().equals(TipoDoUsuario.Administrador)) {
			motivo = Motivo.PrecisaSerADM;
			return true;
		}

		// Triagem, só quem tem acesso é ADM e Triagem
		if (method.containsAnnotation(RestritoTriagem.class) && !usuario.getTipoUsuario().equals(TipoDoUsuario.Triagem)
				&& !usuario.getTipoUsuario().equals(TipoDoUsuario.Administrador)) {
			motivo = Motivo.PrecisaSerTriagem;
			return true;
		}

		// Recepção, só quem tem acesso é ADM, Triagem e Recepção
		if (method.containsAnnotation(RestritoRecepcao.class) && !usuario.getTipoUsuario().equals(TipoDoUsuario.Recepção)
				&& !usuario.getTipoUsuario().equals(TipoDoUsuario.Triagem)
				&& !usuario.getTipoUsuario().equals(TipoDoUsuario.Administrador)) {
			motivo = Motivo.PrecisaSerRecepção;
			return true;
		}

		// Auxiliar Externo, todos têm acesso, basta estar logado
		if (method.containsAnnotation(RestritoAuxiliarExterno.class)) {
			// não faz nada...
		}

		return false;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance)
			throws InterceptionException {
		switch (motivo) {
		case NãoEstáLogado:
			result.redirectTo(AllviewsController.class).mostraMensagem("Favor efetuar <i>login</i> no sistema.",
					"loginOperador");
			break;

		default:
			result.redirectTo(AllviewsController.class).mostraMensagem("Acesso não autorizado.",
					"vazia");
		}
	}
}