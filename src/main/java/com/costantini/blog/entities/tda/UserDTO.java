package com.costantini.blog.entities.tda;

import com.costantini.blog.entities.Usuario;

public class UserDTO {
	private Long id;
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
