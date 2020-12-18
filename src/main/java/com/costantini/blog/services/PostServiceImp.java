package com.costantini.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.costantini.blog.entities.Post;
import com.costantini.blog.repositories.PostRepository;

@Service
public class PostServiceImp implements IPostService{
	
	@Autowired
	public PostRepository postRepository;
	
	@Override
	public Post altaPost(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}

	@Override
	public boolean bajaPost(Long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.getOne(id);
		if(post != null) {
			postRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Post actualizarPost(Long id, Post post) {
		// TODO Auto-generated method stub
		Post postActualizado= postRepository.getOne(id);
		postActualizado.actualizar(post);
		return postRepository.save(postActualizado);
	}

	@Override
	public List<Post> todos() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public List<Post> buscarPorTitulo(String titulo) {
		// TODO Auto-generated method stub
		return postRepository.findByTituloIgnoreCaseContaining(titulo);
	}

	@Override
	public List<Post> noPublicados() {
		// TODO Auto-generated method stub
		return postRepository.findByPublicadoFalse();
	}

	@Override
	public Post obtenerPost(Long id) {
		// TODO Auto-generated method stub
		return postRepository.getOne(id);
	}

}
