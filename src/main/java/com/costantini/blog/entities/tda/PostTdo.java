package com.costantini.blog.entities.tda;

import com.costantini.blog.entities.Post;

public class PostTdo {
	private Post post;
	private Long usuario_id;

	
	public PostTdo() {
		
	}
	public Post getPost() {
	       return post;
	}
	public void setPost(Post post) {
	       this.post = post;
	}
	public Long getUsuario_id() {
	       return usuario_id;
	}
	public void SetUsuario_id(Long usuario_id) {
	       this.usuario_id = usuario_id;
	}
}
