package br.edu.fasatc.ec.fatbodygym.model.treinos;

public class ResultadoTreinoStrategy {

	private ResultadoTreino strategy;
	

	public ResultadoTreinoStrategy(ResultadoTreino strategy) {
		super();
		this.strategy = strategy;
	}
	
	public void changeStrategy(ResultadoTreino newStrategy) {
		this.strategy = newStrategy;
	}
	
	public Double calcularQueimaCalorias() {
		return strategy.calcularQueimaCalorias();
	}
	
	public Double calcularGanhoMassaMuscular() {
		return strategy.calcularQueimaCalorias();
	}
	
}
