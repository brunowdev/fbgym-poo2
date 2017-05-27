package br.edu.fasatc.ec.fatbodygym.model.treinos.resultados;

import java.util.Arrays;

import br.edu.fasatc.ec.fatbodygym.model.TipoTreino;
import br.edu.fasatc.ec.fatbodygym.model.treinos.AbstractTreinoStrategy;
import br.edu.fasatc.ec.fatbodygym.model.treinos.QueimaCaloriasStrategy;

public class TreinoResistenciaMuscularStrategy extends AbstractTreinoStrategy implements QueimaCaloriasStrategy{

	public TreinoResistenciaMuscularStrategy() {
		super(TipoTreino.RESISTENCIA_MUSCULAR);
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
		.mapToDouble(exercicio -> (exercicio.getMassaMuscular() * 10) - exercicio.getCaloriaHora())
		.sum();
	}

}
