package com.dn.crawler.blacklist.core.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dn.crawler.blacklist.bean.BlackUser;
import com.dn.crawler.blacklist.core.BlackListCrawler;
import com.dn.crawler.blacklist.core.Parser;

public class BlackListParser implements Parser<BlackUser> {

	@Override
	public List<BlackUser> parse(String content) {
		List<BlackUser> blackList = new ArrayList<>();
		//正则实现分组检索html content的逻辑
		content = content.replaceAll("\\s", "");
		Pattern pattern1 = Pattern.compile("<divclass=\"ft_publick_pzxxrightft_publick_myjb\"style=\"width:520px;\"><p>骗子姓名：(.+?)</p><p>骗子联系方式：(.+?)</p><p>骗子银行卡号：(.+?)</p><p>骗子支付宝：(.+?)</p></div>");
		Matcher matcher1 = pattern1.matcher(content);
		Pattern pattern2 = Pattern.compile("");
		Pattern pattern3 = Pattern.compile("");
		BlackUser user = null;
		while(matcher1.find()){
			user = new BlackUser();
			user.setName(matcher1.group(1));
			user.setMobile(matcher1.group(2));
			user.setBankCard(matcher1.group(3));
			user.setAlipay(matcher1.group(4));
			blackList.add(user);
		}
		return blackList;
	}
	public static void main(String[] args) {
		new BlackListCrawler().next();
	}
}
