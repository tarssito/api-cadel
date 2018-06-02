package br.com.apicadel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.AlunoTurma;
import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.Classe;
import br.com.apicadel.domain.ClasseTurma;
import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.CursoDisciplina;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.ProfessorDisciplina;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.domain.enums.DiaSemana;
import br.com.apicadel.domain.enums.Perfil;
import br.com.apicadel.domain.enums.StatusAula;
import br.com.apicadel.domain.enums.TurnoLetivo;
import br.com.apicadel.repositories.AlunoRepository;
import br.com.apicadel.repositories.AlunoTurmaRepository;
import br.com.apicadel.repositories.ClasseRepository;
import br.com.apicadel.repositories.ClasseTurmaRepository;
import br.com.apicadel.repositories.CursoDisciplinaRepository;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;
import br.com.apicadel.repositories.ProfessorDisciplinaRepository;
import br.com.apicadel.repositories.ProfessorRepository;
import br.com.apicadel.repositories.TurmaRepository;
import br.com.apicadel.services.EmailService;

@SpringBootApplication
public class ApiCadelApplication implements CommandLineRunner {

	@Autowired
	private ClasseRepository classeRepository;

	@Autowired
	private ClasseTurmaRepository classeTurmaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private CursoDisciplinaRepository cursoDisciplinaRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private ProfessorDisciplinaRepository professorDisciplinaRepository;

	@Autowired
	private AlunoTurmaRepository alunoTurmaRepository;

	@Autowired
	private EmailService emailService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiCadelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Cursos
		Curso si = new Curso(null, "Sistemas de Informação");
		Curso cc = new Curso(null, "Ciência da Computação");
		Curso ec = new Curso(null, "Engenharia da Computação");
		Curso ea = new Curso(null, "Engenharia Ambiental");

		//Disciplinas
		Disciplina laboratorioDeSoftware = new Disciplina(null, "Laboratório de Software", 60);
		Disciplina gestaoProjeto = new Disciplina(null, "Gestão de Projetos", 50);
		Disciplina redes = new Disciplina(null, "Redes", 80);
		Disciplina sd = new Disciplina(null, "Sistemas Distribuídos", 80);
		Disciplina bdII = new Disciplina(null, "Banco de Dados 2", 80);
		Disciplina so = new Disciplina(null, "Sistemas Operacionais", 80);
		Disciplina ihc = new Disciplina(null, "IHC", 80);

		//Relação Curso-Disciplina
		CursoDisciplina cd1 = new CursoDisciplina(null, si, laboratorioDeSoftware);
		CursoDisciplina cd2 = new CursoDisciplina(null, si, gestaoProjeto);
		CursoDisciplina cd3 = new CursoDisciplina(null, si, redes);
		CursoDisciplina cd4 = new CursoDisciplina(null, si, sd);
		CursoDisciplina cd9 = new CursoDisciplina(null, si, bdII);
		CursoDisciplina cd11 = new CursoDisciplina(null, si, so);
		
		CursoDisciplina cd5 = new CursoDisciplina(null, cc, laboratorioDeSoftware);
		CursoDisciplina cd8 = new CursoDisciplina(null, cc, ihc);
		CursoDisciplina cd6 = new CursoDisciplina(null, cc, redes);
		CursoDisciplina cd7 = new CursoDisciplina(null, cc, sd);
		CursoDisciplina cd10 = new CursoDisciplina(null, cc, bdII);
		CursoDisciplina cd12 = new CursoDisciplina(null, cc, so);
		
		//Alunos
		Aluno aluno1 = new Aluno(null, "Davi Erick Filipe Ferreira", "13521250997", "042151000", "ddavierickfilipeferreira@comunicacao.com.br", "M");
		Aluno aluno2 = new Aluno(null, "Sebastião Leonardo Márcio Oliveira", "53460054980", "042151001", "sebastiaoleonador@hotmail.com", "M");
		Aluno aluno3 = new Aluno(null, "Louise Antônia Rita Ribeiro", "93724007698", "042151002", "louise.arr@hotmail.com", "F");
		Aluno aluno4 = new Aluno(null, "Renato Benício Galvão", "48173711372", "042151003", "renatobenicio@hotmail.com", "M");
		Aluno aluno5 = new Aluno(null, "Calebe José Ryan da Conceição", "42183029951", "042151004", "caleberyan@hotmail.com", "M");
		Aluno aluno6 = new Aluno(null, "Henry Marcos Ramos", "31139200941", "042151005", "henryramos@hotmail.com", "M");
		Aluno aluno7 = new Aluno(null, "Noah Daniel Luan Brito", "61589610601", "042151006", "noahbrito@hotmail.com", "M");
		Aluno aluno8 = new Aluno(null, "Fernando Anderson Enrico Monteiro", "14710985405", "042151007", "enricoanders@hotmail.com", "M");
		Aluno aluno9 = new Aluno(null, "Mariah Luna Raimunda Moreira", "40236200925", "042151008", "mariah@hotmail.com", "F");
		Aluno aluno10 = new Aluno(null, "Márcia Rosa Catarina Rodrigues", "47271096892", "042151009", "marciarosa@hotmail.com", "F");
		
