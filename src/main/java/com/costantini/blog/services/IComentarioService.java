package com.costantini.blog.services;

import java.util.List;

import com.costantini.blog.entities.Comentario;

public interface IComentarioService {
	
	public Comentario obtenerComentario(Long id);
	public Comentario altaComentario(Comentario comentario);
	public boolean bajaComentario(Long id);
	public Comentario actualizarComentario (Long id, Comentario comentario);
	public List<Comentario> buscarComentarios(Long id, Integer cantidad);

	
}
