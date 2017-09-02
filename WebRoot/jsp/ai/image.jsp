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
<title>图像识别 - AI - <%=SeoUtil.siteName %></title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/static/css/ai.css?t=<%=Constant.VERSION %>">
</head>
  
<body>
  	<jsp:include page="/include/header.jsp"></jsp:include>
  	
	<div class="ai-container">
		<div class="section section-card-image">
			<div class="introduce">
				<div class="title">图像识别</div>
				<div class="description">基于深度学习及大规模图像训练，准确识别图片中的物体类别、位置、置信度等综合信息</div>
				<div>
					<span><a href="">立即使用</a></span>
					<span><a href="">技术文档</a></span>
				</div>
			</div>
		</div>
		<div class="section section-tip"></div>
		<div class="section section-content"></div>
		<div class="section section-content"></div>
		<div class="section section-content"></div>
		<div class="section section-content"></div>
	</div>
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
  </body>
  <script type="text/javascript">
  $(document).ready(function() {
  	$(".introduce").css({"margin-left": (_config.bodyWidth-$(".introduce").width())/2});
  });
  </script>
</html>