package seara.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import seara.model.DataDisponivel;
import seara.model.Tratamento;
import seara.util.Formatador;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class PlantaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private AgendamentoDAO agendamentoDAO;
	private TratamentoDAO tratamentoDAO;
	private static final int QTD_DATAS_DISPONIVEIS_AGENDAMENTO = 20;
	
	public PlantaoDAO(AgendamentoDAO agendamentoDAO, TratamentoDAO tratamentoDAO) {
		this.agendamentoDAO = agendamentoDAO;
		this.tratamentoDAO = tratamentoDAO;
	}

	public List<DataDisponivel> listaDatasDisponiveis(long idTratamento) {
		Tratamento tratamento = tratamentoDAO.busca(idTratamento);
		Calendar data = Formatador.getDataHoje();

		for (; !tratamento.ehRealizadoNoDia(data.get(Calendar.DAY_OF_WEEK)); data.add(Calendar.DAY_OF_MONTH, 1)) {
		}

		List<DataDisponivel> datasDisponiveis = new LinkedList<DataDisponivel>();

		for (int count = 0; count < QTD_DATAS_DISPONIVEIS_AGENDAMENTO; data.add(Calendar.DAY_OF_MONTH, 1)) {
			if (!tratamento.ehRealizadoNoDia(data.get(Calendar.DAY_OF_WEEK))) {
				continue;
			}

			int nAgendamentos = agendamentoDAO.getNumAgendamentos(data, tratamento);

			if (tratamento.getVagas() > nAgendamentos || tratamento.getVagas() == -1) {
				datasDisponiveis.add(new DataDisponivel(Formatador.getDiaDaSemana(data), Formatador
						.fromCalendarToString(data), tratamento.getVagas(), nAgendamentos));
				count++;
			} else if (tratamento.isPermitirOverbooking()) {
				datasDisponiveis.add(new DataDisponivel(Formatador.getDiaDaSemana(data), Formatador
						.fromCalendarToString(data), tratamento.getVagas(), nAgendamentos));
			}

		}

		return datasDisponiveis;
	}

	/*
	 * public boolean dataEstaDisponivel(Tratamento tratamento, Calendar data) {
	 * int nAgendamentos = agendamentoDAO.getNumAgendamentos(data, tratamento);
	 * return tratamento.getVagas() > nAgendamentos || tratamento.getVagas() ==
	 * -1; }
	 */
}
