package edu.satc.ec.fbgym.model.commons.builders.entities;

/**
 * Created by BRUNO-PC on 09/05/2017.
 */
public abstract class AbstractEntityBuilder<T, K> {

    protected T entity;

    public AbstractEntityBuilder(T entity) {
        this.entity = entity;
    }

    public T build() {
        return entity;
    }

}
