<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<%@ page import="com.util.SeoUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>帮助 - 关于我们 - <%=SeoUtil.siteName %></title>
	<jsp:include page="/include/meta.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="/static/css/common.css?t=<%=Constant.VERSION %>">
	<link rel="stylesheet" type="text/css" href="/static/css/page.css?t=<%=Constant.VERSION %>">
  </head>
  
  <body>
  	<jsp:include page="/include/header.jsp"></jsp:include>
  	
  	<div class="container-help-search">
  		<form action="/help/search.do" method="get" name="help-form" id="help-form">
  			<div class="search-box">
  				<input type="text" name="key" value="${key }" class="search-inp" placeholder="我们能帮你找到什么？在这查找答案" autocomplete="off" maxlength="255">
  				<a href="javascript:void(0);" onclick="help.index.search()" class="search-btn">搜索</a>
  			</div>
  		</form>
  	</div>
	<div class="container-help">
		<div class="navi">
			<jsp:include page="/jsp/help/navi.jsp">
				<jsp:param value="1" name="cur"/>
			</jsp:include>
		</div>
		<div class="content"></div>
	</div>
	
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script src="<%=Constant.STATIC_PREFIX %>/static/js/help.js?t=<%=Constant.VERSION %>" type="text/javascript"></script>
  </body>
</html>