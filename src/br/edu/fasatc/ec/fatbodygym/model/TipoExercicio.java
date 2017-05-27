package br.edu.fasatc.ec.fatbodygym.model;

import java.io.Serializable;

public enum TipoExercicio implements EnumDescription, EnumInteger, Serializable {

	ZUMBA(1, "Zumba", 75, 10), SPINNING(2, "Spinning", 50, 25), CROSSFIT(3, "Crossfit", 200, 75), KETTLEBELLS(4,
			"Keetlebells", 20, 35), BOOTCAMP(5, "Bootcamp", 50, 45), YOGA(6, "Yoga", 5,
					0), MUAY_THAY(7, "Muay Thai", 100, 75), HIPERTROFIA_MUSCULAR(8, "Hipertrofia muscular", 150, 250);

	private TipoExercicio(Integer integer, String description, Integer caloriaHora, Integer massaMuscular) {
		this.integer = integer;
		this.description = description;
		this.caloriaHora = caloriaHora;
		this.massaMuscular = massaMuscular;
	}

	private final Integer integer;
	private final String description;
	private final Integer caloriaHora;
	private final Integer massaMuscular;

	@Override
	public Integer getInteger() {
		return integer;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public Integer getCaloriaHora() {
		return caloriaHora;
	}
	
	public Integer getMassaMuscular() {
		return massaMuscular;
	}

	public static TipoExercicio fromInteger(Integer integer) {

		switch (integer) {
		case 1:
			return ZUMBA;
		case 2:
			return SPINNING;
		case 3:
			return CROSSFIT;
		case 4:
			return KETTLEBELLS;
		case 5:
			return BOOTCAMP;
		case 6:
			return YOGA;
		case 7:
			return MUAY_THAY;
		case 8:
			return HIPERTROFIA_MUSCULAR;

		default:
			return null;
		}
	}

}
