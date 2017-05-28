package br.edu.fasatc.ec.fatbodygym.model.treinos.resultados;

import java.util.Arrays;

import br.edu.fasatc.ec.fatbodygym.model.TipoTreino;
import br.edu.fasatc.ec.fatbodygym.model.treinos.AbstractTreinoStrategy;
import br.edu.fasatc.ec.fatbodygym.model.treinos.ResultadoTreino;

public class TreinoFuncionalStrategy extends AbstractTreinoStrategy implements ResultadoTreino{

	public TreinoFuncionalStrategy() {
		super(TipoTreino.FUNCIONAL);
	}

	@Override
	public Double calcularQueimaCalorias() {
		return Arrays.asList(tipoTreino.getExercicios())
				.stream()
				.mapToDouble(exercicio -> exercicio.getCaloriaHora() * 1)
				.sum();
	}

	@Override
	public Double calcularGanhoMassaMuscular() {
		return Arrays.asList(tipoTreino.getExercicios())
		.stream()
		.mapToDouble(exercicio -> exercicio.getMassaMuscular() * 2)
		.sum();
	}

}
