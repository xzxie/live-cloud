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
<title>推荐系统 - AI - <%=SeoUtil.siteName %></title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/static/css/ai.css?t=<%=Constant.VERSION %>">
</head>
  
<body>
  	<jsp:include page="/include/header.jsp"></jsp:include>
  	
	<div class="ai-container">
		<div class="section section-card-recommend">
			<div class="introduce">
				<div class="title">推荐引擎</div>
				<div class="description">利用基于内容、基于用户行为、基于社交关系网络等多种方法，实时为用户推荐其喜欢的商品或内容</div>
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