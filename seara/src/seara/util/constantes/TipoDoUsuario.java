package seara.util.constantes;

public enum TipoDoUsuario {
	Auxiliar_Externo, Recepção, Triagem, Administrador;

	public String getFormatado() {
		return name().replaceAll("_", " ");
	};
}
