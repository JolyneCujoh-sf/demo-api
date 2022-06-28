package com.example.demo.api.config;

import java.util.HashMap;

public class R extends HashMap<String, Object> {
	public R() {
		put("code", 200);
		put("msg", "success");
	}

	public R(int code, String msg) {
		put("code", code);
		put("msg", msg);
	}

}
