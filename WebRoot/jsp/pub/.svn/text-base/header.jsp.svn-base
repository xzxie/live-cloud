<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
	String item = request.getParameter("item");
	if (StringUtils.isBlank(item)) {
		item = "article";
	}
	
	boolean isLogin = session.getAttribute("userinfo")!=null;
%>

<div class="header-container">
	<form action="" name="search-form" id="search-form">
		<input type="hidden" name="item" id="item" value="<%=item %>">
		<div class="header-title" id="header-title"></div>
		<%
			if (isLogin) {
		%>
		<div class="header-post" id="header-post">
			<a href="" target="_self"></a>
		</div>
		<%
			}
		%>
		<div class="header-search">
			<input type="text" name="keyword" id="keyword" placeholder="搜索">
		</div>
	</form>
</div>