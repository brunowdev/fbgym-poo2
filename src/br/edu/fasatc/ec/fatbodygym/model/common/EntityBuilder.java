package br.edu.fasatc.ec.fatbodygym.model.common;

public class EntityBuilder<T> {

	public EntityBuilder(T entity) {
		super();
		this.entity = entity;
	}

	protected T entity;
	
	public void validate() {}
	
	public T build() {
		
		validate();
		
		return entity;
	}

}
