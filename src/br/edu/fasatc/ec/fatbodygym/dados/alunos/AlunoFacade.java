package br.edu.fasatc.ec.fatbodygym.dados.alunos;

import java.util.List;
import java.util.Objects;

import br.edu.fasatc.ec.fatbodygym.dados.NotAuthorizedException;
import br.edu.fasatc.ec.fatbodygym.dados.OAuthFacade;
import br.edu.fasatc.ec.fatbodygym.model.Aluno;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;

public class AlunoFacade extends OAuthFacade {
	
	public AlunoFacade(Usuario usuario) {
		super(usuario);
	}

	private AlunoApi api = new AlunoApiImpl();

	public List<Aluno> buscarTodosAlunos() {
		return api.getAll();
	}

	@Override
	public void checkAuthorization() {
		
		// Para acessar a API de alunos, deve ser usuário autenticado
		if (Objects.isNull(usuario)) {
			throw new NotAuthorizedException("Usuário não informado.");
		}
		
	}

}
