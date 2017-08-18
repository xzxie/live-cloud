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
<title>最新提问 - 开发者社区 - <%=SeoUtil.siteName %></title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/static/css/pub.css?t=<%=Constant.VERSION %>">
</head>
  
<body>
	<div class="pub-container">
		<jsp:include page="/jsp/pub/navi.jsp"></jsp:include>
		<div class="main-container">
			<jsp:include page="/jsp/pub/header.jsp">
				<jsp:param value="question" name="item"/>
			</jsp:include>
			<div class="list-container"></div>
		</div>
	</div>
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script type="text/javascript" src="/static/js/pub.js?t=<%=Constant.VERSION %>"></script>
  </body>
</html>