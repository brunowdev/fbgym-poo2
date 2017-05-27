package br.edu.fasatc.ec.fatbodygym.dados;

import br.edu.fasatc.ec.fatbodygym.model.Usuario;

public abstract class OAuthFacade {
	
	protected Usuario usuario;
	
	public OAuthFacade(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public abstract void checkAuthorization() throws NotAuthorizedException;

}
