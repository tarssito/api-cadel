package br.com.apicadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apicadel.domain.Professor;
import br.com.apicadel.repositories.ProfessorRepository;
import br.com.apicadel.security.UserSS;

@Service
public class UserDatailsServiceImpl implements UserDetailsService {

	@Autowired
	private ProfessorRepository professorRepository;

	@Override
	public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
		Professor professor = professorRepository.findByMatricula(matricula);
		if (professor == null) {
			throw new UsernameNotFoundException(matricula);
		}
		return new UserSS(professor.getId(), professor.getMatricula(), professor.getSenha(), professor.getPerfis());
	}

}
