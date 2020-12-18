package com.costantini.blog.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


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

import com.costantini.blog.entities.Usuario;
import com.costantini.blog.services.UsuarioServiceImp;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImp usuarioService;
	
	@PostMapping(value="/", consumes={"application/json"})
	public ResponseEntity<?> altaUsuario(@RequestBody Usuario usuario){
		
		try {
			usuarioService.altaUsuario(usuario);
			return new ResponseEntity<>("Usuario creado con exito.",HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al crear usuario.",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/{idUsuario}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable Long idUsuario, @RequestBody Usuario usuario){
		try {
			usuarioService.actualizarUsuario(idUsuario, usuario);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al actualizar el cliente", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Usuario actualizado con exito.", HttpStatus.OK);		
	}
	
	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long idUsuario){
		try {
			usuarioService.bajaUsuario(idUsuario);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Id de usuario inexistente.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Usuario eliminado con exito.", HttpStatus.OK);
	}
	
	@GetMapping("/todos")
	public ResponseEntity<?> todosLosUsuarios(){
		List<Usuario> usuarios = usuarioService.todosLosUsuarios();
		if(usuarios.size()==0) {
			return new ResponseEntity<>("No hay usuarios", HttpStatus.OK);
		}
		usuarios=usuarios.stream()
			.map(elimarClave)
			.collect(Collectors.toList());
		
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/fecha/{fecha}")
	public ResponseEntity<?> usuariosPorFecha(@PathVariable String fecha){
		Timestamp ahora= new Timestamp(new Date().getTime());
		Timestamp fechaParam= Timestamp.valueOf(fecha+" 00:00:00.000000000");
		List<Usuario> usuarios = usuarioService.usuariosPorFecha(fechaParam, ahora);
		if(usuarios.size()>0) {
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}
		return new ResponseEntity<>("No se encontro usuarios.",HttpStatus.OK);
	}
	
	private Function<Usuario, Usuario> elimarClave = usuario -> {
		usuario.setPassword("");
		return usuario;
		
	};
}
