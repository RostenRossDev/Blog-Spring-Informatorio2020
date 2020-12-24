package com.costantini.blog.controllers;

import java.util.List;


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

import com.costantini.blog.entities.Comentario;
import com.costantini.blog.entities.Post;
import com.costantini.blog.entities.Usuario;
import com.costantini.blog.entities.tda.ComentarioTdo;
import com.costantini.blog.services.ComentarioServiceImp;
import com.costantini.blog.services.PostServiceImp;
import com.costantini.blog.services.UsuarioServiceImp;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioControler {

	
	@Autowired
	private ComentarioServiceImp comentarioService;
	
	@Autowired
	private UsuarioServiceImp usuarioService;
	
	@Autowired
	private PostServiceImp postService;
	
	@PostMapping("/")
	public ResponseEntity<?> altaComentario(@RequestBody ComentarioTdo comentarioTda){
		Long autor_id=comentarioTda.getAutor();// obtengo el id del autor del TDO.
		Long post_id= comentarioTda.getPost();//obtengo el id del post del TDO.
		Comentario comentario= new Comentario(); //Creo objeto comentario vacio.
		
		comentario.setComentario(comentarioTda.getComentarioString());//Asigno el string del comentario 
		Usuario autor= usuarioService.getUsuario(autor_id);

		Post post= postService.obtenerPost(post_id);
		comentario.setUsuario(autor);
		comentario.setPost(post);
		comentarioService.altaComentario(comentario);
		return new ResponseEntity<>("Comentario guardado con exito.", HttpStatus.CREATED);
			
		
	}
	
	@PutMapping("/")
	public ResponseEntity<?> actualizarComentario(@RequestBody ComentarioTdo comentarioTda){
		Long comentarioId = comentarioTda.getComentario();
		Comentario comentario = comentarioService.obtenerComentario(comentarioId);
		
		if(comentarioTda.getComentarioString() != "" && comentario != null) {
			comentario.setComentario(comentarioTda.getComentarioString());
			if( comentarioService.actualizarComentario(comentarioId, comentario) != null) {
				return new ResponseEntity<>("Comentario actualizado con exito.",HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>("Error al actualizar el comentario.",HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> bajaComentario(@RequestBody ComentarioTdo comentarioTda){
		
		if(comentarioService.bajaComentario(comentarioTda.getComentario())) {
			return new ResponseEntity<>("Se elimino el comentario con exito.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al eliminar comentario.",HttpStatus.OK);
	}
	
	@GetMapping("/post_{id}-cantidad_{cantidad}")
	public ResponseEntity<?> obtenerComentarios(@PathVariable Long id, @PathVariable Integer cantidad){
				

		List<Comentario> comentarios= comentarioService.buscarComentarios(id, cantidad);
	
		return new ResponseEntity<>(comentarios,HttpStatus.OK);
	}
}
