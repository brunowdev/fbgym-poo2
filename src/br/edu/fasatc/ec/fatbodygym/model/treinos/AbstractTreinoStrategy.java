package br.edu.fasatc.ec.fatbodygym.model.treinos;

import br.edu.fasatc.ec.fatbodygym.model.TipoTreino;

public abstract class AbstractTreinoStrategy {

	protected TipoTreino tipoTreino;

	public AbstractTreinoStrategy(TipoTreino tipoTreino) {
		super();
		this.tipoTreino = tipoTreino;
	}

}
