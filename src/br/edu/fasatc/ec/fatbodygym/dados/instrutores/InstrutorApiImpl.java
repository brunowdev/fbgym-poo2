package br.edu.fasatc.ec.fatbodygym.dados.instrutores;

import java.util.List;

import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.Instrutor;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.InstrutorRepository;

public class InstrutorApiImpl implements InstrutorApi {
	
	private InstrutorRepository repository = new InstrutorRepository();

	@Override
	public List<Instrutor> getAll() {
		try {
			return repository.findAll();
		} catch (WriteFileException | ReadFileException e) {
			e.printStackTrace();
		}
		
		return null;
	}	

}
