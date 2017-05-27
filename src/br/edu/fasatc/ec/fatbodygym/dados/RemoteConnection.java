package br.edu.fasatc.ec.fatbodygym.dados;

import java.util.Objects;

import br.edu.fasatc.ec.fatbodygym.dados.alunos.AlunoFacade;
import br.edu.fasatc.ec.fatbodygym.dados.instrutores.InstrutorFacade;
import br.edu.fasatc.ec.fatbodygym.dados.treinos.TreinoFacade;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;

public class RemoteConnection {
	
	public AlunoFacade alunoFacade;
	public InstrutorFacade instrutorFacade;
	public TreinoFacade treinoFacade;
	
	public void connect(String email, String senha) {

		Usuario usuario = Usuario.Builder.create().email(email).senha(senha).build();
		
		prepareApis(usuario);
	}
	
	public void connect(Usuario usuario) {
		prepareApis(usuario);
	}
	
	private void prepareApis(Usuario usuario) {
		this.alunoFacade = new AlunoFacade(usuario);
		this.instrutorFacade = new InstrutorFacade(usuario);
		this.treinoFacade = new TreinoFacade(usuario);
	}
	
	public InstrutorFacade getInstrutoresApi() {
		checkConnected();
		return instrutorFacade;
	}
	
	public AlunoFacade getAlunosApi() {
		checkConnected();
		return alunoFacade;
	}
	
	public TreinoFacade getTreinoFacade() {
		return treinoFacade;
	}

	public void disconnect() {
		this.alunoFacade = null;
		this.instrutorFacade = null;
		this.treinoFacade = null;
	}
	
	public void checkConnected() {
		
		Objects.requireNonNull(alunoFacade, "Não conectado.");

		Objects.requireNonNull(instrutorFacade, "Não conectado.");
		
		Objects.requireNonNull(treinoFacade, "Não conectado.");
	}

}
