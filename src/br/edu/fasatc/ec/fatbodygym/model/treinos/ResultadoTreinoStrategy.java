package br.edu.fasatc.ec.fatbodygym.model.treinos;

public class ResultadoTreinoStrategy {

	private QueimaCaloriasStrategy strategy;
	

	public ResultadoTreinoStrategy(QueimaCaloriasStrategy strategy) {
		super();
		this.strategy = strategy;
	}
	
	public void changeStrategy(QueimaCaloriasStrategy newStrategy) {
		this.strategy = newStrategy;
	}
	
	public Double calcularQueimaCalorias() {
		return strategy.calcularQueimaCalorias();
	}
	
	public Double calcularGanhoMassaMuscular() {
		return strategy.calcularQueimaCalorias();
	}
	
}
