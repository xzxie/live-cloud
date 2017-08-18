<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<%@ page import="com.util.WebUtil"%>
<%@ page import="com.util.SeoUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	boolean isHttp = "http".equals(session.getAttribute("scheme")+"") ? true : false;
	boolean isHttps = !isHttp;
	boolean isLogin = session.getAttribute("userinfo")!=null;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><%=SeoUtil.siteName %> - 为直播而生</title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=Constant.STATIC_PREFIX %>/static/css/index.css?t=<%=Constant.VERSION %>">
</head>

<body>
	<jsp:include page="/include/header.jsp"></jsp:include>
  	
  	<!-- banner -->
	<div id="section-banner" class="lazy">
		<div id="container-welcome">
			<div class="header">Welcome to aliyun Elastic Compute Service!</div>
			<div class="command-line">[root@iZ945afm343Z /]# <span class="input-span" id="input-span-0"></span></div>
		</div>
		<c:if test="${sessionScope.userinfo == null }">
		<div id="container-login">
			<form action="/user/login.do" name="form" id="form" method="post">
				<input id="token" type="hidden"/>
				<input id="checkAddress" type="hidden"/>
				<input id="sid" type="hidden"/>
				<div class="input-box">
					<img src="<%=Constant.STATIC_PREFIX %>/static/image/user/user-control.png" class="icon">
					<input type="text" name="username" id="username" autocomplete="off" class="input radius2" placeholder="手机号/邮箱">
				</div>
				<div class="input-box">
					<img src="<%=Constant.STATIC_PREFIX %>/static/image/user/pwd-control.png" class="icon">
					<input type="password" name="password" id="password" autocomplete="off" class="input radius2" placeholder="密码">
				</div>
				<div style="margin: 17px 20px;">
					<div id="captcha-target"></div>
				</div>
				<div class="input-box">
					<input type="button" id="login-btn" class="radius2" onclick="login_ajax()" value="登 录">
				</div>
				<div class="input-box">
					<div class="socail-desc">
						<hr style="float: left;">
						社交网络登录
						<hr style="float: right;">
					</div>
					<div>
						<a href="/user/login_qq.do">
							<em class="login-qq"></em>
						</a>
						<a href="/user/login_weixin.do">
							<em class="login-weixin"></em>
						</a>
						<a href="/user/login_weibo.do">
							<em class="login-weibo"></em>
						</a>
					</div>
				</div>
			</form>
		</div>
		</c:if>
	</div>
	
	<!-- product -->
	<div class="section" id="product">
		<div class="container-title">
			<hr>
			<span class="title">产品介绍</span>
		</div>
		<div class="container" style="width: 100%;">
			<video id="product-video" src="<%=Constant.STATIC_PREFIX %>/static/asserts/banner1.mp4" autoplay="autoplay" loop="loop"></video>
		</div>
	</div>
	
	<!-- cdn -->
	<div class="section" id="cdn">
		<div class="container-title">
			<hr>
			<span class="title">CDN加速</span>
		</div>
		<div class="container">
			<div class="pulse dot-pulse1"></div>
			<div class="pulse dot-pulse2"></div>
			<div class="pulse dot-pulse3"></div>
			<div class="pulse dot-pulse4"></div>
			<div class="pulse dot-pulse5"></div>
			<div class="pulse dot-pulse6"></div>
		</div>
		<div class="apply-use transition"><a href="JavaScript:void(0);" onclick="apply()">申请试用</a></div>
	</div>
	
	<!-- package -->
	<div class="section" id="package">
		<div class="container-title">
			<hr>
			<span class="title">套餐服务</span>
		</div>
		<div class="container">
			<%-- 套餐1 --%>
			<div class="package-item package-item-active">
				<div class="top">
					<div class="title">
						<div class="version font20">试用版</div>
						<div class="desc">免费试用版本，无栈即可创业</div>
					</div>
				</div>
				<div class="info">
					<div class="service-list">
						<div class="list">服务1</div>
						<div class="list">服务2</div>
						<div class="list">服务3</div>
						<div class="list">服务4</div>
					</div>
				</div>
				<div class="sale">
					<a href="">购买</a>
				</div>
			</div>
			<%-- 套餐2 --%>
			<div class="package-item">
				<div class="top">
					<div class="title">
						<div class="version font20">标准版</div>
						<div class="desc">适用于小型初创企业快速搭建平台</div>
					</div>
				</div>
				<div class="info">
					<div class="service-list">
						<div class="list">服务1</div>
						<div class="list">服务2</div>
						<div class="list">服务3</div>
						<div class="list">服务4</div>
					</div>
				</div>
				<div class="sale">
					<a href="">购买</a>
				</div>
			</div>
			<%-- 套餐3 --%>
			<div class="package-item">
				<div class="top">
					<div class="title">
						<div class="version font20">进阶版</div>
						<div class="desc">适用于小型初创企业快速搭建平台</div>
					</div>
				</div>
				<div class="info">
					<div class="service-list">
						<div class="list">服务1</div>
						<div class="list">服务2</div>
						<div class="list">服务3</div>
						<div class="list">服务4</div>
					</div>
				</div>
				<div class="sale">
					<a href="">购买</a>
				</div>
			</div>
			<%-- 套餐4 --%>
			<div class="package-item">
				<div class="top">
					<div class="title">
						<div class="version font20">旗舰版</div>
						<div class="desc">适用于小型初创企业快速搭建平台</div>
					</div>
				</div>
				<div class="info">
					<div class="service-list">
						<div class="list">服务1</div>
						<div class="list">服务2</div>
						<div class="list">服务3</div>
						<div class="list">服务4</div>
					</div>
				</div>
				<div class="sale">
					<a href="">购买</a>
				</div>
			</div>
		</div>
	</div>
	
	<!-- client -->
	<div class="section" id="client">
		<div class="container-title">
			<hr>
			<span class="title">客户端</span>
		</div>
		<div class="container">
			<div class="client-animal">
				<img alt="" src="<%=Constant.STATIC_PREFIX %>/static/image/online-1.gif">
			</div>
			<div class="client-platform">
				<div class="client-line">
					<div class="client-item client-iphone transition">
						<img alt="" src="<%=Constant.STATIC_PREFIX %>/static/image/client-iphone.png">
					</div>
					<div class="client-item client-ipad transition">
						<img alt="" src="<%=Constant.STATIC_PREFIX %>/static/image/client-ipad.png">
					</div>
				</div>
				<div class="client-line">
					<div class="client-item client-android transition">
						<img alt="" src="<%=Constant.STATIC_PREFIX %>/static/image/client-android.png">
					</div>
					<div class="client-item client-pc transition">
						<img alt="" src="<%=Constant.STATIC_PREFIX %>/static/image/client-pc.png">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer-navi -->
	<div class="section" id="footer-navi">
		<div class="container-title">
			<hr>
			<span class="title">导航</span>
		</div>
		<div class="container">
			<div class="item aboutus">
				<div class="title">关于我们</div>
				<ul>
					<li><a href="/help/index.do?t=1" target="_blank">关于<%=SeoUtil.siteName %></a></li>
					<li><a href="/help/index.do?t=2" target="_blank">开放API</a></li>
					<li><a href="/help/index.do?t=3" target="_blank">产品优势</a></li>
					<li><a href="/help/index.do?t=4" target="_blank">合作机构</a></li>
					<li><a href="/help/index.do?t=5" target="_blank">媒体关注</a></li>
					<li><a href="/help/index.do?t=6" target="_blank">加入我们</a></li>
					<li><a href="/help/index.do?t=7" target="_blank">联系我们</a></li>
				</ul>
			</div>
			<div class="item corp">
				<div class="title">合作共赢</div>
				<ul>
					<li><a href="/help/index.do?t=8" target="_blank">帮助中心</a></li>
					<li><a href="/help/index.do?t=9" target="_blank">常见问题</a></li>
					<li><a href="/help/index.do?t=10" target="_blank">免责申明</a></li>
					<li><a href="/help/index.do?t=11" target="_blank">隐私保护</a></li>
					<li><a href="/help/index.do?t=12" target="_blank">权利申明</a></li>
				</ul>
			</div>
			<div class="item navi">
				<div class="title">网站导航</div>
				<ul>
					<li><a href="/user/login.do" target="_blank">登录/注册</a></li>
					<li><a href="/#product" target="_self">产品展示</a></li>
					<li><a href="/#cdn" target="_self">CDN加速</a></li>
					<li><a href="/#package" target="_self">套餐服务</a></li>
					<li><a href="/#client" target="_self">客户端下载</a></li>
				</ul>
			</div>
			<div class="item beian">
				<div class="title">网站备案</div>
				<ul>
					<li><a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action" target="_blank">粤ICP备15069292号</a></li>
					<li><img width="80px" src="<%=Constant.STATIC_PREFIX %>/static/image/qrcode-site.png"/></li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/layer-v3.0.3/layer.js?t=<%=Constant.VERSION %>"></script>
	<script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/index.js?t=<%=Constant.VERSION %>"></script>
	<%if (isHttp && !isLogin){ %>
	<script src="https://js.touclick.com/js.touclick?b=<%=Constant.touclick_http_pubKey %>" ></script>
	<script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/TouClick.js?t=<%=Constant.VERSION %>"></script>
	<%} else if (isHttps && !isLogin) { %>
	<script src="https://js.touclick.com/js.touclick?b=<%=Constant.touclick_http_pubKey %>" ></script>
	<script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/TouClick.js?t=<%=Constant.VERSION %>"></script>
	<%} %>
</body>
</html>
