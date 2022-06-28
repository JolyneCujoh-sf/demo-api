package com.example.demo.api.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import com.example.demo.api.db.dao.CityDao;
import com.example.demo.api.db.dao.TripDao;

import cn.hutool.core.map.MapUtil;

@Service
public class TripService {
	@Resource
	private TripDao tripDao;
	
	@Resource
	private CityDao cityDao;
	
	public String searchUserTrip(HashMap param) throws Exception {
		ArrayList<HashMap> list = tripDao.searchUserTrip(param);
		String result = "低风险";
		f1:for(HashMap map : list) {
			String city=MapUtil.getStr(map,"city");
			String district=MapUtil.getStr(map,"district");
			String code=cityDao.searchCodeByCity(city).toString();
			//查询本地宝网站
			String url = "http://m." + code + ".bendibao.com/news/yqdengji/?qu=" + district;
			Document document = Jsoup.connect(url).get();
			Elements elements = document.getElementsByClass("list-content");
			String temp="低风险";
			f2:for(Element element : elements) {
				temp = element.select("p:last-child").text();
				if(temp.equals("高风险")) {
					result="高风险";
					break f1;
				}
				else if(temp.equals("中风险")) {
					result="中风险";
				}
			}
		}
		return result;
	}
}