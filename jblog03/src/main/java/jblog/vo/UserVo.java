package jblog.vo;

public class UserVo {
	private String name;
	private String id;
	private String password;
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
