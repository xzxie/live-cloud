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
<title>注册 - <%=SeoUtil.siteName %></title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="/static/css/common.css?t=<%=Constant.VERSION %>">
<link rel="stylesheet" type="text/css" href="/static/css/reg-login.css?t=<%=Constant.VERSION %>">
</head>
  
<body>
  	<jsp:include page="/include/header.jsp"></jsp:include>
  	
	<div class="container reg-container">
		<div class="reg-login-bg"></div>
		<div id="reg-container-box">
			<div id="container-reg">
				<form action="/user/login.do" name="form" id="form" method="post">
					<input id="token" type="hidden"/>
					<input id="checkAddress" type="hidden"/>
					<input id="sid" type="hidden"/>
					<div class="input-box">
						<img src="<%=Constant.STATIC_PREFIX %>/static/image/user/user-control.png" class="icon">
						<input type="text" name="username" id="username" autocomplete="off" class="input radius2" placeholder="手机号/邮箱">
					</div>
					<div class="input-box" style="display: block;">
						<img src="<%=Constant.STATIC_PREFIX %>/static/image/user/user-control.png" class="icon">
						<input type="text" name="phone-code" id="phone-code" autocomplete="off" class="input radius2" placeholder="手机验证码">
					</div>
					<div class="input-box">
						<img src="<%=Constant.STATIC_PREFIX %>/static/image/user/pwd-control.png" class="icon">
						<input type="password" name="password" id="password" autocomplete="off" class="input radius2" placeholder="密码">
					</div>
					<div class="input-box">
						<img src="<%=Constant.STATIC_PREFIX %>/static/image/user/pwd-control.png" class="icon">
						<input type="password" name="repeat-password" id="repeat-password" autocomplete="off" class="input radius2" placeholder="再次输入密码">
					</div>
					<div style="margin: 17px 20px;">
						<div id="captcha-target"></div>
					</div>
					<div class="input-box">
						<input type="button" id="login-btn" class="radius2" onclick="reg_ajax()" value="注 册">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer font12">
		粤ICP备15069292号@2017 云烟阁科技  All Right Reserved.
	</div>
	<div id="particles"></div>
	
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/particles.min.js?t=<%=Constant.VERSION %>"></script>
  	<script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/particle-init.js?t=<%=Constant.VERSION %>"></script>
  	<%if (isHttp && !isLogin){ %>
	<script src="https://js.touclick.com/js.touclick?b=<%=Constant.touclick_http_pubKey %>" ></script>
	<script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/TouClick.js?t=<%=Constant.VERSION %>"></script>
	<%} else if (isHttps && !isLogin) { %>
	<script src="https://js.touclick.com/js.touclick?b=<%=Constant.touclick_http_pubKey %>" ></script>
	<script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/TouClick.js?t=<%=Constant.VERSION %>"></script>
	<%} %>
	<script type="text/javascript">
		
	</script>
  </body>
</html>