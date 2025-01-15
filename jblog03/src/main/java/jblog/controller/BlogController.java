package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jblog.security.Auth;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.FileUploadService;
import jblog.service.PostService;
import jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	private final BlogService blogService;
	private final CategoryService categoryService;
	private final FileUploadService fileUploadService;
	private final PostService postService;

	public BlogController(BlogService blogService, CategoryService categoryService, 
			FileUploadService fileUploadService, PostService postService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.fileUploadService = fileUploadService;
		this.postService = postService;
	}

	@GetMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String main(@PathVariable("id") String id,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2,
			 Model model) {
		model.addAllAttributes(blogService.getMainContents(id, path1, path2));
		return "blog/main";
	}
	
	@Auth
	@GetMapping("/admin")
	public String admin(@PathVariable("id") String id, Model model) {
		model.addAttribute("blog", blogService.getBlog(id));
		return "blog/admin-default";
	}
	
	@Auth
	@PostMapping("/admin/update")
	public String update(@PathVariable("id") String id,
			@RequestParam("title") String title, 
			@RequestParam("logo-file") MultipartFile file) {
		
		BlogVo blogVo = new BlogVo();

		blogVo.setBlogId(id);
		blogVo.setTitle(title);
		blogVo.setProfile(Optional.ofNullable(fileUploadService.restore(file)).orElse(""));

		blogService.updateBlog(blogVo);

		return "redirect:/"+ id +"/admin";
	}
	
	@Auth
	@GetMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("categoryList", categoryService.getCategoriesAndPostCnt(id));
		return "blog/admin-category";
	}
	
	@Auth
	@GetMapping("/admin/category/delete")
	public String adminCategoryDelete(@PathVariable("id") String id, 
			@RequestParam("categoryId") Long categoryId, 
			Model model) {
		categoryService.delete(categoryId);
		return "redirect:/"+ id +"/admin/category";
	}
	
	@Auth
	@PostMapping("/admin/category")
	public String adminCategoryAdd(
			@PathVariable("id") String id, 
			@RequestParam("name") String name, 
			@RequestParam("desc") String description) {
		categoryService.insert(id, name, description);
		return "redirect:/"+ id +"/admin/category";
	}
	
	@Auth
	@GetMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, Model model) {
		model.addAttribute("blog", blogService.getBlog(id));
		model.addAttribute("categoryList", categoryService.getCategories(id));
		return "blog/admin-write";
	}
	
	@Auth
	@PostMapping("/admin/write")
	public String adminWriteAdd(@PathVariable("id") String id, 
			@RequestParam("category") Long categoryId, 
			@RequestParam("title") String title, 
			@RequestParam("content") String content) {
		postService.insert(categoryId, title, content);
		return "redirect:/"+ id +"/admin/write";
	}
}
