package com.costantini.blog.services;

import java.util.List;

import com.costantini.blog.entities.Post;

public interface IPostService {

	public Post obtenerPost(Long id);
	public Post altaPost(Post post);
	public boolean bajaPost(Long id);
	public Post actualizarPost(Long id, Post post);
	public List<Post> todos();
	public List<Post> buscarPorTitulo(String titulo);
	public List<Post> noPublicados();
	
}
