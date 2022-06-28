package com.example.demo.api.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginForm {
	@NotBlank(message = "code不能为空")
	private String code;
	
	@NotBlank(message = "nickname不能为空")
	private String nickname;
	
	@NotBlank(message = "sex不能为空")
	@Pattern(regexp = "^男$|^女$")
	private String sex;
	
	@NotBlank(message = "tel不能为空")
	@Pattern(regexp = "^1[1-9][0-9]{9}$")
	private String tel;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
