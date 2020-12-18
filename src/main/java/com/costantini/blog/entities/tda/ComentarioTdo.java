package com.costantini.blog.entities.tda;

public class ComentarioTdo {
	private Long autor;
	private Long post;
	private Long comentario;
	private String comentarioString;
	private Integer cantidad;
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Long getAutor() {
		return autor;
	}
	public void setAutor(Long autor) {
		this.autor = autor;
	}
	public Long getPost() {
		return post;
	}
	public void setPost(Long post) {
		this.post = post;
	}
	public Long getComentario() {
		return comentario;
	}
	public void setComentario(Long comentario) {
		this.comentario = comentario;
	}
	public String getComentarioString() {
		return comentarioString;
	}
	public void setComentarioString(String comentarioString) {
		this.comentarioString = comentarioString;
	}
	
	
	
	
}
