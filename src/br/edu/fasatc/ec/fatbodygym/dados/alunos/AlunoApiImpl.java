package br.edu.fasatc.ec.fatbodygym.dados.alunos;

import java.util.List;

import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.Aluno;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.AlunoRepository;

public class AlunoApiImpl implements AlunoApi {

	private AlunoRepository repository = new AlunoRepository();

	@Override
	public List<Aluno> getAll() {
		try {
			return repository.findAll();
		} catch (WriteFileException | ReadFileException e) {
			e.printStackTrace();
		}
		return null;
	}

}
