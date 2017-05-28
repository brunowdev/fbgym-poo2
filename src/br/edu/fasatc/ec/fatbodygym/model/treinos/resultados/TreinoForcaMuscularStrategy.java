package br.edu.fasatc.ec.fatbodygym.model.treinos.resultados;

import java.util.Arrays;

import br.edu.fasatc.ec.fatbodygym.model.TipoTreino;
import br.edu.fasatc.ec.fatbodygym.model.treinos.AbstractTreinoStrategy;
import br.edu.fasatc.ec.fatbodygym.model.treinos.ResultadoTreino;

public class TreinoForcaMuscularStrategy extends AbstractTreinoStrategy implements ResultadoTreino{

	public TreinoForcaMuscularStrategy() {
		super(TipoTreino.FORCA_MUSCULAR);
	}

	@Override
	public Double calcularQueimaCalorias() {
		return Arrays.asList(tipoTreino.getExercicios())
				.stream()
				.mapToDouble(exercicio -> exercicio.getCaloriaHora() * 5)
				.sum();
	}

	@Override
	public Double calcularGanhoMassaMuscular() {
		return Arrays.asList(tipoTreino.getExercicios())
		.stream()
		.mapToDouble(exercicio -> (exercicio.getMassaMuscular() * 4) - exercicio.getCaloriaHora() * 3)
		.sum();
	}

}
