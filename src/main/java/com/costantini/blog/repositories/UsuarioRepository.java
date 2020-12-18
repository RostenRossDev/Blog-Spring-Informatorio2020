package com.costantini.blog.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.costantini.blog.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<Usuario> findByCiudad(String ciudad);
	
	public List<Usuario> findByFechaAltaBetween(Date altaTimeStart, Date altaTimeEnd);

}
