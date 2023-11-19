package com.test.bancom;

import com.test.bancom.model.Usuario;
import com.test.bancom.model.Post;
import com.test.bancom.repository.UsuarioRepository;
import com.test.bancom.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BancomApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostRepository postRepository;

	@Test
	public void createAndRetrieveUsuario() {
		Usuario newUser = new Usuario();
		newUser.setCellphone("1234567890");
		newUser.setName("Test");
		newUser.setLastName("User");
		newUser.setPassword("password");
		Usuario savedUser = usuarioRepository.save(newUser);

		Usuario retrievedUser = usuarioRepository.findById(savedUser.getId()).orElse(null);
		assertThat(retrievedUser).isNotNull();
		assertThat(retrievedUser.getName()).isEqualTo(newUser.getName());
	}

	@Test
	public void createAndRetrievePost() {
		Usuario user = new Usuario();
		user.setCellphone("1234567890");
		user.setName("Test User");
		user.setLastName("Lastname");
		user.setPassword("password");
		Usuario savedUser = usuarioRepository.save(user);

		Post newPost = new Post();
		newPost.setText("Hello, world!");
		newPost.setUsuario(savedUser);
		Post savedPost = postRepository.save(newPost);

		List<Post> posts = postRepository.findByUsuarioId(savedUser.getId());
		assertThat(posts).isNotEmpty();
		assertThat(posts).extracting(Post::getText).contains(newPost.getText());
	}

	@Test
	public void testUsuarioPostRelation() {
		Usuario user = new Usuario();
		user.setCellphone("9876543210");
		user.setName("Another User");
		user.setLastName("Lastname");
		user.setPassword("secure");
		Usuario savedUser = usuarioRepository.save(user);

		Post post1 = new Post();
		post1.setText("Post 1");
		post1.setUsuario(savedUser);
		postRepository.save(post1);

		Post post2 = new Post();
		post2.setText("Post 2");
		post2.setUsuario(savedUser);
		postRepository.save(post2);

		List<Post> posts = postRepository.findByUsuarioId(savedUser.getId());
		assertThat(posts).hasSize(2);
	}
}
