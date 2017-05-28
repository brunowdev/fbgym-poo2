package br.edu.fasatc.ec.fatbodygym.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;
import br.edu.fasatc.ec.fatbodygym.model.treinos.ResultadoTreino;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_TREINOS)
public class Treino extends AbstractEntidadeEntity implements SearchableString {

	private static final long serialVersionUID = 8594356440602648459L;

	private Long id;
	private Aluno aluno;
	private List<Exercicio> exercicios;
	private Date data;
	private TipoTreino tipoTreino;
	private Double queimaCalorias;
	private Double ganhoMassaMuscular;

	public Treino() {
	}

	public Treino(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public List<Exercicio> getExercicios() {
		return Optional.of(exercicios).orElse(Collections.emptyList());
	}

	public Date getData() {
		return data;
	}

	public TipoTreino getTipoTreino() {
		return tipoTreino;
	}
	
	public Double getQueimaCalorias() {
		return queimaCalorias;
	}

	public void setQueimaCalorias(Double queimaCalorias) {
		this.queimaCalorias = queimaCalorias;
	}

	public Double getGanhoMassaMuscular() {
		return ganhoMassaMuscular;
	}

	public void setGanhoMassaMuscular(Double ganhoMassaMuscular) {
		this.ganhoMassaMuscular = ganhoMassaMuscular;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setTipoTreino(TipoTreino tipoTreino) {
		this.tipoTreino = tipoTreino;
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
		final Treino other = (Treino) obj;
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

		final List<String> contentFields = new ArrayList<>(12);

		final String aluno = Optional.ofNullable(getAluno().getNome()).orElse(null).toString();
		final List<String> exercicios = Optional.ofNullable(getExercicios()).orElse(null).stream().map(exercicio -> exercicio.getNome()).collect(Collectors.toList());
		final String tipoTreino = Optional.ofNullable(getTipoTreino()).orElse(null).toString();

		contentFields.add(aluno);
		contentFields.addAll(exercicios);
		contentFields.add(tipoTreino);

		return (String[]) contentFields.toArray();
	}
	
	public void finalizarTreino() {
		
		ResultadoTreino strategy = tipoTreino.getStrategy().get();
		
		this.queimaCalorias = strategy.calcularQueimaCalorias();

		this.ganhoMassaMuscular = strategy.calcularGanhoMassaMuscular();
		
	}

}
