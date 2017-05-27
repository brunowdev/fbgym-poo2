package br.edu.fasatc.ec.fatbodygym.dados.treinos;

import java.util.List;
import java.util.Objects;

import br.edu.fasatc.ec.fatbodygym.dados.NotAuthorizedException;
import br.edu.fasatc.ec.fatbodygym.dados.OAuthFacade;
import br.edu.fasatc.ec.fatbodygym.model.Treino;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;

public class TreinoFacade extends OAuthFacade {
	
	private TreinoApi api = new TreinoApiImpl();

	public TreinoFacade(Usuario usuario) {
		super(usuario);
	}

	@Override
	public void checkAuthorization() throws NotAuthorizedException {
		
		if (Objects.isNull(usuario)) {
			throw new NotAuthorizedException("Apenas usuários autenticados podem acessar este recurso.");
		}
		
	}
	
	public List<Treino> buscarTodosTreinos() {
		return api.getAll();
	}

}
