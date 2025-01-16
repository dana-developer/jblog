package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final PostRepository postRepository;

	public CategoryService(CategoryRepository categoryRepository, PostRepository postRepository) {
		this.categoryRepository = categoryRepository;
		this.postRepository = postRepository;
	}

	public List<CategoryVo> getCategories(String id) {
		return categoryRepository.findAllByBlogId(id);
	}

	public List<CategoryVo> getCategoriesAndPostCnt(String id) {
		return categoryRepository.findAllAndPostCntByBlogId(id);
	}

	@Transactional
	public void delete(Long categoryId) {
		postRepository.deleteByCategoryId(categoryId);
		categoryRepository.delete(categoryId);
	}

	public void insert(String blogId, String name, String description) {
		CategoryVo vo = new CategoryVo();
		vo.setBlogId(blogId);
		vo.setName(name);
		vo.setDescription(description);
		
		categoryRepository.insert(vo);
	}

	public Long getDefaultCategoryId(String id) {
		return categoryRepository.findDefaultCategoryId(id);
	}
	
	public CategoryVo getCategoryByIdAndBlogId(Long categoryId, String blogId) {
		return categoryRepository.findByIdAndBlogId(categoryId, blogId);
	}
}
