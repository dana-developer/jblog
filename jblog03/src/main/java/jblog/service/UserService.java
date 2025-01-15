package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.UserRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final BlogRepository blogRepository;
	private final CategoryRepository categoryRepository;
	
	public UserService(UserRepository userRepository, BlogRepository blogRepository, CategoryRepository categoryRepository) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@Transactional
	public void join(UserVo vo) {
		userRepository.insert(vo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogId(vo.getId());
		blogVo.setTitle(vo.getName() + "님의 블로그");
		blogVo.setProfile("");
		blogRepository.insert(blogVo);
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(vo.getId());
		categoryVo.setName("미분류/기타");
		categoryRepository.insert(categoryVo);
	}

	public UserVo login(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
}
