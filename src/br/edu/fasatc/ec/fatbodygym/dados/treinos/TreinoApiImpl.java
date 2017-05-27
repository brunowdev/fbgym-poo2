package br.edu.fasatc.ec.fatbodygym.dados.treinos;

import java.util.List;

import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.Treino;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.TreinoRepository;

public class TreinoApiImpl implements TreinoApi {
	
	private TreinoRepository repository = new TreinoRepository();

	@Override
	public List<Treino> getAll() {
		try {
			return repository.findAll();
		} catch (WriteFileException | ReadFileException e) {
			e.printStackTrace();
		}
		
		return null;
	}	

}
