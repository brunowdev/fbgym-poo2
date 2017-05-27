package br.edu.fasatc.ec.fatbodygym.model;

import java.math.BigDecimal;
import java.util.Date;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;
import br.edu.fasatc.ec.fatbodygym.model.common.EntityBuilder;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_ALUNOS)
public final class Aluno extends Pessoa implements SearchableString {

	private static final long serialVersionUID = 7793253166397502975L;

	public Aluno(Long id) {
		setId(id);
	}

	public Aluno() {
	}

	private BigDecimal peso;

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	@Override
	public String[] getSearchableFields() {
		return new String[] { getCpf(), getNome(), getRg() };
	}

	public static class Builder extends EntityBuilder<Aluno> {
		
		protected Builder(Aluno aluno) {
			super(aluno);
		}

		public static Builder create() {
			return new Builder(new Aluno());
		}

		public static Builder from(Aluno aluno) {
			return new Builder(aluno);
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
		
		public Builder peso(BigDecimal peso) {
			entity.setPeso(peso);
			return this;
		}
		
		public Builder rg(String rg) {
			entity.setRg(rg);
			return this;
		}
	
	}

}
