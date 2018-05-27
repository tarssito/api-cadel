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
import br.com.apicadel.domain.enums.TurnoLetivo;
import br.com.apicadel.repositories.AlunoRepository;
import br.com.apicadel.repositories.ClasseRepository;
import br.com.apicadel.repositories.ClasseTurmaRepository;
import br.com.apicadel.repositories.CursoDisciplinaRepository;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;
import br.com.apicadel.repositories.ProfessorDisciplinaRepository;
import br.com.apicadel.repositories.ProfessorRepository;
import br.com.apicadel.repositories.TurmaRepository;

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

	public static void main(String[] args) {
		SpringApplication.run(ApiCadelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Curso si = new Curso(null, "Sistema de Informação");
		Curso cc = new Curso(null, "Ciência da Computação");

		Disciplina laboratorioDeSoftware = new Disciplina(null, "Laboratorio de Software", 60);
		Disciplina gestaoProjeto = new Disciplina(null, "Gestão de Projetos", 50);
		Disciplina redes = new Disciplina(null, "Redes", 80);
		Disciplina sd = new Disciplina(null, "Sistemas Distribuídos", 80);
		Disciplina bdII = new Disciplina(null, "Banco de Dados 2", 80);
		Disciplina so = new Disciplina(null, "Sistemas Operacionais", 80);
		Disciplina ihc = new Disciplina(null, "IHC", 80);

		Turma turma1 = new Turma(null, "SI011", "1", "2018", si, TurnoLetivo.MATUTINO.getTurno(),
				laboratorioDeSoftware);
		Turma turma2 = new Turma(null, "CC011", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), laboratorioDeSoftware);

		Turma turma3 = new Turma(null, "SI012", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), gestaoProjeto);
		Turma turma4 = new Turma(null, "CC012", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), gestaoProjeto);

		Turma turma5 = new Turma(null, "SI013", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), redes);
		Turma turma6 = new Turma(null, "CC013", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), redes);

		Turma turma7 = new Turma(null, "SI014", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), sd);
		Turma turma8 = new Turma(null, "CC014", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), sd);

		Turma turma9 = new Turma(null, "SI015", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), bdII);
		Turma turma10 = new Turma(null, "CC015", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), bdII);

		Turma turma11 = new Turma(null, "SI016", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), so);
		Turma turma12 = new Turma(null, "CC016", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), so);

		Turma turma13 = new Turma(null, "SI017", "1", "2018", si, TurnoLetivo.NOTURNO.getTurno(), ihc);
		Turma turma14 = new Turma(null, "CC017", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), ihc);

		disciplinaRepository.saveAll(Arrays.asList(laboratorioDeSoftware, gestaoProjeto, redes, sd, bdII, so, ihc));
		cursoRepository.saveAll(Arrays.asList(si, cc));

		CursoDisciplina cd1 = new CursoDisciplina(null, si, laboratorioDeSoftware);
		CursoDisciplina cd2 = new CursoDisciplina(null, si, gestaoProjeto);
		CursoDisciplina cd3 = new CursoDisciplina(null, si, redes);
		CursoDisciplina cd4 = new CursoDisciplina(null, si, sd);

		CursoDisciplina cd5 = new CursoDisciplina(null, cc, laboratorioDeSoftware);
		CursoDisciplina cd6 = new CursoDisciplina(null, cc, redes);
		CursoDisciplina cd7 = new CursoDisciplina(null, cc, sd);
		CursoDisciplina cd8 = new CursoDisciplina(null, cc, ihc);

		CursoDisciplina cd9 = new CursoDisciplina(null, si, bdII);
		CursoDisciplina cd10 = new CursoDisciplina(null, cc, bdII);

		CursoDisciplina cd11 = new CursoDisciplina(null, si, so);
		CursoDisciplina cd12 = new CursoDisciplina(null, cc, so);

		cursoDisciplinaRepository.saveAll(Arrays.asList(cd1, cd2, cd3, cd4, cd5, cd6, cd7, cd8, cd9, cd10, cd11, cd12));

		turmaRepository.saveAll(Arrays.asList(turma1, turma2, turma3, turma4, turma5, turma6, turma7, turma8, turma9,
				turma10, turma11, turma12, turma13, turma14));

		Professor prof1 = new Professor(null, "Admin", "00000000000", "00000000", "admin@gmail.com", "M", true, 5,
				Perfil.ADMIN, BCrypt.hashpw("admin", BCrypt.gensalt()), si);

		Professor prof2 = new Professor(null, "Sandro Alex", "05049493000", "22145000", "sandro@gmail.com", "M", true,
				5, Perfil.PROFESSOR, BCrypt.hashpw("admin", BCrypt.gensalt()), si);

		Professor prof3 = new Professor(null, "Eduardo Xavier", "05049493900", "22145001", "xavier@gmail.com", "M",
				true, 5, Perfil.PROFESSOR, BCrypt.hashpw("admin", BCrypt.gensalt()), si);

		Professor prof4 = new Professor(null, "Alex Coelho", "05049493922", "22145002", "alex@gmail.com", "M", true, 5,
				Perfil.PROFESSOR, BCrypt.hashpw("admin", BCrypt.gensalt()), si);

		Professor prof5 = new Professor(null, "Arthur", "05049493888", "22145003", "arthur@gmail.com", "M", true, 5,
				Perfil.PROFESSOR, BCrypt.hashpw("admin", BCrypt.gensalt()), si);

		professorRepository.saveAll(Arrays.asList(prof1, prof2, prof3, prof4, prof5));

		ProfessorDisciplina pd1 = new ProfessorDisciplina(null, prof1, laboratorioDeSoftware);
		ProfessorDisciplina pd2 = new ProfessorDisciplina(null, prof2, redes);
		ProfessorDisciplina pd3 = new ProfessorDisciplina(null, prof3, sd);
		ProfessorDisciplina pd4 = new ProfessorDisciplina(null, prof3, so);
		ProfessorDisciplina pd5 = new ProfessorDisciplina(null, prof3, bdII);
		ProfessorDisciplina pd6 = new ProfessorDisciplina(null, prof4, gestaoProjeto);
		ProfessorDisciplina pd7 = new ProfessorDisciplina(null, prof5, ihc);

		professorDisciplinaRepository.saveAll(Arrays.asList(pd1, pd2, pd3, pd4, pd5, pd6, pd7));

		Aluno aluno1 = new Aluno(null, "Tarssito Pereira", "03035268584", "042151013", "tarssito@hotmail.com", "M");
		Aluno aluno2 = new Aluno(null, "Ítalo Brazil", "03035268583", "042151012", "italo@hotmail.com", "M");
		Aluno aluno3 = new Aluno(null, "Ian Ítalo", "03035268582", "042151011", "ian@hotmail.com", "M");
		Aluno aluno4 = new Aluno(null, "Marcos Reis", "03035268581", "042151015", "marcos@hotmail.com", "M");
		Aluno aluno5 = new Aluno(null, "Gabriel Rebouças", "03035268587", "042151016", "gabriel@hotmail.com", "M");
		Aluno aluno6 = new Aluno(null, "Thiago Vinhas", "03035268589", "042151017", "vinhas@hotmail.com", "M");

		Aluno aluno7 = new Aluno(null, "Jeferson Souza", "02235268584", "042151023", "jeferson@hotmail.com", "M");
		Aluno aluno8 = new Aluno(null, "José Veríssimo", "12035268583", "042151022", "ze@hotmail.com", "M");
		Aluno aluno9 = new Aluno(null, "Danilo Reis", "15035268582", "042151021", "danilo@hotmail.com", "M");
		Aluno aluno10 = new Aluno(null, "Igor Carmo", "12032338581", "042151025", "rael@hotmail.com", "M");
		Aluno aluno11 = new Aluno(null, "Wasgton Silva", "10035268587", "042151026", "wasgton@hotmail.com", "M");
		Aluno aluno12 = new Aluno(null, "Ricardo Oliveira", "03035268900", "042151027", "ricardo@hotmail.com", "M");

		alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8, aluno9,
				aluno10, aluno11, aluno12));

		/**
		 * Dump de classe
		 */
		LocalDate dataDeInscricao = LocalDate.now();
		Date data = Date.from(dataDeInscricao.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Classe a1 = new Classe(null, data, DiaSemana.SEGUNDA, TurnoLetivo.NOTURNO, "18:45", "20:15", si, prof1,
				laboratorioDeSoftware);
		Classe a2 = new Classe(null, data, DiaSemana.SEGUNDA, TurnoLetivo.NOTURNO, "20:15", "21:50", si, prof1,
				laboratorioDeSoftware);

		Classe a3 = new Classe(null, data, DiaSemana.TERCA, TurnoLetivo.NOTURNO, "18:45", "20:15", si, prof4, gestaoProjeto);
		Classe a4 = new Classe(null, data, DiaSemana.TERCA, TurnoLetivo.NOTURNO, "20:15", "21:50", si, prof4, gestaoProjeto);

		Classe a5 = new Classe(null, data, DiaSemana.QUARTA, TurnoLetivo.NOTURNO, "18:45", "20:15", si, prof2, redes);
		Classe a6 = new Classe(null, data, DiaSemana.QUARTA, TurnoLetivo.NOTURNO, "20:15", "21:50", si, prof2, redes);

		Classe a7 = new Classe(null, data, DiaSemana.QUINTA, TurnoLetivo.NOTURNO, "18:45", "20:15", si, prof5, ihc);
		Classe a8 = new Classe(null, data, DiaSemana.QUINTA, TurnoLetivo.NOTURNO, "20:15", "21:50", si, prof5, ihc);

		Classe a9 = new Classe(null, data, DiaSemana.SEXTA, TurnoLetivo.NOTURNO, "18:45", "20:15", si, prof3, sd);
		Classe a10 = new Classe(null, data, DiaSemana.SEXTA, TurnoLetivo.NOTURNO, "20:15", "21:50", si, prof3, sd);

		classeRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));

		ClasseTurma at1 = new ClasseTurma(null, a1, turma1);
		ClasseTurma at2 = new ClasseTurma(null, a1, turma2);
		ClasseTurma at3 = new ClasseTurma(null, a2, turma1);
		ClasseTurma at4 = new ClasseTurma(null, a2, turma2);

		ClasseTurma at5 = new ClasseTurma(null, a3, turma3);
		ClasseTurma at7 = new ClasseTurma(null, a4, turma3);

		ClasseTurma at9 = new ClasseTurma(null, a5, turma5);
		ClasseTurma at10 = new ClasseTurma(null, a6, turma6);
		ClasseTurma at11 = new ClasseTurma(null, a5, turma5);
		ClasseTurma at12 = new ClasseTurma(null, a6, turma6);

		ClasseTurma at13 = new ClasseTurma(null, a7, turma13);
		ClasseTurma at14 = new ClasseTurma(null, a8, turma13);

		ClasseTurma at15 = new ClasseTurma(null, a5, turma7);
		ClasseTurma at16 = new ClasseTurma(null, a6, turma8);
		ClasseTurma at17 = new ClasseTurma(null, a5, turma7);
		ClasseTurma at18 = new ClasseTurma(null, a6, turma8);

		classeTurmaRepository
				.saveAll(Arrays.asList(at1, at2, at3, at4, at5, at7, at9, at10, at11, at12, at13, at14, at15, at16, at17, at18));
	}

}