		//Professor
		Professor prof1 = new Professor(null, "Admin", "12345678982", "admin", "admin@gmail.com", "M", true, 5,
				Perfil.ADMIN, BCrypt.hashpw("admin123", BCrypt.gensalt()), si);

		Professor prof2 = new Professor(null, "Victor Thomas Moura", "82026562490", "22145000", "victor@gmail.com", "M", true,
				5, Perfil.PROFESSOR, BCrypt.hashpw("82026562", BCrypt.gensalt()), si);

		Professor prof3 = new Professor(null, "Juan Ryan Cavalcanti", "33618093802", "22145001", "juanryan@gmail.com", "M",
				true, 5, Perfil.PROFESSOR, BCrypt.hashpw("33618093", BCrypt.gensalt()), si);

		Professor prof4 = new Professor(null, "Benício Jorge Pinto", "45382932450", "22145002", "benicio@gmail.com", "M", true, 5,
				Perfil.PROFESSOR, BCrypt.hashpw("45382932", BCrypt.gensalt()), si);

		Professor prof5 = new Professor(null, "Débora Stefany de Paula", "96875568624", "22145003", "deborasp@gmail.com", "F", true, 5,
				Perfil.PROFESSOR, BCrypt.hashpw("admin", BCrypt.gensalt()), si);
		
		//Relação Professor Disciplina
		ProfessorDisciplina pd1 = new ProfessorDisciplina(null, prof1, laboratorioDeSoftware);
		ProfessorDisciplina pd2 = new ProfessorDisciplina(null, prof2, redes);
		ProfessorDisciplina pd3 = new ProfessorDisciplina(null, prof3, sd);
		ProfessorDisciplina pd4 = new ProfessorDisciplina(null, prof3, so);
		ProfessorDisciplina pd5 = new ProfessorDisciplina(null, prof3, bdII);
		ProfessorDisciplina pd6 = new ProfessorDisciplina(null, prof4, gestaoProjeto);
		ProfessorDisciplina pd7 = new ProfessorDisciplina(null, prof5, ihc);
		
