<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<%@ page import="com.util.SeoUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录 - <%=SeoUtil.siteName %></title>
	<jsp:include page="/include/meta.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="/static/css/common.css?t=<%=Constant.VERSION %>">
	<link rel="stylesheet" type="text/css" href="/static/css/reg-login.css?t=<%=Constant.VERSION %>">
  </head>
  
  <body>
  	<jsp:include page="/include/header.jsp"></jsp:include>
  	
	<div class="container login-container">
		
	</div>
	<div id="particles"></div>
	
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script type="text/javascript" src="/static/js/particles.min.js"></script>
  	<script type="text/javascript" src="/static/js/particle-init.js"></script>
	<script type="text/javascript">
		
	</script>
  </body>
</html>