package com.generatiom.blogpessoal.repository;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.generatiom.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		usuarioRepository.deleteAll(); // 0L - começa na posição 0 (como se fosse autoincrement) L de long

		usuarioRepository.save(new Usuario(0L, "João da Silva", "https://i.imgur.com/h4t8loa.jpg%22 ",
				"joao@email.com.br", "13465278"));

		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "https://i.imgur.com/h4t8loa.jpg%22",
				"manuela@email.com.br", "13465278"));

		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "https://i.imgur.com/h4t8loa.jpg%22",
				"adriana@email.com.br", "13465278"));

		usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "https://i.imgur.com/h4t8loa.jpg%22",
				"paulo@email.com.br", "13465278"));
	}

	@Test //anotação de teste 
    @DisplayName("Retornar 1 usuário") // dar o nome para o metodo 
    public void deveRetornarUmUsuario() {

        Optional <Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
        assertTrue(usuario.get().getUsuario().equals("joao@email.com"));
	}
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
	}
}