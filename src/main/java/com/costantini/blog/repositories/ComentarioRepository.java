package com.costantini.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.costantini.blog.entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	
	@Query(value="SELECT * from comentario WHERE id_post =? ORDER BY fecha_alta DESC LIMIT ?", nativeQuery=true)
	public List<Comentario> comentariosPorPost(Long post, Integer cantidad);
	
}
