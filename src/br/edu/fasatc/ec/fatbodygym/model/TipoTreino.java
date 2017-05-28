package br.edu.fasatc.ec.fatbodygym.model;

import java.io.Serializable;
import java.util.function.Supplier;

import br.edu.fasatc.ec.fatbodygym.model.treinos.ResultadoTreino;
import br.edu.fasatc.ec.fatbodygym.model.treinos.resultados.*;


import static br.edu.fasatc.ec.fatbodygym.model.TipoExercicio.*;

public enum TipoTreino implements EnumDescription, EnumInteger, Serializable {

	FUNCIONAL(1, "Funcional", new TipoExercicio[] {BOOTCAMP, CROSSFIT, ZUMBA}, TreinoFuncionalStrategy::new), 
	HIPERTROFIA(2, "Hipertrofia muscular", new TipoExercicio[] {HIPERTROFIA_MUSCULAR, SPINNING}, TreinoHipertrofiaStrategy::new), 
	FORCA_MUSCULAR(3,"Força muscular", new TipoExercicio[] {HIPERTROFIA_MUSCULAR, HIPERTROFIA_MUSCULAR}, TreinoForcaMuscularStrategy::new), 
	POTENCIA_MUSCULAR(4, "Potência muscular", new TipoExercicio[] {CROSSFIT, SPINNING}, TreinoPotenciaMuscularStrategy::new), 
	RESISTENCIA_MUSCULAR(5, "Resistência muscular", new TipoExercicio[] {MUAY_THAY, HIPERTROFIA_MUSCULAR}, TreinoResistenciaMuscularStrategy::new);

	private TipoTreino(Integer integer, String description, TipoExercicio[] exercicios, Supplier<ResultadoTreino> strategy) {
		this.integer = integer;
		this.description = description;
		this.exercicios = exercicios;
		this.strategy = strategy;
	}

	private final Integer integer;
	private final String description;
	private final TipoExercicio[] exercicios;
	private final Supplier<ResultadoTreino> strategy;

	@Override
	public Integer getInteger() {
		return integer;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	public TipoExercicio[] getExercicios() {
		return exercicios;
	}

	public Supplier<ResultadoTreino> getStrategy() {
		return strategy;
	}

	public static TipoTreino fromInteger(Integer integer) {

		switch (integer) {
		case 1:
			return FUNCIONAL;
		case 2:
			return HIPERTROFIA;
		case 3:
			return FORCA_MUSCULAR;
		case 4:
			return POTENCIA_MUSCULAR;
		case 5:
			return RESISTENCIA_MUSCULAR;

		default:
			return null;
		}

	}

}
