package br.edu.fasatc.ec.fatbodygym.model;

import java.util.Date;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;
import br.edu.fasatc.ec.fatbodygym.model.common.EntityBuilder;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_INSTRUTORES)
public final class Instrutor extends Pessoa implements SearchableString {

	private static final long serialVersionUID = -5022466853513308191L;

	public Instrutor() {
	}

	public Instrutor(Long id) {
		setId(id);
	}

	@Override
	public String[] getSearchableFields() {
		return new String[] { getCpf(), getNome(), getRg() };
	}
	

	public static class Builder extends EntityBuilder<Instrutor> {
		
		protected Builder(Instrutor instrutor) {
			super(instrutor);
		}

		public static Builder create() {
			return new Builder(new Instrutor());
		}

		public static Builder from(Instrutor instrutor) {
			return new Builder(instrutor);
		}

		public Builder nome(String nome) {
			entity.setNome(nome);
			return this;
		}
		
		public Builder cpf(String cpf) {
			entity.setCpf(cpf);
			return this;
		}
		
		public Builder dataNascimento(Date dataNascimento) {
			entity.setDataNascimento(dataNascimento);
			return this;
		}
		
		public Builder rg(String rg) {
			entity.setRg(rg);
			return this;
		}
	
	}


}
