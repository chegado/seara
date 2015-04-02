package seara.util.constantes;

public enum StatusDoAgendamento {
	Data_Não_Marcada, Data_Marcada, Senha_Emitida, Finalizado, Assistido_Faltou;
	
	public String getFormatado() {
		return name().replaceAll("_", " ");
	};
}
