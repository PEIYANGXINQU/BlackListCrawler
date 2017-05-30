<%@page import="com.dn.crawler.blacklist.bean.BlackUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网贷黑名单列表【动脑学院】</title>
<style>
	html,body{
		width:100%;
		height:100%;
		margin:0px;
		padding:20px;
		font-family:Arial "Microsoft Yahei";
	}
	.blacklist-block{
		border:1px solid #666;
		border-radius:8px;
		background-color:#abcdef;
		padding:10px;
		float:left;
		text-align:center;
	}
	.blacklist-block h6{
		margin:10px 0;
		padding:0;
	}
</style>
</head>
<body>
	<%
		List<BlackUser> blackList = (List<BlackUser>)request.getAttribute("blackList");
	    BlackUser user = null;
		for(int i=0;i<blackList.size();i++){
			user = blackList.get(i);
	%>
	<div class="blacklist-block">
		<h6><%=user.getName() %></h6>
		<p class="mobile"><%=user.getMobile() %></p>
		<p class="bank-card"><%=user.getBankCard() %></p>
		<p class="alipay"><%=user.getAlipay() %></p>
		<p class="desc">骗子死全家</p>
		<div class="image-list">
		</div>
	</div>
	<%} %>
	<button></button>
</body>
</html>