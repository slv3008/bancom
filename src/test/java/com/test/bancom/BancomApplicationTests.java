package com.test.bancom;

import com.test.bancom.model.Usuario;
import com.test.bancom.model.Post;
import com.test.bancom.service.UsuarioService;
import com.test.bancom.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BancomApplicationTests {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PostService postService;

	@Test
	public void contextLoads() {
		// Prueba básica para verificar que el contexto de la aplicación se carga correctamente
		assertThat(usuarioService).isNotNull();
		assertThat(postService).isNotNull();
	}

	@Test
	public void createAndRetrieveUsuario() {
		// Crear un nuevo usuario y luego recuperarlo para verificar que se guardó correctamente
		Usuario newUser = new Usuario();
		newUser.setCellphone("1234567890");
		newUser.setName("Test");
		newUser.setLastName("User");
		newUser.setPassword("password");
		Usuario savedUser = usuarioService.createUsuario(newUser);

		Usuario retrievedUser = usuarioService.getUsuarioById(savedUser.getId()).orElse(null);
		assertThat(retrievedUser).isNotNull();
		assertThat(retrievedUser.getName()).isEqualTo(newUser.getName());
	}

	@Test
	public void createAndRetrievePost() {
		// Crear un nuevo post y luego recuperarlo para verificar que se guardó correctamente
		// Asumimos que ya existe un usuario
		Usuario user = new Usuario();
		// Estableciendo un ID de usuario existente
		user.setId(1L);

		Post newPost = new Post();
		newPost.setText("Hello, world!");
		newPost.setUsuario(user);
		Post savedPost = postService.createPost(newPost);

		List<Post> posts = postService.getPostsByUsuarioId(user.getId());
		assertThat(posts).isNotEmpty();
		assertThat(posts).extracting(Post::getText).contains(newPost.getText());
	}

	@Test
	public void testUsuarioPostRelation() {
		// Prueba para verificar la relación entre Usuario y Post
		// Asumiendo que ya existe un usuario

		Usuario user = new Usuario();
		// Estableciendo un ID de usuario existente
		user.setId(1L);

		Post post1 = new Post();
		post1.setText("Post 1");
		post1.setUsuario(user);
		postService.createPost(post1);

		Post post2 = new Post();
		post2.setText("Post 2");
		post2.setUsuario(user);
		postService.createPost(post2);

		List<Post> posts = postService.getPostsByUsuarioId(user.getId());
		assertThat(posts).hasSize(2);
	}
}
