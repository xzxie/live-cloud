<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.SeoUtil"%>
<%@ page import="com.util.Constant"%>
<!doctype html>
<html>
<head>
<title><%=SeoUtil.siteName %> - 为直播而生</title>
<jsp:include page="/include/meta.jsp"></jsp:include>
</head>

<body>
<div class="repair">
	<img src="<%=Constant.STATIC_PREFIX %>/static/image/close.png?t=<%=Constant.VERSION %>" style="padding-top: 100px;"/>
</div>
<%@include file="/include/analysis.jsp" %>
</body>
</html>