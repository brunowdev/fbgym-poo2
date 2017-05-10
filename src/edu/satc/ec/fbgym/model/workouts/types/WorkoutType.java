package edu.satc.ec.fbgym.model.workouts.types;

import edu.satc.ec.fbgym.model.commons.enums.EnumDescription;
import edu.satc.ec.fbgym.model.commons.enums.EnumInteger;

/**
 * Created by BRUNO-PC on 09/05/2017.
 */
public enum WorkoutType implements EnumInteger, EnumDescription {

    ENDURANCE(1, "Endurance"), STRENGTH(2, "Strength"), BALANCE(3, "Balance"), FLEXIBILITY(4, "Flexibility");

    private final int value;
    private final String description;

    WorkoutType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getValue() {
        return value;
    }

}
