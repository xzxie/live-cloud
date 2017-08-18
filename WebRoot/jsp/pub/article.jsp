<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<%@ page import="com.util.SeoUtil"%>
<%@ page import="com.util.WebUtil"%>
<%
	boolean isHttp = WebUtil.isHttp(request);
	boolean isHttps = WebUtil.isHttps(request);
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
			<div class="article-container">
				<div class="article">
					<div class="title">Vuex框架原理与源码分析</div>
					<div class="meta">
						<span class="meta-author">古龙</span> · 
						<span class="meta-time">2017-04-27</span>
					</div>
					<div class="content">
Vuex是一个专为Vue服务，用于管理页面数据状态、提供统一数据操作的生态系统。它集中于MVC模式中的Model层，规定所有的数据操作必须通过 action - mutation - state change 的流程来进行，再结合Vue的数据视图双向绑定特性来实现页面的展示更新。统一的页面状态管理以及操作处理，可以让复杂的组件交互变得简单清晰，同时可在调试模式下进行时光机般的倒退前进操作，查看数据改变过程，使code debug更加方便。

最近在开发的项目中用到了Vuex来管理整体页面状态，遇到了很多问题。决定研究下源码，在答疑解惑之外，能深入学习其实现原理。

先将问题抛出来，使学习和研究更有针对性：

    使用Vuex只需执行 Vue.use(Vuex)，并在Vue的配置中传入一个store对象的示例，store是如何实现注入的？
    state内部是如何实现支持模块配置和模块嵌套的？
    在执行dispatch触发action（commit同理）的时候，只需传入（type, payload），action执行函数中第一个参数store从哪里获取的？
    如何区分state是外部直接修改，还是通过mutation方法修改的？
    调试时的“时空穿梭”功能是如何实现的？

    注：本文对有Vuex有实际使用经验的同学帮助更大，能更清晰理解Vuex的工作流程和原理，使用起来更得心应手。初次接触的同学，可以先参考Vuex官方文档进行基础概念的学习。
					</div>
					<div class="tags">
						<span><a class="tag">Vuex</a></span>
						<span><a class="tag">Vue</a></span>
						<span><a class="tag">前端</a></span>
						<span><a class="tag">源码分析</a></span>
					</div>
				</div>
			</div>
			<!-- //文章详细页 -->
		</div>
	</div>
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script type="text/javascript" src="/static/js/pub.js?t=<%=Constant.VERSION %>"></script>
  </body>
</html>