		//Turmas
		Turma si001LS = new Turma(null, "SI001", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), laboratorioDeSoftware);
		Turma si001GP = new Turma(null, "SI001", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), gestaoProjeto);
		Turma si001LR = new Turma(null, "SI001", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), redes);
		Turma si001IHC = new Turma(null, "SI001", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), ihc);
		Turma si001SD = new Turma(null, "SI001", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), sd);

		Turma cc001LS = new Turma(null, "CC001", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), laboratorioDeSoftware);
		Turma cc001GP = new Turma(null, "CC001", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), gestaoProjeto);
		Turma cc001LR = new Turma(null, "CC001", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), redes);
		Turma cc001SD = new Turma(null, "CC001", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), sd);

		//ALUNO 1 EM TODAS AS DISCIPLINAS DA TURMA SI001
		AlunoTurma aluno1Turma1 = new AlunoTurma(null, aluno1, si001LS);
		AlunoTurma aluno1Turma2 = new AlunoTurma(null, aluno1, si001GP);
		AlunoTurma aluno1Turma3 = new AlunoTurma(null, aluno1, si001LR);
		AlunoTurma aluno1Turma4 = new AlunoTurma(null, aluno1, si001IHC);
		AlunoTurma aluno1Turma5 = new AlunoTurma(null, aluno1, si001SD);
		
		//ALUNO 2 EM 4 DISCIPLINAS DA TURMA SI001 E EM UMA DISCIPLINA DA TURMA CC001
		AlunoTurma aluno2Turma1 = new AlunoTurma(null, aluno2, si001LS);
		AlunoTurma aluno2Turma2 = new AlunoTurma(null, aluno2, si001GP);
		AlunoTurma aluno2Turma3 = new AlunoTurma(null, aluno2, si001LR);
		AlunoTurma aluno2Turma4 = new AlunoTurma(null, aluno2, si001IHC);
		AlunoTurma aluno2Turma5 = new AlunoTurma(null, aluno2, cc001SD);
		
		//Classes
		Classe a1 = new Classe(null, DiaSemana.SEGUNDA, TurnoLetivo.NOTURNO, "18:45", "20:15", "1", "2018", si, prof2,
				laboratorioDeSoftware);
		Classe a2 = new Classe(null, DiaSemana.SEGUNDA, TurnoLetivo.NOTURNO, "20:15", "21:50", "1", "2018", si, prof2,
				laboratorioDeSoftware);

		Classe a3 = new Classe(null, DiaSemana.TERCA, TurnoLetivo.NOTURNO, "18:45", "20:15", "1", "2018", si, prof3,
				gestaoProjeto);
		Classe a4 = new Classe(null, DiaSemana.TERCA, TurnoLetivo.NOTURNO, "20:15", "21:50", "1", "2018", si, prof3,
				gestaoProjeto);

		Classe a5 = new Classe(null, DiaSemana.QUARTA, TurnoLetivo.NOTURNO, "18:45", "20:15", "1", "2018", si, prof4,
				redes);
		Classe a6 = new Classe(null, DiaSemana.QUARTA, TurnoLetivo.NOTURNO, "20:15", "21:50", "1", "2018", si, prof4,
				redes);

		Classe a7 = new Classe(null, DiaSemana.QUINTA, TurnoLetivo.NOTURNO, "18:45", "20:15", "1", "2018", si, prof5,
				ihc);
		Classe a8 = new Classe(null, DiaSemana.QUINTA, TurnoLetivo.NOTURNO, "20:15", "21:50", "1", "2018", si, prof5,
				ihc);

		Classe a9 = new Classe(null, DiaSemana.SABADO, TurnoLetivo.NOTURNO, "18:45", "20:15", "1", "2018", si, prof2,
				sd);
		Classe a10 = new Classe(null, DiaSemana.SABADO, TurnoLetivo.NOTURNO, "20:15", "21:50", "1", "2018", si, prof2,
				sd);
		
		//Relação Classe-Turma
		ClasseTurma at1 = new ClasseTurma(null, a1, si001LS);
		ClasseTurma at2 = new ClasseTurma(null, a2, si001LS);
		
		ClasseTurma at3 = new ClasseTurma(null, a3, si001GP);
		ClasseTurma at4 = new ClasseTurma(null, a4, si001GP);

		ClasseTurma at5 = new ClasseTurma(null, a5, si001LR);
		ClasseTurma at6 = new ClasseTurma(null, a6, si001LR);

		ClasseTurma at7 = new ClasseTurma(null, a7, si001IHC);
		ClasseTurma at8 = new ClasseTurma(null, a8, si001IHC);
		
		ClasseTurma at9 = new ClasseTurma(null, a8, si001SD);
		ClasseTurma at10 = new ClasseTurma(null, a9, si001SD);
		ClasseTurma at11 = new ClasseTurma(null, a8, cc001SD);
		ClasseTurma at12 = new ClasseTurma(null, a9, cc001SD);
		
		cursoRepository.saveAll(Arrays.asList(si, cc, ec, ea));
		
		disciplinaRepository.saveAll(Arrays.asList(laboratorioDeSoftware, gestaoProjeto, redes, sd, bdII, so, ihc));
		
		cursoDisciplinaRepository.saveAll(Arrays.asList(cd1, cd2, cd3, cd4, cd5, cd6, cd7, cd8, cd9, cd10, cd11, cd12));
		
		alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8, aluno9, aluno10));
		
		professorRepository.saveAll(Arrays.asList(prof1, prof2, prof3, prof4, prof5));
		
		professorDisciplinaRepository.saveAll(Arrays.asList(pd1, pd2, pd3, pd4, pd5, pd6, pd7));
		
		turmaRepository.saveAll(Arrays.asList(si001LS, si001GP, si001LR, si001IHC, si001SD, cc001LS, cc001GP, cc001LR, cc001SD));
		
		alunoTurmaRepository.saveAll(Arrays.asList(aluno1Turma1, aluno1Turma2, aluno1Turma3, aluno1Turma4, aluno1Turma5,
				aluno2Turma1, aluno2Turma2, aluno2Turma3, aluno2Turma4, aluno2Turma5));
		
		classeRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));
		
		classeTurmaRepository.saveAll(Arrays.asList(at1, at2, at3, at4, at5, at7, at9, at10, at11, at12));
		
		LocalDate dataDeInscricao = LocalDate.now();
		Date data = Date.from(dataDeInscricao.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		//emailService.sendOrderConfirmationEmail(aula1);
	}

}
