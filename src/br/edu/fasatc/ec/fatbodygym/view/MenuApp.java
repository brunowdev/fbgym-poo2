package br.edu.fasatc.ec.fatbodygym.view;

import java.awt.Frame;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.edu.fasatc.ec.fatbodygym.exceptions.EntidadeNaoEncontradaException;
import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.Aluno;
import br.edu.fasatc.ec.fatbodygym.model.Exercicio;
import br.edu.fasatc.ec.fatbodygym.model.Instrutor;
import br.edu.fasatc.ec.fatbodygym.model.TipoExercicio;
import br.edu.fasatc.ec.fatbodygym.model.TipoTreino;
import br.edu.fasatc.ec.fatbodygym.model.Treino;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.AlunoRepository;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.ExercicioRepository;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.InstrutorRepository;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.TreinoRepository;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.UsuarioRepository;
import br.edu.fasatc.ec.fatbodygym.relatorios.Relatorios;
import br.edu.fasatc.ec.fatbodygym.view.login.LoginGUI;

public class MenuApp {

	private static Scanner scanner;
	private static Usuario usuarioLogado;

	static {

		try {
			scanner = new Scanner(System.in);
		} catch (final Exception exc) {
			sair();
		}

	}

	public static void main(String... args) {

		try {
			final Frame jf = new Frame();
			final LoginGUI login = new LoginGUI(jf, true);
			login.setVisible(true);
			exibir();
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ocorreu um erro durante a execução do programa.\n\nErro: " + e.getMessage());
			e.printStackTrace();
			sair();
		}

	}

	public static void exibir() throws WriteFileException, ReadFileException, FileNotFoundException {
		final AbstractBaseMenu menu = retornaMenu(usuarioLogado);
		invokeMenu(menu);
		sair();
	}

	private static AbstractBaseMenu retornaMenu(Usuario usuario) {
		return (usuario.getAluno() == null) ? new MenuInstrutor(scanner) : new MenuAluno(scanner);
	}

	private static void invokeMenu(AbstractBaseMenu menu)
			throws WriteFileException, ReadFileException, FileNotFoundException {

		if (menu instanceof MenuAluno) {
			escolherOpcaoMenuAluno(menu);
		} else {
			escolherOpcaoMenuInstrutor(menu);
		}

	}

	// Menu dos alunos

	private static void escolherOpcaoMenuAluno(AbstractBaseMenu menu) throws WriteFileException, ReadFileException {

		int opcao = menu.selecionarOpcao();

		// Captura o aluno refernte ao usu�rio
		final Aluno aluno = usuarioLogado.getAluno();

		if (aluno == null) {
			throw new IllegalStateException("O usuário atual não pode acessar os recursos como um aluno.");
		}

		final TreinoRepository treinoRepository = new TreinoRepository();

		while (opcao != 0) {

			switch (opcao) {
			case 1:
				treinoRepository.merge(lerTreino(menu, null));
				break;
			case 2:
				final Treino treino = localizarTreinoParaEditar(menu);
				treinoRepository.merge(lerTreino(menu, treino));
				break;
			case 3:
				localizarTreinoPorTexto(menu);
				break;
			case 4:
				localizarTreinoPorCodigo(menu);
				break;
			case 5:
				listarTreinos(menu);
				break;
			case 6:
				localizarTreinoParaRemover(menu);
				break;
			case 0:
				break;
			}

			opcao = menu.selecionarOpcao();
		}

	}

