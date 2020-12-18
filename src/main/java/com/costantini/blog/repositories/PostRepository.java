package com.costantini.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.costantini.blog.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	public List<Post> findByTituloIgnoreCaseContaining(String titulo);
	public List<Post> findByPublicadoFalse();
}
