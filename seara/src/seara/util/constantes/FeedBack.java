package seara.util.constantes;

public enum FeedBack {
	N‹o_Se_Aplica_N‹o_Ž_Retorno, Melhorou, Piorou, Continua_Igual, Problema_Resolvido;

	public String getFormatado() {
		if (name().contains("N‹o_Se")) {
			return "N‹o Se Aplica (N‹o Ž Retorno)";
		}
		return name().replaceAll("_", " ");
	};
}
