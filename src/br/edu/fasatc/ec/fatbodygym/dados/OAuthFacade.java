package br.edu.fasatc.ec.fatbodygym.dados;

import br.edu.fasatc.ec.fatbodygym.model.Usuario;

public abstract class OAuthFacade {
	
	protected Usuario usuario;
	
	public OAuthFacade(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	/**
	 * Método que permite que cada façade faça a autenticação aos seus recursos.
	 * 
	 * @throws NotAuthorizedException
	 */
	public abstract void checkAuthorization() throws NotAuthorizedException;

}