	private static Treino localizarTreinoParaEditar(AbstractBaseMenu menu) {
		final TreinoRepository treinoRepository = new TreinoRepository();

		Treino treino = null;
		while (treino == null) {

			try {
				System.out.println("Informe o código para alterar: ");
				treino = treinoRepository.findById(new Treino(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (treino == null) {
				System.out.println("Treino não encontrado!");
			}
		}

		return treino;
	}

	private static Treino localizarTreinoParaRemover(AbstractBaseMenu menu)
			throws WriteFileException, ReadFileException {
		final TreinoRepository treinoRepository = new TreinoRepository();

		Treino treino = null;
		while (treino == null) {

			try {
				System.out.println("Informe o código para remover: ");
				treino = treinoRepository.findById(new Treino(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (treino == null) {
				System.out.println("Treino não encontrado!");
			} else {
				treinoRepository.remove(treino);
				System.out.println("Treino removido com sucesso!");
			}

		}

		return treino;
	}

	private static void localizarTreinoPorTexto(AbstractBaseMenu menu) {
		final TreinoRepository treinoRepository = new TreinoRepository();
		Treino treino = null;

		while (treino == null) {

			try {
				System.out.println("Informe o nome de algum exercício para buscar: ");
				final String texto = menu.lerTexto();
				treino = treinoRepository.findByStringFields(texto);
				imprimirTreino(treino);
			} catch (final Exception e) {
			}

			if (treino == null) {
				System.out.println("Treino não encontrado!");
			}
		}

	}

	private static void localizarTreinoPorCodigo(AbstractBaseMenu menu) {
		final TreinoRepository treinoRepository = new TreinoRepository();
		Treino treino = null;

		while (treino == null) {

			try {
				System.out.println("Informe o código para buscar: ");
				final Long id = menu.lerLong();
				treino = treinoRepository.findById(new Treino(id));
				imprimirTreino(treino);
			} catch (final Exception e) {
			}

			if (treino == null) {
				System.out.println("Treino não encontrado!");
			}
		}

	}

	private static void imprimirTreino(Treino treino) {

		System.out.println("\n\n");
		System.out.println("Código: " + treino.getId());
		System.out.println("Tipo: " + treino.getTipoTreino().toString());
		System.out.println("Data: " + treino.getData());
		System.out.println("Calorias: " + treino.getQueimaCalorias());
		System.out.println("Exercícios: ");
		Optional.of(treino.getExercicios()).orElse(Collections.emptyList()).stream()
				.forEach(exercicio -> System.out.println(exercicio.getNome()));

	}

	/**
	 * M�todo que cadastra um novo treino ou altera um j� existente.
	 *
	 * @param menu
	 * @param aluno
	 * @return
	 * @throws ReadFileException
	 */
	private static Treino lerTreino(AbstractBaseMenu menu, Treino treino) throws ReadFileException {

		final Treino treinoParaSalvar = treino == null ? new Treino() : treino;
		final Aluno aluno = usuarioLogado.getAluno();

		System.out.println((treino == null ? ("Cadastrando") : ("Alterando")) + " treino");
		System.out.println("Aluno: " + aluno.getNome());
		treinoParaSalvar.setAluno(aluno);
		System.out.println("Exercícios: > ");
		treinoParaSalvar.setExercicios(lerExerciciosPraticados());
		System.out.println("Data do treino: (dd/mm/aaaa) > ");
		treinoParaSalvar.setData(menu.lerData(true));
		System.out.println("Tipo: > ");
		treinoParaSalvar.setTipoTreino(TipoTreino.fromInteger(menu.lerInteiro()));
		System.out.println("Treino " + (aluno == null ? ("cadastrado") : ("alterado")) + " com sucesso!");

		treinoParaSalvar.finalizarTreino();

		return treinoParaSalvar;
	}

	private static List<Exercicio> lerExerciciosPraticados() throws ReadFileException {

		final ExercicioRepository exercicioRepository = new ExercicioRepository();
		final List<Exercicio> exercicios = new ArrayList<>();
		Long codigoExercicio = -1L;

		while (codigoExercicio != 0) {

			System.out.println("Informe os exerc�cios praticados: ");
			System.out.println("(0 para sair) > ");
			codigoExercicio = scanner.nextLong();
			scanner.nextLine();

			if (codigoExercicio == 0) {
				break;
			}

			Exercicio exercicio = null;

			try {
				exercicio = exercicioRepository.findById(new Exercicio(codigoExercicio));
			} catch (final EntidadeNaoEncontradaException e) {
				System.out.println("Exercício não encontrado!");
			}

			if (exercicios.contains(exercicio)) {
				System.out.println("Você já adicionou este exercício ao seu treino.");
			} else {
				exercicios.add(exercicio);
				System.out.println("Exercício adicionado ao treino com sucesso.");
			}

		}

		return exercicios;
	}

	private static void listarTreinos(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final TreinoRepository treinoRepository = new TreinoRepository();

		final Aluno aluno = usuarioLogado.getAluno();

		System.out.println("Listando treinos: ");
		List<Treino> treinos = new ArrayList<>();
		treinos = treinoRepository.findAll();
		treinos.stream().filter(treino -> treino.getAluno().equals(aluno)).forEach(treino -> imprimirTreino(treino));

	}

	// Menu dos instrutores

	private static void escolherOpcaoMenuInstrutor(AbstractBaseMenu menu)
			throws WriteFileException, ReadFileException, FileNotFoundException {

		int opcao = menu.selecionarOpcao();

		final AlunoRepository alunoRepository = new AlunoRepository();
		final ExercicioRepository exercicioRepository = new ExercicioRepository();
		final InstrutorRepository instrutorRepository = new InstrutorRepository();
		final UsuarioRepository usuarioRepository = new UsuarioRepository();

		while (opcao != 0) {

			switch (opcao) {

			case 1:
				final Aluno alunoCadastrado = alunoRepository.merge(lerAluno(menu, null));

				final Usuario usuarioAluno = usuarioRepository.merge(lerUsuario(menu, null));

				Usuario.Builder usuarioBuilder = Usuario.Builder.from(usuarioAluno);

				usuarioBuilder.aluno(alunoCadastrado);
				usuarioRepository.merge(usuarioBuilder.build());
				break;
			case 2:
				final Aluno aluno = localizarAlunoParaEditar(menu);
				alunoRepository.merge(lerAluno(menu, aluno));
				break;
			case 3:
				localizarAlunoPorTexto(menu);
				break;
			case 4:
				localizarAlunoPorCodigo(menu);
				break;
			case 5:
				listarAlunos(menu);
				break;
			case 6:
				localizarRemoverAlunoPorCodigo(menu);
				break;
			case 7:
				exercicioRepository.merge(lerExercicio(menu, null));
				break;
			case 8:
				final Exercicio exercicio = localizarExercicioParaEditar(menu);
				exercicioRepository.merge(lerExercicio(menu, exercicio));
				break;
			case 9:
				localizarExercicioPorTexto(menu);
				break;
			case 10:
				localizarExercicioPorCodigo(menu);
				break;
			case 11:
				listarExercicios(menu);
				break;
			case 12:
				localizarRemoverExercicioPorCodigo(menu);
				break;
			case 13:
				usuarioRepository.merge(lerUsuario(menu, null));
				break;
			case 14:
				final Usuario usuario = localizarUsuarioParaEditar(menu);
				usuarioRepository.merge(lerUsuario(menu, usuario));
				break;
			case 15:
				localizarUsuarioPorEmail(menu);
				break;
			case 16:
				localizarUsuarioPorCodigo(menu);
				break;
			case 17:
				listarUsuarios(menu);
				break;
			case 18:
				localizarRemoverUsuarioPorCodigo(menu);
				break;
			case 19:
				instrutorRepository.merge(lerInstrutor(menu, null));
				break;
			case 20:
				final Instrutor instrutor = localizarInstrutorParaEditar(menu);
				instrutorRepository.merge(lerInstrutor(menu, instrutor));
				break;
			case 21:
				localizarInstrutorPorTexto(menu);
				break;
			case 22:
				localizarInstrutorPorCodigo(menu);
				break;
			case 23:
				listarInstrutores(menu);
				break;
			case 24:
				localizarRemoverInstrutorPorCodigo(menu);
				break;
			case 25:
				gerarRelatorio(menu, 1);
				break;
			case 26:
				gerarRelatorio(menu, 2);
				break;
			case 27:
				gerarRelatorio(menu, 3);
				break;

			case 0:
				break;
			}
			opcao = menu.selecionarOpcao();
		}

	}

	private static void gerarRelatorio(AbstractBaseMenu menu, int idRelatorio)
			throws FileNotFoundException, WriteFileException, ReadFileException {

		final TreinoRepository treinoRepository = new TreinoRepository();
		System.out.println("Informe o caminho para gerar o relat�rio: ");
		final String caminhoRelatorio = menu.lerTexto();

		switch (idRelatorio) {
		case 1:
			Relatorios.relatorioExercicioMaisPraticados(caminhoRelatorio, treinoRepository.findAll());
			break;

		case 2:
			Relatorios.relatorioAlunosMaisAtivos(caminhoRelatorio, treinoRepository.findAll());
			break;

		case 3:
			final AlunoRepository alunoRepository = new AlunoRepository();
			Relatorios.relatorioAlunos(caminhoRelatorio, alunoRepository.findAll());
			break;

		default:
			break;
		}

	}

	/**
	 * M�todo que cadastra um novo aluno ou altera um j� existente.
	 *
	 * @param menu
	 * @param aluno
	 * @return
	 */
	private static Aluno lerAluno(AbstractBaseMenu menu, Aluno aluno) {

		final Aluno.Builder alunoBuilder = Objects.isNull(aluno) ? Aluno.Builder.create() : Aluno.Builder.from(aluno);

		System.out.println((aluno == null ? ("Cadastrando") : ("Alterando")) + " aluno");
		System.out.println("Nome: > ");
		alunoBuilder.nome(menu.lerTexto());
		System.out.println("CPF: > ");
		alunoBuilder.cpf(menu.lerTexto());
		System.out.println("RG: > ");
		alunoBuilder.rg(menu.lerTexto());
		System.out.println("Data nascimento: (dd/mm/aaaa) > ");
		alunoBuilder.dataNascimento(menu.lerData(true));
		System.out.println("Peso: (kg) > ");
		alunoBuilder.peso(menu.lerBigdecimal());
		System.out.println("Aluno " + (aluno == null ? ("cadastrado") : ("alterado")) + " com sucesso!");

		return alunoBuilder.build();
	}

	private static Aluno localizarAlunoParaEditar(AbstractBaseMenu menu) {
		final AlunoRepository alunoRepository = new AlunoRepository();
		Aluno aluno = null;

		while (aluno == null) {

			try {
				System.out.println("Informe o código para alterar: ");
				aluno = alunoRepository.findById(new Aluno(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno não encontrado!");
			}
		}

		return aluno;
	}

	private static void localizarAlunoPorTexto(AbstractBaseMenu menu) {
		final AlunoRepository alunoRepository = new AlunoRepository();
		Aluno aluno = null;

		while (aluno == null) {

			try {
				System.out.println("Informe o CPF, nome ou RG para buscar: ");
				final String texto = menu.lerTexto();
				aluno = alunoRepository.findByStringFields(texto);
				imprimirAluno(aluno);
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno não encontrado!");
			}
		}

	}

	private static void localizarAlunoPorCodigo(AbstractBaseMenu menu) {
		final AlunoRepository alunoRepository = new AlunoRepository();
		Aluno aluno = null;

		while (aluno == null) {

			try {
				System.out.println("Informe o código para buscar: ");
				final Long id = menu.lerLong();
				aluno = alunoRepository.findById(new Aluno(id));
				imprimirAluno(aluno);
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno não encontrado!");
			}
		}

	}

	private static void localizarRemoverAlunoPorCodigo(AbstractBaseMenu menu)
			throws WriteFileException, ReadFileException {
		final AlunoRepository alunoRepository = new AlunoRepository();
		Aluno aluno = null;

		while (aluno == null) {

			try {
				System.out.println("Informe o código para remover: ");
				final Long id = menu.lerLong();
				aluno = alunoRepository.findById(new Aluno(id));
				imprimirAluno(aluno);
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno não encontrado!");
			} else {
				alunoRepository.remove(aluno);
				System.out.println("Aluno removido com sucesso!");
			}
		}

	}

	private static void listarAlunos(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final AlunoRepository alunoRepository = new AlunoRepository();

		System.out.println("Listando alunos: ");
		List<Aluno> alunos = new ArrayList<>();
		alunos = alunoRepository.findAll();
		alunos.stream().forEach(aluno -> imprimirAluno(aluno));

	}

	private static void imprimirAluno(Aluno aluno) {

		System.out.println("\n\n");
		System.out.println("código: " + aluno.getId());
		System.out.println("Nome: " + aluno.getNome());
		System.out.println("CPF: " + aluno.getCpf());
		System.out.println("RG: " + aluno.getRg());
		System.out.println("Data nascimento: " + aluno.getDataNascimento());
		System.out.println("Peso: " + aluno.getPeso());

	}

	/**
	 * M�todo que cadastra um novo Exerc�cio ou altera um j� existente.
	 *
	 * @param menu
	 * @param aluno
	 * @return
	 */
	private static Exercicio lerExercicio(AbstractBaseMenu menu, Exercicio exercicio) {

		final Exercicio exercicioParaSalvar = exercicio == null ? new Exercicio() : exercicio;

		System.out.println(exercicio == null ? ("Cadastrando") : ("Alterando") + " exerc�cio");
		System.out.println("Nome: > ");
		exercicioParaSalvar.setNome(menu.lerTexto());
		System.out.println("S�ries: > ");
		exercicioParaSalvar.setSeries(menu.lerInteiro());
		System.out.println("Quantidade s�rie: > ");
		exercicioParaSalvar.setQuantidadeSerie(menu.lerInteiro());
		System.out.println("Tipo: > ");
		exercicioParaSalvar.setTipoExercicio(TipoExercicio.fromInteger(menu.lerInteiro()));
		System.out.println("Exerc�cio " + exercicio == null ? ("cadastrado") : ("alterado") + " com sucesso!");

		return exercicioParaSalvar;
	}

	private static Exercicio localizarExercicioParaEditar(AbstractBaseMenu menu) {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;
		while (exercicio == null) {

			try {
				System.out.println("Informe o código para alterar: ");
				exercicio = exercicioRepository.findById(new Exercicio(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exerc�cio não encontrado!");
			}
		}

		return exercicio;
	}

	private static void localizarExercicioPorTexto(AbstractBaseMenu menu) {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;

		while (exercicio == null) {

			try {
				System.out.println("Informe o nome para buscar: ");
				final String texto = menu.lerTexto();
				exercicio = exercicioRepository.findByStringFields(texto);
				imprimirExercicio(exercicio);
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exerc�cio não encontrado!");
			}
		}

	}

	private static void localizarExercicioPorCodigo(AbstractBaseMenu menu) {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;

		while (exercicio == null) {

			try {
				System.out.println("Informe o código para buscar: ");
				final Long id = menu.lerLong();
				exercicio = exercicioRepository.findById(new Exercicio(id));
				imprimirExercicio(exercicio);
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exerc�cio não encontrado!");
			}
		}

	}

	private static void localizarRemoverExercicioPorCodigo(AbstractBaseMenu menu)
			throws WriteFileException, ReadFileException {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;

		while (exercicio == null) {

			try {
				System.out.println("Informe o código para remover: ");
				final Long id = menu.lerLong();
				exercicio = exercicioRepository.findById(new Exercicio(id));
				imprimirExercicio(exercicio);
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exerc�cio não encontrado!");
			} else {
				exercicioRepository.remove(exercicio);
				System.out.println("Exercício removido com sucesso!");
			}
		}

	}

	private static void listarExercicios(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		System.out.println("Listando exercícios: ");
		List<Exercicio> exercicios = new ArrayList<>();
		exercicios = exercicioRepository.findAll();
		exercicios.stream().forEach(exercicio -> imprimirExercicio(exercicio));

	}

	private static void imprimirExercicio(Exercicio exercicio) {

		System.out.println("\n\n");
		System.out.println("Código: " + exercicio.getId());
		System.out.println("Nome: " + exercicio.getNome());
		System.out.println("Séries: " + exercicio.getSeries());
		System.out.println("Tipo exerc�cio: " + exercicio.getTipoExercicio().toString());

	}

	/**
	 * M�todo que cadastra um novo instrutor ou altera um j� existente.
	 *
	 * @param menu
	 * @param instrutor
	 * @return
	 */
	private static Instrutor lerInstrutor(AbstractBaseMenu menu, Instrutor instrutor) {

		final Instrutor.Builder instrutorBuilder = Objects.isNull(instrutor) ? Instrutor.Builder.create()
				: Instrutor.Builder.from(instrutor);

		System.out.println((instrutor == null ? ("Cadastrando") : ("Alterando")) + " instrutor");
		System.out.println("Nome: > ");
		instrutorBuilder.nome(menu.lerTexto());
		System.out.println("CPF: > ");
		instrutorBuilder.cpf(menu.lerTexto());
		System.out.println("RG: > ");
		instrutorBuilder.rg(menu.lerTexto());
		System.out.println("Data nascimento: (dd/mm/aaaa) > ");
		instrutorBuilder.dataNascimento(menu.lerData(true));
		System.out.println("Instrutor " + (instrutor == null ? ("cadastrado") : ("alterado")) + " com sucesso!");

		return instrutorBuilder.build();
	}

	private static Instrutor localizarInstrutorParaEditar(AbstractBaseMenu menu) {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();
		Instrutor instrutor = null;

		while (instrutor == null) {

			try {
				System.out.println("Informe o código para alterar: ");
				instrutor = instrutorRepository.findById(new Instrutor(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (instrutor == null) {
				System.out.println("Instrutor não encontrado!");
			}
		}

		return instrutor;
	}

	private static void localizarInstrutorPorTexto(AbstractBaseMenu menu) {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();
		Instrutor instrutor = null;

		while (instrutor == null) {

			try {
				System.out.println("Informe o CPF, nome ou RG para buscar: ");
				final String texto = menu.lerTexto();
				instrutor = instrutorRepository.findByStringFields(texto);
				imprimirInstrutor(instrutor);
			} catch (final Exception e) {
			}

			if (instrutor == null) {
				System.out.println("Instrutor não encontrado!");
			}
		}

	}

	private static void localizarInstrutorPorCodigo(AbstractBaseMenu menu) {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();
		Instrutor instrutor = null;

		while (instrutor == null) {

			try {
				System.out.println("Informe o código para buscar: ");
				final Long id = menu.lerLong();
				instrutor = instrutorRepository.findById(new Instrutor(id));
				imprimirInstrutor(instrutor);
			} catch (final Exception e) {
			}

			if (instrutor == null) {
				System.out.println("Instrutor não encontrado!");
			}
		}

	}

	private static void localizarRemoverInstrutorPorCodigo(AbstractBaseMenu menu)
			throws WriteFileException, ReadFileException {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();
		Instrutor instrutor = null;

		while (instrutor == null) {

			try {
				System.out.println("Informe o código para remover: ");
				final Long id = menu.lerLong();
				instrutor = instrutorRepository.findById(new Instrutor(id));
				imprimirInstrutor(instrutor);
			} catch (final Exception e) {
			}

			if (instrutor == null) {
				System.out.println("Instrutor não encontrado!");
			} else {
				instrutorRepository.remove(instrutor);
				System.out.println("Instrutor removido com sucesso!");
			}
		}

	}

	private static void listarInstrutores(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();

		System.out.println("Listando instrutores: ");
		List<Instrutor> instrutores = new ArrayList<>();
		instrutores = instrutorRepository.findAll();
		instrutores.stream().forEach(instrutor -> imprimirInstrutor(instrutor));

	}

	private static void imprimirInstrutor(Instrutor instrutor) {

		System.out.println("\n\n");
		System.out.println("código: " + instrutor.getId());
		System.out.println("Nome: " + instrutor.getNome());
		System.out.println("CPF: " + instrutor.getCpf());
		System.out.println("RG: " + instrutor.getRg());
		System.out.println("Data nascimento: " + instrutor.getDataNascimento());

	}

	/**
	 * M�todo que cadastra um novo usu�rio ou altera um j� existente.
	 *
	 * @param menu
	 * @param usuario
	 * @return
	 */
	private static Usuario lerUsuario(AbstractBaseMenu menu, Usuario usuario) {

		final Usuario.Builder usuarioBuilder = Objects.isNull(usuario) ? Usuario.Builder.create()
				: Usuario.Builder.from(usuario);

		System.out.println((Objects.isNull(usuario) ? ("Cadastrando") : ("Alterando")) + " usuário");
		System.out.println("E-mail: > ");
		usuarioBuilder.email(menu.lerTexto());
		System.out.println("Senha: > ");
		usuarioBuilder.senha(menu.lerTexto());
		System.out.println("Usuário " + (usuario == null ? ("cadastrado") : ("alterado")) + " com sucesso!");

		return usuarioBuilder.build();
	}

	private static Usuario localizarUsuarioParaEditar(AbstractBaseMenu menu) {
		final UsuarioRepository usuarioRepository = new UsuarioRepository();
		Usuario usuario = null;

		while (usuario == null) {

			try {
				System.out.println("Informe o código para alterar: ");
				usuario = usuarioRepository.findById(new Usuario(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (usuario == null) {
				System.out.println("Usu�rio não encontrado!");
			}
		}

		return usuario;
	}

	private static void localizarUsuarioPorEmail(AbstractBaseMenu menu) {
		final UsuarioRepository usuarioRepository = new UsuarioRepository();
		Usuario usuario = null;

		while (usuario == null) {

			try {
				System.out.println("Informe o e-mail para buscar: ");
				final String texto = menu.lerTexto();
				usuario = usuarioRepository.findByStringFields(texto);
				imprimirUsuario(usuario);
			} catch (final Exception e) {
			}

			if (usuario == null) {
				System.out.println("Usuário não encontrado!");
			}
		}

	}

	private static void localizarUsuarioPorCodigo(AbstractBaseMenu menu) {
		final UsuarioRepository usuarioRepository = new UsuarioRepository();
		Usuario usuario = null;

		while (usuario == null) {

			try {
				System.out.println("Informe o código para buscar: ");
				final Long id = menu.lerLong();
				usuario = usuarioRepository.findById(new Usuario(id));
				imprimirUsuario(usuario);
			} catch (final Exception e) {
			}

			if (usuario == null) {
				System.out.println("Usuário não encontrado!");
			}
		}

	}

	private static void localizarRemoverUsuarioPorCodigo(AbstractBaseMenu menu)
			throws WriteFileException, ReadFileException {
		final UsuarioRepository usuarioRepository = new UsuarioRepository();
		Usuario usuario = null;

		while (usuario == null) {

			try {
				System.out.println("Informe o código para buscar: ");
				final Long id = menu.lerLong();
				usuario = usuarioRepository.findById(new Usuario(id));
				imprimirUsuario(usuario);
			} catch (final Exception e) {
			}

			if (usuario == null) {
				System.out.println("Usuário não encontrado!");
			} else {
				usuarioRepository.remove(usuario);
				System.out.println("Usuário removido com sucesso!");
			}
		}

	}

	private static void listarUsuarios(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final UsuarioRepository usuarioRepository = new UsuarioRepository();

		System.out.println("Listando usuários: ");
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = usuarioRepository.findAll();
		usuarios.stream().forEach(usuario -> imprimirUsuario(usuario));
	}

	private static void imprimirUsuario(Usuario usuario) {

		System.out.println("\n\n");
		System.out.println("Código: " + usuario.getId());
		System.out.println("E-mail: " + usuario.getEmail());

	}

	private static void sair() {
		System.out.println("Fechando");
		fecharRecursosEstaticos();
		System.exit(0);
	}

	private static void fecharRecursosEstaticos() {
		if (scanner != null) {
			scanner.close();
		}
	}

	public static void definirUsuarioLogado(Usuario usuario) {
		usuarioLogado = usuario;
	}

}
