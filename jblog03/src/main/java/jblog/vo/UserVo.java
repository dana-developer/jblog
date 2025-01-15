package jblog.vo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;

public class UserVo {

	@NotEmpty
	@Length(min = 1, max = 50)
	private String id;
	
	@NotEmpty
	@Length(min = 1, max = 45)
	private String name;

	@NotEmpty
	@Length(min = 1, max = 16)
	private String password;
	
	@NotEmpty(message = "약관에 동의해야 합니다.")
	private String agreeProv; // 약관 동의 여부

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgreeProv() {
		return agreeProv;
	}

	public void setAgreeProv(String agreeProv) {
		this.agreeProv = agreeProv;
	}

	@Override
	public String toString() {
		return "UserVo [name=" + name + ", id=" + id + ", password=" + password + ", agreeProv=" + agreeProv + "]";
	}

}
