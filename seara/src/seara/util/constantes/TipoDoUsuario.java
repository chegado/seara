package seara.util.constantes;

public enum TipoDoUsuario {
	Auxiliar_Externo, Recep��o, Triagem, Administrador;

	public String getFormatado() {
		return name().replaceAll("_", " ");
	};
}
