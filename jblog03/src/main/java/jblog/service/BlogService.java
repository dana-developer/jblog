package jblog.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;

@Service
public class BlogService {
	
	private final BlogRepository blogRepository;
	private final CategoryService categoryService;
	private final PostService postService;
	
	public BlogService(BlogRepository blogRepository, CategoryService categoryService, PostService postService) {
		this.blogRepository = blogRepository;
		this.categoryService = categoryService;
		this.postService = postService;
	}

	public BlogVo getBlog(String id) {
		return blogRepository.findById(id);
	}

	public void updateBlog(BlogVo blogVo) {
		blogRepository.update(blogVo);
	}
	
	public Map<String, Object> getMainContents(String id, Optional<Long> path1, Optional<Long> path2) {
		
		Map<String, Object> result = new HashMap<>();;
		
		Long categoryId = 0L;
		Long postId = 0L;
		
		if(path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if(path1.isPresent()) {
			categoryId = path1.get();
		}
		
		if(categoryId == 0L) {
			categoryId = categoryService.getDefaultCategoryId(id);
		}
		
		if(postId == 0L) {
			postId = postService.getRecentPostId(categoryId);
		}
		
		result.put("blog", getBlog(id));
		result.put("categoryList", categoryService.getCategories(id));
		result.put("postList", postService.getPosts(categoryId));
		result.put("currentCategoryId", categoryId);
		result.put("currentPostId", postId);
		result.put("post", postService.getPost(postId));
		
		return result;
	}

}
