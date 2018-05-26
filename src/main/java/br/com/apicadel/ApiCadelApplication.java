package br.com.apicadel;

import java.util.Arrays;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.CursoDisciplina;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.ProfessorDisciplina;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.domain.enums.Perfil;
import br.com.apicadel.domain.enums.TurnoLetivo;
import br.com.apicadel.repositories.AlunoRepository;
import br.com.apicadel.repositories.CursoDisciplinaRepository;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;
import br.com.apicadel.repositories.ProfessorDisciplinaRepository;
import br.com.apicadel.repositories.ProfessorRepository;
import br.com.apicadel.repositories.TurmaRepository;

@SpringBootApplication
public class ApiCadelApplication implements CommandLineRunner {

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
		Disciplina ihc = new Disciplina(null, "IHC", 80);
		
		Turma turma1 = new Turma(null, "SI01", "1", "2018", si, TurnoLetivo.MATUTINO.getTurno(), laboratorioDeSoftware);
		Turma turma2 = new Turma(null, "CC01", "1", "2018", cc, TurnoLetivo.NOTURNO.getTurno(), laboratorioDeSoftware);

		disciplinaRepository.saveAll(Arrays.asList(laboratorioDeSoftware, gestaoProjeto, redes, sd, ihc));
		cursoRepository.saveAll(Arrays.asList(si, cc));
		
		CursoDisciplina cd1 = new CursoDisciplina(null, si, laboratorioDeSoftware);
		CursoDisciplina cd2 = new CursoDisciplina(null, si, gestaoProjeto);
		CursoDisciplina cd3 = new CursoDisciplina(null, si, redes);
		CursoDisciplina cd4 = new CursoDisciplina(null, si, sd);
		
		CursoDisciplina cd5 = new CursoDisciplina(null, cc, laboratorioDeSoftware);
		CursoDisciplina cd6 = new CursoDisciplina(null, cc, redes);
		CursoDisciplina cd7 = new CursoDisciplina(null, cc, sd);
		CursoDisciplina cd8 = new CursoDisciplina(null, cc, ihc);
		
		cursoDisciplinaRepository.saveAll(Arrays.asList(cd1, cd2, cd3, cd4, cd5, cd6,cd7, cd8));
		
		turmaRepository.saveAll(Arrays.asList(turma1, turma2));

		Professor prof1 = new Professor(null, "André Costa", "05049493929", "22145675", "andre@gmail.com", "M", true, 5,
				Perfil.ADMIN, BCrypt.hashpw("admin", BCrypt.gensalt()));

		professorRepository.saveAll(Arrays.asList(prof1));

		ProfessorDisciplina pd = new ProfessorDisciplina(null, prof1, laboratorioDeSoftware);
		
		professorDisciplinaRepository.saveAll(Arrays.asList(pd));
		
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

	}
}
