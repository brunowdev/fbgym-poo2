package br.edu.fasatc.ec.fatbodygym.dados.instrutores;

import java.util.List;
import java.util.Objects;

import br.edu.fasatc.ec.fatbodygym.dados.NotAuthorizedException;
import br.edu.fasatc.ec.fatbodygym.dados.OAuthFacade;
import br.edu.fasatc.ec.fatbodygym.model.Instrutor;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;

public class InstrutorFacade extends OAuthFacade {
	
	private InstrutorApi api = new InstrutorApiImpl();

	public InstrutorFacade(Usuario usuario) {
		super(usuario);
	}

	@Override
	public void checkAuthorization() throws NotAuthorizedException {
		
		if (Objects.isNull(usuario)) {
			throw new NotAuthorizedException("Apenas usuários autenticados podem acessar este recurso.");
		}
		
		if (!Objects.isNull(usuario.getAluno())) { // Se o usuário autenticado é um aluno
			throw new NotAuthorizedException("Apenas usuários autenticados podem acessar este recurso.");
		}
		
	}
	
	public List<Instrutor> buscarTodosInstrutores() {
		return api.getAll();
	}

}
