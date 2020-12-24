package com.costantini.blog.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.costantini.blog.entities.Usuario;
import com.costantini.blog.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	
	public void altaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		 usuarioRepository.save(usuario);		
	}

	@Override
	public void bajaUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
			usuarioRepository.deleteById(idUsuario);
	}

	@Override
	public void actualizarUsuario(Long idUsuario, Usuario usuario) {
		// TODO Auto-generated method stub
		Usuario usuarioActualizado;
		usuarioActualizado=usuarioRepository.getOne(idUsuario);
		usuarioActualizado.actualizar(usuario);
		usuarioRepository.save(usuarioActualizado);
		
	}

	@Override
	public List<Usuario> todosLosUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public List<Usuario> usuariosDeResistencia() {
		// TODO Auto-generated method stub
		String ciudad = "resistencia";
		return usuarioRepository.findByCiudad(ciudad);
	}

	@Override
	public List<Usuario> usuariosPorFecha(Timestamp altaTimeStart, Timestamp altaTimeEnd) {
		// TODO -generated method stub
		return usuarioRepository.findByFechaAltaBetween(altaTimeStart, altaTimeEnd);
	}

	
	public Usuario getUsuario(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.getOne(id);
	}

}
