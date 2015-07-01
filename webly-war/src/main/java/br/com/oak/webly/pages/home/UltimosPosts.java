package br.com.oak.webly.pages.home;

import java.io.Serializable;
import java.util.List;

import br.com.oak.webly.core.vo.PostVo;

public class UltimosPosts implements Serializable {

	private static final long serialVersionUID = 3912049165630056064L;

	private final List<PostVo> posts;

	public UltimosPosts(final List<PostVo> posts) {
		this.posts = posts;
	}

	public List<PostVo> getPosts() {
		return posts;
	}
}