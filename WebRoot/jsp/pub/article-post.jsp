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
			<!-- 文章详细页 -->
			<form action="/pub/article_post.do" method="post" name="form" id="form">
			<div class="article-container">
				<div class="article">
					<div class="title">
						<input type="hidden" name="operate" value="post">
						<input type="hidden" name="id" value="">
						<input type="text" name="title" id="article-title" placeholder="文章标题"/>
					</div>
					<div class="content">
						<textarea class="xheditor {height:'250'}" name="content" id="article-content" placeholder="文章内容"></textarea>
					</div>
					<div class="tags">
						标签：<input type="text" name="tags" id="tags" class="tags"><br/>
						例如：<span><a class="tag">Vuex</a></span>
						<span><a class="tag">Vue</a></span>
						<span><a class="tag">前端</a></span>
						<span><a class="tag">源码分析</a></span>
					</div>
					<div class="post">
						<input type="button" value="发 布" name="article-sub" id="article-sub">
					</div>
				</div>
			</div>
			</form>
			<!-- //文章详细页 -->
		</div>
	</div>
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script type="text/javascript" src="/static/js/pub.js?t=<%=Constant.VERSION %>"></script>
	<script type="text/javascript" src="/static/js/xheditor-1.2.2/xheditor-1.2.2.min.js"></script>
	<script type="text/javascript" src="/static/js/xheditor-1.2.2/xheditor_lang/zh-cn.js"></script>
	<script type="text/javascript" src="/static/js/layer-v3.0.3/layer.js?t=<%=Constant.VERSION %>"></script>
	<script type="text/javascript">
		$("#article-sub").click(function(){
			var article_title = $("#article-title").val();
			var article_content = $("#article-content").val();
			if (article_title == null || article_title == "") {
				layer.open({
					type: 1,
					area: ['400px', '200px'],
					title: '提示：',
					shade: 0.6,
					maxmin: false,
					anim: 0,
					content: '<div style="padding:50px;">文章标题不能为空.</div>'
				});
				return;
			}
			if (article_content == null || article_content == "") {
				layer.open({
					type: 1,
					area: ['400px', '200px'],
					title: '提示：',
					shade: 0.6,
					maxmin: false,
					anim: 0,
					content: '<div style="padding:50px;">文章内容不能为空.</div>'
				});
				return;
			}
			$("#form").submit();
		});
	</script>
  </body>
</html>