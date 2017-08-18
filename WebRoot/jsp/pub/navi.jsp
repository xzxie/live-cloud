<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<%@ page import="com.util.SeoUtil"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
	String cur = request.getAttribute("cur")+"";
	if (StringUtils.isBlank(cur)) {
		cur = "index";
	}
%>
<div class="navi-container">
	<div class="logo">
		<a href="/">
			<img alt="logo" src="<%=Constant.STATIC_PREFIX %>/static/image/logo.png?t=<%=Constant.VERSION %>">
		</a>
	</div>
	<div class="navi-list">
		<div class="list" <%if("index".equals(cur)){ %>style="border-right:4px solid #32d3c3;"<%} %>><a href="/pub/article_list.do" <%if("index".equals(cur)){ %>style="color: #32d3c3;"<%} %>>最新文章</a></div>
		<div class="list" <%if("question".equals(cur)){ %>style="border-right:4px solid #32d3c3;"<%} %>><a href="/pub/question_list.do" <%if("question".equals(cur)){ %>style="color: #32d3c3;"<%} %>>最新提问</a></div>
		<div class="list" <%if("about".equals(cur)){ %>style="border-right:4px solid #32d3c3;"<%} %>><a href="/pub/about.do" <%if("about".equals(cur)){ %>style="color: #32d3c3;"<%} %>>关于我们</a></div>
		<div class="list" <%if("techsalon".equals(cur)){ %>style="border-right:4px solid #32d3c3;"<%} %>><a href="/pub/techsalon.do" <%if("techsalon".equals(cur)){ %>style="color: #32d3c3;"<%} %>>技术沙龙</a></div>
	</div>
</div>

<div class="footer-container">
@2017-<%=SeoUtil.siteName %>技术论坛  <br/>All rights reserved.
</div>

<div id="totop"></div>