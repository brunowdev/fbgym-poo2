package edu.satc.ec.fbgym.model.users.roles;

import edu.satc.ec.fbgym.model.commons.enums.EnumDescription;
import edu.satc.ec.fbgym.model.commons.enums.EnumInteger;

/**
 * Created by BRUNO-PC on 09/05/2017.
 */
public enum RoleEnum implements EnumInteger, EnumDescription {

    ADMIN(1, "Administrator"), PERSONAL_TRAINER(2, "Personal Trainer"), FREQUENTER(3, "Frequenter");

    private final int value;
    private final String description;

    RoleEnum(int value, String description) {
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
