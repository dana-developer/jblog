package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {

	private final PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public void insert(Long categoryId, String title, String content) {
		PostVo postVo = new PostVo();
		postVo.setContents(content);
		postVo.setTitle(title);
		postVo.setCategoryId(categoryId);
		
		postRepository.insert(postVo);
	}

	public List<PostVo> getPosts(Long categoryId) {
		return postRepository.findAllByCategoryId(categoryId);
	}

	public Long getRecentPostId(Long categoryId) {
		return postRepository.findRecentPostIdByCategoryId(categoryId);
	}

	public PostVo getPost(Long postId) {
		return postRepository.findById(postId);
	}

}
