package seara.util.constantes;

public enum FeedBack {
	Não_Se_Aplica_Não_é_Retorno, Melhorou, Piorou, Continua_Igual, Problema_Resolvido;

	public String getFormatado() {
		if (name().contains("Não_Se")) {
			return "Não Se Aplica (Não é Retorno)";
		}
		return name().replaceAll("_", " ");
	};
}
