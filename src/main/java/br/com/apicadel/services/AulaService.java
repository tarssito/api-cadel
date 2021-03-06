package br.com.apicadel.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Aluno;
import br.com.apicadel.domain.Aula;
import br.com.apicadel.domain.Classe;
import br.com.apicadel.domain.FrequenciaAluno;
import br.com.apicadel.domain.enums.StatusAula;
import br.com.apicadel.repositories.AlunoRepository;
import br.com.apicadel.repositories.AulaRepository;
import br.com.apicadel.repositories.ClasseRepository;
import br.com.apicadel.repositories.FrequenciaAlunoRepository;

@Service
public class AulaService extends GenericServiceImpl<Aula, Long> {
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private FrequenciaAlunoRepository frequenciaAlunoRepository;
	
	@Autowired
	private ClasseRepository classeRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	public AulaService(AulaRepository repository) {
		super(repository);
		this.aulaRepository = repository;
	}

	@Override
	public Aula save(Aula entity) {
		if (entity.getId() != null) {
			List<FrequenciaAluno> frequenciasAlunos = entity.getFrequenciasAlunos();
			for (FrequenciaAluno frequenciaAluno : frequenciasAlunos) {
				FrequenciaAluno frequencia = new FrequenciaAluno();
				frequencia.setAula(entity);
				frequencia.setAluno(frequenciaAluno.getAluno());
				frequencia.setPresenca(frequenciaAluno.getPresenca());
				frequenciaAlunoRepository.save(frequencia);
			}
		}
		return super.save(entity);
	}
	
	public Aula fromInsertAula(Aula aula) {
		Classe classe = classeRepository.findById(aula.getClasse().getId()).get();
		aula.setData(getCurrentDate());
		aula.setClasse(classe);
		aula.setStatus(StatusAula.ABERTA.getCodStatus());
		return aula;
	}
		
	public Aula fromUpdateAula(Aula aula) {
		Classe classe = classeRepository.findById(aula.getClasse().getId()).get();
		List<Aluno> alunos = aula.getAlunos();
		List<FrequenciaAluno> newFrequenciasAluno = new ArrayList<>();
		for (Aluno aluno : alunos) {
			FrequenciaAluno frequencia = new FrequenciaAluno();
			Aluno alunoBd = alunoRepository.findById(aluno.getId()).get();
			frequencia.setAluno(alunoBd);
			frequencia.setPresenca(aluno.getPresenca());
			newFrequenciasAluno.add(frequencia);
		}
		aula.setData(getCurrentDate());
		aula.setClasse(classe);
		aula.setStatus(StatusAula.FECHADA.getCodStatus());
		aula.setFrequenciasAlunos(newFrequenciasAluno);
		return aula;
	}
	
	/**
	 * Método que retorna data atual, formato: Y-m-d HH:mm:ss
	 * 
	 * @return
	 */
	public Date getCurrentDate() {
		LocalDate dataDeInscricao = LocalDate.now();
		Date data = Date.from(dataDeInscricao.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return data;
	}

	public List<Aula> search(String semestre, String ano, Long idCurso, Long idDisciplina, Long idProfessor) {
		return aulaRepository.search(semestre, ano, idCurso, idDisciplina, idProfessor);
	}
	
}
