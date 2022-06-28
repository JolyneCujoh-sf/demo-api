package com.example.demo.api.db.dao;

import java.util.HashMap;

public interface UserDao {
	public long searchUserByOpenId(String openId);
	
	public void register(HashMap param);
	
	public int searchIdByOpenId(String openId);
	
	public long searchUserByTel(String tel);
}
