package br.edu.fasatc.ec.fatbodygym.dados;

import java.util.Objects;

import br.edu.fasatc.ec.fatbodygym.dados.alunos.AlunoFacade;
import br.edu.fasatc.ec.fatbodygym.dados.instrutores.InstrutorFacade;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;

public class RemoteConnection {
	
	public AlunoFacade alunoFacade;
	public InstrutorFacade instrutorFacade;
	
	public void connect(String email, String senha) {

		Usuario usuario = Usuario.Builder.create().email(email).senha(senha).build();
		
		this.alunoFacade = new AlunoFacade(usuario);
		this.instrutorFacade = new InstrutorFacade(usuario);
	}
	
	public InstrutorFacade getInstrutoresApi() {
		checkConnected();
		return instrutorFacade;
	}
	
	public AlunoFacade getAlunosApi() {
		checkConnected();
		return alunoFacade;
	}
	
	public void disconnect() {
		this.alunoFacade = null;
		this.instrutorFacade = null;
	}
	
	public void checkConnected() {
		Objects.requireNonNull(alunoFacade, "Não conectado.");

		Objects.requireNonNull(instrutorFacade, "Não conectado.");
	}

}
