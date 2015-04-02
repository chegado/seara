package seara.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import seara.util.constantes.DiaDaSemana;

public class Formatador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static int TIRA_ESSA_GAMBI_PELAMOR = 0;

	public static final DiaDaSemana[] diaDaSemana = { null, DiaDaSemana.Domingo, DiaDaSemana.Segunda, DiaDaSemana.Terça,
			DiaDaSemana.Quarta, DiaDaSemana.Quinta, DiaDaSemana.Sexta, DiaDaSemana.Sábado };

	public static DiaDaSemana getDiaDaSemana(Calendar data) {
		return diaDaSemana[data.get(Calendar.DAY_OF_WEEK)];
	}

	public static String removeCaracteresNaoNumericos(String str) {
		return str.replaceAll("[^\\d]", "");
	}

	public static String removeCaracteresNaoAlphaNumericos(String str) {
		StringBuffer resp = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetterOrDigit(str.charAt(i))) {
				resp.append(Character.toUpperCase(str.charAt(i)));
			}
		}

		return resp.toString();
	}

	public static Calendar getDataHoje() {
		Calendar hoje = Calendar.getInstance();
		hoje.set(Calendar.HOUR_OF_DAY, 0);
		hoje.set(Calendar.MINUTE, 0);
		hoje.set(Calendar.SECOND, 0);
		hoje.add(Calendar.DATE, TIRA_ESSA_GAMBI_PELAMOR);
		return hoje;
	}

	public static boolean ehHoje(Calendar calendar) {
		if (calendar == null) {
			return false;
		}

		Calendar hoje = getDataHoje();
		return calendar.get(Calendar.DATE) == hoje.get(Calendar.DATE)
				&& calendar.get(Calendar.MONTH) == hoje.get(Calendar.MONTH)
				&& calendar.get(Calendar.YEAR) == hoje.get(Calendar.YEAR);
	}

	public static String fromCalendarToString(Calendar calendar) {
		SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
		return frmt.format(calendar.getTime());
	}

	public static String formataNome(String nome) {
		String[] nomes = nome.trim().split("\\s+");
		StringBuffer nomeFrmt = new StringBuffer();

		for (int i = 0; i < nomes.length; i++) {
			nomeFrmt.append(nomes[i].toUpperCase());

			if (i < nomes.length - 1) {
				nomeFrmt.append(" ");
			}
		}

		return nomeFrmt.toString();
	}

	public static Calendar fromStringToCalendar(String data) {
		Calendar date = Calendar.getInstance();
		data = data.trim();
		int day = Integer.parseInt(data.substring(0, 2));
		int month = Integer.parseInt(data.substring(3, 5)) - 1; // starts from
																// zero!
		int year = Integer.parseInt(data.substring(6, 10));
		date.set(year, month, day, 0, 0, 0);
		return date;
	}
}
