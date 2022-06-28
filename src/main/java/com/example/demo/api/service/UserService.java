package com.example.demo.api.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.api.db.dao.UserDao;

import cn.hutool.core.date.DateTime;

@Service
public class UserService {

	@Resource
	private UserDao userDao;

	@Transactional
	public int login(String openId, String nickname, String sex, String tel) {
		//long rows = userDao.searchUserByOpenId(openId);
		long rows = userDao.searchUserByTel(openId);
		if (rows == 0) {
			// 注册账号
			String createTime=new DateTime().toString("yyyy-MM-dd hh:mm:ss");
			HashMap param=new HashMap<>() {{
				put("openId", openId);
				put("nickname", nickname);
				put("sex", sex);
				put("tel", tel);
				put("createTime", createTime);
			}};
			
			userDao.register(param);
			
		}
		
		int id=userDao.searchIdByOpenId(openId);
		return id;
	}
}
