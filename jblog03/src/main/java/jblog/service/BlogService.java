package jblog.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.PostVo;

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
	
	public Map<String, Object> getMainContents(String blogId, Optional<Long> path1, Optional<Long> path2) {
		
		Map<String, Object> result = new HashMap<>();
		
//		Long categoryId = 0L;
//		Long postId = 0L;
//		
//		if(path2.isPresent()) {	// categoryId와 postId를 모두 입력한 경우 (/jblog/hong/1/2)
//			categoryId = path1.get();
//			postId = path2.get();
//		} else if(path1.isPresent()) { // categoryId만 입력한 경우 (/jblog/hong/1)
//			categoryId = path1.get();
//			postId = postService.getRecentPostId(categoryId);
//		} else { // 모두 입력되지 않은 경우 (/jblog/hong)
//			categoryId = categoryService.getDefaultCategoryId(blogId);
//			postId = postService.getRecentPostId(categoryId);
//		}
//		
		Long categoryId = path1.orElseGet(() -> categoryService.getDefaultCategoryId(blogId));
	    Long postId = path2.orElseGet(() -> postService.getRecentPostId(categoryId));
		
		// 해당 카테고리가 blogId에 속하지 않는 경우
		if(categoryService.getCategoryByIdAndBlogId(categoryId, blogId) == null) {
			return null;
		}
		
		if(postId != null) {
			PostVo postvo = postService.getPost(categoryId, postId);
			
			// 해당 포스트가 카테고리에 속하지 않은 경우
			if(postvo == null) {
				return null;
			}
			result.put("post", postvo);
		}
		
		result.put("blog", getBlog(blogId));
		result.put("categoryList", categoryService.getCategories(blogId));
		result.put("postList", postService.getPosts(categoryId));
		result.put("currentCategoryId", categoryId);
		result.put("currentPostId", postId);
		
		return result;
	}

}
