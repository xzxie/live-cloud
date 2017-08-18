<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<%@ page import="com.util.SeoUtil"%>
<%
	boolean isHttp = "http".equals(session.getAttribute("scheme")+"") ? true : false;
	boolean isHttps = !isHttp;
	boolean isLogin = session.getAttribute("userinfo")!=null;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>开发者社区 - <%=SeoUtil.siteName %></title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/static/css/pub.css?t=<%=Constant.VERSION %>">
</head>
  
<body>
	<div class="pub-container">
		<jsp:include page="/jsp/pub/navi.jsp"></jsp:include>
		<div class="main-container">
			<jsp:include page="/jsp/pub/header.jsp">
				<jsp:param value="article" name="item"/>
			</jsp:include>
			<div class="list-container">
				<div class="list" id="post-1" data-id="1">
					<div class="box">
						<div class="post-title">Vuex框架原理与源码分析</div>
						<div class="post-meta">
							<span class="post-meta-author">古龙</span>
							<span class="post-meta-time">2017-04-27</span>
						</div>
						<div class="post-abstract">Vuex是一个为Vue提供页面状态管理服务的生态系统。本文从使用Vuex框架所引起的疑问入手，以核心流程-文件目录-源码核心的顺序进行分析，并引入实例...</div>
						<div class="post-tags">
							<span><a class="tag">Vuex</a></span>
							<span><a class="tag">Vue</a></span>
							<span><a class="tag">前端</a></span>
							<span><a class="tag">源码分析</a></span>
						</div>
					</div>
				</div>
				<div class="list"></div>
				<div class="list"></div>
				<div class="list"></div>
				<div class="list"></div>
				<div class="list"></div>
				<div class="list"></div>
				<div class="list"></div>
			</div>
		</div>
	</div>
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script type="text/javascript" src="/static/js/pub.js?t=<%=Constant.VERSION %>"></script>
  </body>
</html>