package com.costantini.blog.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.costantini.blog.entities.Comentario;
import com.costantini.blog.repositories.ComentarioRepository;
;
@Service
public class ComentarioServiceImp implements IComentarioService{
	
	private Logger log = LoggerFactory.getLogger(ComentarioServiceImp.class);
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Override
	public Comentario obtenerComentario(Long id) {
		
		return comentarioRepository.getOne(id);
	}
	
	@Override
	public Comentario altaComentario(Comentario comentario) {
		// TODO Auto-generated method stub
		return comentarioRepository.save(comentario);
	}

	@Override
	public boolean bajaComentario(Long id) {
		// TODO Auto-generated method stub
		Comentario comentario =comentarioRepository.getOne(id);
		if(comentario != null) {
			comentarioRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Comentario actualizarComentario(Long id, Comentario comentario) {
		// TODO Auto-generated method stub
		Comentario comentarioActualizado=comentarioRepository.getOne(id);
		comentarioActualizado.actualizar(comentario);
		return comentarioRepository.save(comentarioActualizado);
	}

	@Override
	public List<Comentario> buscarComentarios(Long id, Integer cantidad) {
		// TODO Auto-generated method stub
			
			List<Comentario> comentarios = comentarioRepository.comentariosPorPost(id, cantidad);
			log.info("comentarios: "+comentarios.toString());
			 return comentarios;
	
	}
	
	
}
