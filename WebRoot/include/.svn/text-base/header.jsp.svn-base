<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.SeoUtil" %>
<%@ page import="com.util.Constant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="header-navi">
	<div class="header-logo">
		<a href="/">
			<div class="logo"><img alt="logo" src="<%=Constant.STATIC_PREFIX %>/static/image/logo.png"></div>
			<div class="title font17">
				<!-- <span class="bold"><%=SeoUtil.siteName %></span> -->
				<span class="color-ccc"> | </span>
				<span class="font13">For live broadcast</span>
			</div>
		</a>
	</div>
	
	<div class="header-navi-detail">
		<c:if test="${sessionScope.userinfo == null}">
		<div class="menu font16" id="main-bar">首页</div>
		<div class="menu" id="product-bar" data-login="false">产品展示</div>
		<div class="menu" id="cdn-bar" data-login="false">CDN加速</div>
		<div class="menu" id="package-bar" data-login="false">套餐服务</div>
		<div class="menu" id="client-bar" data-login="false">客户端</div>
		</c:if>
		<c:if test="${sessionScope.userinfo != null}">
		<div class="menu font16" id="main-bar">首页</div>
		<div class="menu" id="product-bar" data-login="true">产品展示</div>
		<div class="menu" id="cdn-bar" data-login="true">CDN加速</div>
		<div class="menu" id="package-bar" data-login="true">套餐服务</div>
		<div class="menu" id="client-bar" data-login="true">客户端</div>
		</c:if>
		<div class="menu-more solution-menu-more" id="solution-bar">
			<a href="/solution/index.do">解决方案<i class="triangle"></i></a>
			<div class="solution-drop-box">
				<div class="item"><a href="/solution/index.do">娱乐解决方案</a></div>
				<div class="item"><a href="/solution/index.do">教育解决方案</a></div>
				<div class="item"><a href="/solution/edu.do">电商解决方案</a></div>
				<div class="item"><a href="/solution/index.do">企业解决方案</a></div>
				<div class="item"><a href="/solution/index.do">政务解决方案</a></div>
				<div class="item"><a href="/solution/index.do">游戏解决方案</a></div>
			</div>
		</div>
		<div class="menu-more ai-menu-more" id="ai-bar">
			<a href="/ai/index.do">AI<i class="triangle"></i></a>
			<div class="ai-drop-box">
				<div class="item"><a href="/ai/text.do">文字识别</a></div>
				<div class="item"><a href="/ai/image.do">图像识别</a></div>
				<div class="item"><a href="/ai/face.do">人脸识别</a></div>
				<div class="item"><a href="/ai/voice.do">语音识别</a></div>
				<div class="item"><a href="/ai/recommend.do">推荐引擎</a></div>
				<div class="item"><a href="/ai/machine.do">机器学习</a></div>
			</div>
		</div>
	</div>
	
	<div class="header-login-reg">
		<div class="developer drop-box bold">
			开发者
			<div class="more">
				<span class="drop-down"></span>
				<ul>
					<li class="border-bottom"><a href="/api/doc.do">开发指南</a></li>
					<li class="border-bottom"><a href="/api/index.do">API文档</a></li>
					<li class="border-bottom"><a href="/sdk/index.do">SDK下载</a></li>
					<li><a href="/pub/index.do">开发者社区</a></li>
				</ul>
			</div>
		</div>
		<div class="reg-login-user-box">
		<c:if test="${sessionScope.userinfo == null}">
			<a href="/user/reg.do" class="reg">注册</a>
			<a href="/user/login.do" class="login">登录</a>
		</c:if>
		<c:if test="${sessionScope.userinfo != null}">
			<div class="userinfo drop-box bold">
				<a href="/user/home.do" class="user">${fn:substring(sessionScope.userinfo.nikename, 0, 7) }..</a>
				<div class="more" style="height: 280px;">
					<span class="drop-down"></span>
					<ul>
						<li class="border-bottom"><a href="/user/index.do">个人中心</a></li>
						<li class="border-bottom"><a href="/user/api.do">开放API</a></li>
						<li class="border-bottom"><a href="/user/data.do">数据中心</a></li>
						<li class="border-bottom"><a href="/user/website.do">网站管理</a></li>
						<li class="border-bottom"><a href="/admin/index.do">系统管理</a></li>
						<li><a href="/user/logout.do">退出</a></li>
					</ul>
				</div>
			</div>
		</c:if>
		</div>
	</div>
</div>
