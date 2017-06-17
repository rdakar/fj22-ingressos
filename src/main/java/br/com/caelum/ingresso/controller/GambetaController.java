package br.com.caelum.ingresso.controller;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.ingresso.model.Permissao;
import br.com.caelum.ingresso.model.Usuario;

@RestController
public class GambetaController {

    @PersistenceContext
    private EntityManager manager;	
	
    @Transactional
	@GetMapping("/gambeta")
	public String criaUsuario() {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		Permissao permissao = new Permissao("ROLE_ADMIN");
		manager.persist(permissao);
		
		Set<Permissao> permissoes = new HashSet<>();
		permissoes.add(permissao);
	
		Usuario usuario = new Usuario("ninja@ninja.com", bcrypt.encode("ninja"), permissoes);
		
		manager.persist(usuario);
		
		return usuario.getEmail();
	}
}
