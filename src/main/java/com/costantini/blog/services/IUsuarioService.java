package com.costantini.blog.services;

import java.sql.Timestamp;
import java.util.List;

import com.costantini.blog.entities.Usuario;

public interface IUsuarioService {
	
	public void altaUsuario(Usuario usuario);
	
	public void bajaUsuario(Long idUsuario);
	
	public void actualizarUsuario(Long id, Usuario usuario);
	
	public List<Usuario> todosLosUsuarios();
	
	public List<Usuario> usuariosDeResistencia();
	
	public List<Usuario> usuariosPorFecha(Timestamp altaTimeStart, Timestamp altaTimeEnd);
	
	public Usuario getUsuario(Long id);
}
