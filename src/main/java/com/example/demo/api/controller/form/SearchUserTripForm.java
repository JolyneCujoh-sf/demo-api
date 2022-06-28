package com.example.demo.api.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SearchUserTripForm {
	@NotBlank(message = "tel不能为空")
	@Pattern(regexp = "^1[1-9][0-9]{9}$",message = "电话不正确")
	private String tel;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	

}
