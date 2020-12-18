package com.costantini.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comentario implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="fecha_alta")
	private Timestamp fechaAlta;
	@Column(length=200)
	private String comentario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_post")
	private Post post;
	
	@PrePersist
	@JsonFormat(pattern="yyy-MM-dd HH:mm:ss")
	public void prePersist(){
		this.fechaAlta=new Timestamp(new Date().getTime());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public void actualizar(Comentario comentario) {
		if(comentario.getComentario() != null && comentario.getComentario()!= this.comentario) {
			this.comentario=comentario.getComentario();
		}

	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}


	private static final long serialVersionUID = 1L;

}
