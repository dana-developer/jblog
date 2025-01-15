package jblog.controller.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jblog.service.UserService;
import jblog.vo.UserVo;

@RestController("userApiController") // 등록되는 빈 이름 지정
@RequestMapping("/api/user")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/checkBlogId")
	public Object checkId(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		UserVo userVo = userService.getUser(id);
		return Map.of("exist", userVo != null);
	}
}
