package com.dn.crawler.blacklist.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dn.crawler.blacklist.bean.BlackUser;
import com.dn.crawler.blacklist.core.imp.BlackListParser;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class BlackListCrawler {
	
	private BlackListParser parser = new BlackListParser();
	//数据源的URL模板
	public static final String url = "http://www.p2pblack.com/cheat/frontCheatList.html?currentPage=$currentPage&showCount=$showCount";

	private int currentPage = 1;
	
	private int showCount = 20;
	
	public BlackListCrawler(){
		
	}
	
	public BlackListCrawler(int currentPage){
		this.currentPage = currentPage;
	}
	
	public BlackListCrawler(int currentPage,int showCount){
		this.currentPage = currentPage;
		this.showCount = showCount;
	}
	
	public List<BlackUser> next(){
		String content = null;
		//通过httpclient根据url和参数模拟提交请求，返回content代码
		HttpClient client = new HttpClient();
		
		String url = this.url.replace("$currentPage", this.currentPage+"").replace("$showCount", this.showCount+"");
		System.out.println(url);
		HttpMethod method = new GetMethod(url);
		try {
			method.addRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//			method.addRequestHeader("Accept-Encoding","gzip, deflate");
			method.addRequestHeader("Accept-Language","zh-CN,zh;q=0.8");
			method.addRequestHeader("Cache-Control","max-age=0");
			method.addRequestHeader("Connection","keep-alive");
			method.addRequestHeader("Content-Length","0");
			method.addRequestHeader("Content-Type","text/html; charset=UTF-8");
			method.addRequestHeader("Cookie","JSESSIONID=0C1E06CF2ED55A8585717A6813AFDF02; JSESSIONID=1D5A4086BEDCACE81A3F9260956B34CB; _gat=1; Hm_lvt_c22de9653a184612bc894e4ad9e5963b=1476260795,1476328079,1476450663,1476450687; Hm_lpvt_c22de9653a184612bc894e4ad9e5963b=1476453558; Hm_lvt_cd3ba14d1328e4aa733e6cf862aea4fa=1476260795,1476328078,1476450663,1476450687; Hm_lpvt_cd3ba14d1328e4aa733e6cf862aea4fa=1476453559; _ga=GA1.2.908661338.1476260701");
			method.addRequestHeader("Host","www.p2pblack.com");
			method.addRequestHeader("Origin","http://www.p2pblack.com");
			method.addRequestHeader("Referer","http://www.p2pblack.com/cheat/frontCheatList.html?currentPage=1&showCount=100");
			method.addRequestHeader("Upgrade-Insecure-Requests","1");
			method.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2700.0 Safari/537.36");
			
			client.executeMethod(method);
			
			content  = method.getResponseBodyAsString();
			currentPage++;
			//html内容传入
			return parser.parse(content);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}
