package com.costantini.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id")
	private Long id;
	private String titulo;
	private String descripcion;
	private String contenido;
	@Column(name="fecha_alta")
	private Timestamp fechaAlta;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario autor;
	
	private boolean publicado;
		
	@PrePersist
	@JsonFormat(pattern="yyy-MM-dd HH:mm:ss")
	public void prePersist(){
		this.fechaAlta=new Timestamp(new Date().getTime());
	}
	
		
	@OneToMany(
			targetEntity = com.costantini.blog.entities.Comentario.class,
			mappedBy="post",fetch=FetchType.LAZY, cascade=CascadeType.ALL
			)
	//@JsonManagedReference
	private List<Comentario> comentarios;
	
	
	public Post() {
		this.comentarios=new ArrayList<Comentario>();
	}
	
	
	public Post(Long id, String titulo, String descripcion, String contenido, Timestamp fechaAlta, Usuario autor,
			boolean publicado, List<Comentario> comentarios) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
		this.fechaAlta = fechaAlta;
		this.autor = autor;
		this.publicado = publicado;
		this.comentarios = comentarios;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public boolean isPublicado() {
		return publicado;
	}
	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}
	
	public void actualizar(Post post) {
		if(post.getTitulo() != null && post.getTitulo() !=this.titulo) {
			this.titulo = post.getTitulo();
		}
		if(post.getContenido() != null && post.getContenido() != this.contenido) {
			this.contenido= post.getContenido();
		}
		if(post.getDescripcion() != null && post.getDescripcion() != this.descripcion) {
			this.descripcion= post.getDescripcion();
		}
		
	}

	
	@Override
	public String toString() {
		return "Post [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", contenido=" + contenido
				+ ", fechaAlta=" + fechaAlta + ", autor=" + autor + ", publicado=" + publicado + ", comentarios="
				+ comentarios + "]";
	}


	private static final long serialVersionUID = 1L;

}
