package br.edu.fasatc.ec.fatbodygym.model;

import java.util.Objects;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;
import br.edu.fasatc.ec.fatbodygym.model.common.EntityBuilder;
import br.edu.fasatc.ec.fatbodygym.utils.security.Criptografia;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_USUARIOS)
public class Usuario extends AbstractEntidadeEntity implements SearchableString {

	private static final long serialVersionUID = -429947733530672916L;

	public Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = Criptografia.criptografar(senha);
	}

	private Long id;
	private String email;
	private String senha;
	private Aluno aluno;

	@Override
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Aluno getAluno() {
		return aluno;
	}

	protected void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected void setSenha(String senha) {
		this.senha = senha;
	}

	public void hidePersonalFields() {
		this.id = Long.MAX_VALUE;
		this.senha = "*******";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String[] getSearchableFields() {
		return new String[] { getEmail() };
	}

	/**
	 * A busca pelo usuário deverá ser exata para verificar se o usuário já
	 * existe. Se for necessário, pode ser criada uma outra implementação no
	 * repository.
	 */
	@Override
	public boolean containsMatch(String query) {
		return false;
	}

	public static boolean usuarioCorresponde(Usuario usuarioBaseDados, Usuario candidate) {

		Objects.requireNonNull(usuarioBaseDados);

		Objects.requireNonNull(candidate);

		final String candidatePassword = Criptografia.criptografar(candidate.getSenha());

		return usuarioBaseDados.getSenha().equals(candidatePassword);
	}
	
	public static class Builder extends EntityBuilder<Usuario> {
		
		protected Builder(Usuario aluno) {
			super(aluno);
		}

		public static Builder create() {
			return new Builder(new Usuario());
		}
		
		public static Builder from(Usuario from) {
			return new Builder(from);
		}
		
		
		public Builder email(String email) {
			entity.setEmail(email);
			return this;
		}

		public Builder senha(String senha) {
			entity.setSenha(Criptografia.criptografar(senha));
			return this;
		}

		public Builder aluno(Aluno aluno) {
			entity.setAluno(aluno);
			return this;
		}

	}

}
