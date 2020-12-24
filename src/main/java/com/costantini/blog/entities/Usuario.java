package com.costantini.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usuario implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	@NotEmpty
	private String nombre;
	
	@Column(nullable = false)
	@NotBlank
	@NotEmpty
	private String apellido;
	
	@Column(unique=true, nullable = false)
	@NotEmpty
	@Email
	private String email;
	
	@Column(nullable = false)
	@NotBlank
	@NotEmpty
	private String password;
	
	@Column(name="fehca_alta")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp fechaAlta;
	
	@Column(nullable = false)
	@NotBlank
	@NotEmpty
	private String ciudad;
	
	@Column(nullable = false)
	@NotBlank
	@NotEmpty
	private String provincia;
	
	@Column(nullable = false)
	@NotBlank
	@NotEmpty
	private String pais;
	
	@OneToMany(
			mappedBy="autor",fetch=FetchType.LAZY, cascade=CascadeType.ALL
			)
	//@JsonManagedReference
	private List<Post> posts;
	
	@OneToMany(mappedBy="usuario",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//@JsonManagedReference
	private List<Comentario> comentarios;
	
	
	
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public void actualizar(Usuario usuario) {
		if(usuario.getApellido() != null && usuario.getApellido() != this.apellido) {
			this.apellido=usuario.getApellido();
		}
		if(usuario.getNombre() != null && usuario.getNombre() != this.nombre) {
			this.nombre=usuario.getNombre();
		}
		if(usuario.getCiudad() != null && usuario.getCiudad() != this.ciudad) {
			this.ciudad=usuario.getCiudad();
		}
		if(usuario.getProvincia() != null && usuario.getProvincia() != this.provincia) {
			this.provincia=usuario.getProvincia();
		}
		if(usuario.getPais() != null && usuario.getPais() != this.pais) {
			this.pais=usuario.getPais();
		}
		if(usuario.getEmail() != null && usuario.getEmail() != this.email) {
			this.email=usuario.getEmail();
		}
	}
	
	private static final long serialVersionUID = 1L;

}
