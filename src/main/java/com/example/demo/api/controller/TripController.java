package com.example.demo.api.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.service.TripService;
import com.example.demo.api.config.R;
import com.example.demo.api.controller.form.SearchUserTripForm;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;

@RestController
@RequestMapping("/trip")
public class TripController {
	@Resource
	private TripService tripService;
	
	@PostMapping("/searchUserTrip")
	@SaCheckLogin
	public R searchUserTrip(@RequestBody @Valid SearchUserTripForm form) {
		HashMap param = new HashMap<>();
		param.put("tel", form.getTel());
		DateTime d1 = new DateTime();
		DateTime d2 = d1.offsetNew(DateField.DAY_OF_MONTH, -14);
		param.put("startDate", d2.toDateStr());
		param.put("endDate", d1.toDateStr());
		try {
			String result=tripService.searchUserTrip(param);
			R r = new R();
			r.put("result",result);
			return r;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
