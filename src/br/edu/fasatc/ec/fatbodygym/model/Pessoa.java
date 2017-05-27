package br.edu.fasatc.ec.fatbodygym.model;

import java.util.Date;

public abstract class Pessoa extends AbstractEntidadeEntity {

	private static final long serialVersionUID = 3128570043988823792L;

	private Long id;
	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private Boolean ativo;

	public Pessoa() {
		this.ativo = Boolean.TRUE;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected void setCpf(String cpf) {
		this.cpf = cpf;
	}

	protected void setRg(String rg) {
		this.rg = rg;
	}

	protected void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	protected void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	protected void setId(Long id) {
		this.id = id;
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
		final Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
