package com.example.demo.api.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.config.R;
import com.example.demo.api.controller.form.LoginForm;
import com.example.demo.api.service.UserService;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@PostMapping("/login")
	public R login(@RequestBody @Valid LoginForm form) {
		String code = form.getCode();

		String url = "https://api.weixin.qq.com/sns/jscode2session";
		HashMap param = new HashMap<>();
		param.put("appid", "wx1b3fd73d006704e0");
		param.put("secret", "a5c501d75a5fa6da326308ba9e829dd4");
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");

		String response = HttpUtil.post(url, param);
		JSONObject json = JSONUtil.parseObj(response);
		String openId = json.getStr("openid");
		if (openId != null && openId.length() > 0) {
			int id=userService.login(openId, form.getNickname(), form.getSex(), form.getTel());
			//int id=userService.login(openId, form.getNickname(), form.getSex(), form.getTel());
			StpUtil.login(id);
			String token = StpUtil.getTokenInfo().getTokenValue();
			R r = new R();
			r.put("token", token);
			return r;
		}
		else {
			throw new RuntimeException("openId为空");
		}

	}

}
