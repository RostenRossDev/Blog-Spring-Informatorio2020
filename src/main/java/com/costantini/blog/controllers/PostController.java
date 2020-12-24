package com.costantini.blog.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.costantini.blog.entities.Post;
import com.costantini.blog.entities.Usuario;
import com.costantini.blog.entities.tda.PostTdo;
import com.costantini.blog.services.PostServiceImp;
import com.costantini.blog.services.UsuarioServiceImp;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
	private Logger log = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostServiceImp postService;
	
	@Autowired
	private UsuarioServiceImp usuarioService;
	
	@PostMapping("/")
	public ResponseEntity<?> altaPost(@RequestBody PostTdo wrapper ){
		
		Usuario autor =usuarioService.getUsuario(wrapper.getUsuario_id()); //obtengo el usuario de la db usando el id del postTdo
		
		Post post = wrapper.getPost(); // Guardo el objeto post que viene de afuera.
		
		post.setAutor(autor); // Al objeto autor le asigno su autor.
		
		if (autor != null) { // si el autor es distinto de nulo
			if (postService.altaPost(post) != null) { // Guardo el post en l db.
				return new ResponseEntity<>("Post creado con exito.", HttpStatus.CREATED);// Si no hay error se retorna msj de exito.
			}
		}
		return new ResponseEntity<>("Error al crear el post.", HttpStatus.OK);// Si hay algun error se emite msj de error.
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> bajaPost(@RequestBody PostTdo postTdo){
		if(postService.bajaPost(postTdo.getPost_id())) {
			return new ResponseEntity<>("Post eliminado con exito.",HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al eliminar el post.", HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<?>actualizarPost(@RequestBody PostTdo postTdo){
		if(postService.actualizarPost(postTdo.getPost_id(), postTdo.getPost()) != null) {
			return new ResponseEntity<>("Post actualizado con exito.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al actualizar el post.", HttpStatus.OK);		
	}
	
	@GetMapping("/todos")
	public ResponseEntity<?> todosLosPost(){
		List<Post> posts = postService.todos();
		if(posts.size()>0) {
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}
		return new ResponseEntity<>("No hay posts.", HttpStatus.OK);
	}
	
	@GetMapping("/{titulo}")
	public ResponseEntity<?> postPorTitulo(@PathVariable String titulo){
		List<Post> posts = postService.buscarPorTitulo(titulo);
		
		if(posts.size()>0) {
			log.info("tamaño : "+posts.size());
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/no_publicados")
	public ResponseEntity<?> postNoPublicados(){
		List<Post> posts = postService.noPublicados();
		if (posts.size()>0) {
			return new ResponseEntity<>(posts, HttpStatus.OK);
		}
		return new ResponseEntity<>("No hay post.",HttpStatus.OK);
	}
}
