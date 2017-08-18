<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<%@ page import="com.util.SeoUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><%=SeoUtil.siteName %> - 为直播而生</title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=Constant.STATIC_PREFIX %>/static/css/mobile.css?t=<%=Constant.VERSION %>">
</head>
  
  <body>
    <div>
    	<div class="intro-img">
    		<img src="<%=Constant.STATIC_PREFIX %>/static/image/m/banner.jpg">
    	</div>
    	<div class="intro-text">
    		<b><span class="font64 color2"><%=SeoUtil.siteName %></span></b><br />
    		<span class="font45 color3">泛娱乐直播平台解决方案，为直播而生。</span><hr/>
    	</div>
    	<div class="qrcode">
    		<img src="<%=Constant.STATIC_PREFIX %>/static/image/qrcode.png">
    	</div>
    </div>
    
    <jsp:include page="/include/commonjs.jsp"></jsp:include>
  </body>
</html>
