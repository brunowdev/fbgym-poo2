package br.edu.fasatc.ec.fatbodygym.dados;

public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 3324929091120093922L;

	public NotAuthorizedException(String message) {
		super("403 - " + message);
	}
}
