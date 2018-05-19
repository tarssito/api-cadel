package br.com.apicadel;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.Curso;
import br.com.apicadel.domain.Disciplina;
import br.com.apicadel.domain.Professor;
import br.com.apicadel.domain.Turma;
import br.com.apicadel.domain.enums.Perfil;
import br.com.apicadel.repositories.AlunoRepository;
import br.com.apicadel.repositories.AulaRepository;
import br.com.apicadel.repositories.CursoRepository;
import br.com.apicadel.repositories.DisciplinaRepository;
import br.com.apicadel.repositories.ProfessorRepository;
import br.com.apicadel.repositories.TurmaRepository;

@SpringBootApplication
public class ApiCadelApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private AulaRepository aulaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiCadelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Curso si = new Curso(null, "Sistema de Informação");
		Curso cc = new Curso(null, "Ciência da Computação");

		Turma turma1 = new Turma(null, "SI01", "2018.1", si);
		Turma turma2 = new Turma(null, "CC01", "2018.1", cc);

		Disciplina laboratorioDeSoftware = new Disciplina(null, "Laboratorio de Software", 60);

		si.getDisciplinas().addAll(Arrays.asList(laboratorioDeSoftware));
		cc.getDisciplinas().addAll(Arrays.asList(laboratorioDeSoftware));
		
		laboratorioDeSoftware.getCursos().addAll(Arrays.asList(si, cc));

		disciplinaRepository.saveAll(Arrays.asList(laboratorioDeSoftware));
		cursoRepository.saveAll(Arrays.asList(si, cc));

		si.getTurmas().addAll(Arrays.asList(turma1));
		cc.getTurmas().addAll(Arrays.asList(turma2));

		turmaRepository.saveAll(Arrays.asList(turma1, turma2));

		Professor prof1 = new Professor(null, "André Costa", "05049493929", "22145675", "andre@gmail.com", "M", true,
				5, Perfil.ADMIN, passwordEncoder.encode("123456"));

		prof1.getDisciplinas().addAll(Arrays.asList(laboratorioDeSoftware));
		laboratorioDeSoftware.getProfessores().addAll(Arrays.asList(prof1));
		prof1.getTurmas().addAll(Arrays.asList(turma1, turma2));
		turma1.getProfessores().addAll(Arrays.asList(prof1));
		turma2.getProfessores().addAll(Arrays.asList(prof1));

		professorRepository.saveAll(Arrays.asList(prof1));

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

		turma1.getAlunos().addAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6));
		turma2.getAlunos().addAll(Arrays.asList(aluno7, aluno8, aluno9, aluno10, aluno11, aluno12));

		aluno1.getTrumas().add(turma1);
		aluno2.getTrumas().add(turma1);
		aluno3.getTrumas().add(turma1);
		aluno4.getTrumas().add(turma1);
		aluno5.getTrumas().add(turma1);
		aluno6.getTrumas().add(turma1);
	
		aluno7.getTrumas().add(turma2);
		aluno8.getTrumas().add(turma2);
		aluno9.getTrumas().add(turma2);
		aluno10.getTrumas().add(turma2);
		aluno11.getTrumas().add(turma2);
		aluno12.getTrumas().add(turma2);
		
		alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8, aluno9,
				aluno10, aluno11, aluno12));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Aula a1 = new Aula(null, sdf.parse("30/03/2018 19:02"), sdf.parse("30/03/2018 19:02"),
				sdf.parse("30/03/2018 20:15"), prof1, laboratorioDeSoftware);

		Aula a2 = new Aula(null, sdf.parse("10/04/2018 19:02"), sdf.parse("30/03/2018 19:02"),
				sdf.parse("30/03/2018 20:15"), prof1, laboratorioDeSoftware);
		
		a1.getAlunos().addAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6));
		a2.getAlunos().addAll(Arrays.asList(aluno7, aluno8, aluno9, aluno10, aluno11, aluno12));
		
		laboratorioDeSoftware.getAulas().addAll(Arrays.asList(a1, a2));
		prof1.getAulas().addAll(Arrays.asList(a1, a2));

		aluno1.getFrequencias().add(a1);
		aluno2.getFrequencias().add(a1);
		aluno3.getFrequencias().add(a1);
		aluno4.getFrequencias().add(a1);
		aluno5.getFrequencias().add(a1);
		aluno6.getFrequencias().add(a1);

		aluno7.getFrequencias().add(a2);
		aluno8.getFrequencias().add(a2);
		aluno9.getFrequencias().add(a2);
		aluno10.getFrequencias().add(a2);
		aluno11.getFrequencias().add(a2);
		aluno12.getFrequencias().add(a2);
		
		aulaRepository.saveAll(Arrays.asList(a1, a2));

	}
}
