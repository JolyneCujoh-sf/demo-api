package com.example.demo.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.dev33.satoken.stp.StpInterface;

@Component
public class StpInterfaceImpl implements StpInterface {

	@Override
	public List<String> getPermissionList(Object loginId, String loginType) {
		ArrayList list=new ArrayList<>();
		return list;
	}

	@Override
	public List<String> getRoleList(Object loginId, String loginType) {
		ArrayList list=new ArrayList<>();
		return list;
	}

}