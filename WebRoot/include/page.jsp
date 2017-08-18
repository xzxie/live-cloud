<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.model.SearchBean"%>

<%
String param = request.getParameter("param");
SearchBean searchBean = (SearchBean)request.getAttribute("searchBean");
%>

<div class="page-box">
	<%if(searchBean.getP()>=2){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()-1 %>&f=${f}">&lt;上一页</a>
	<%} %>
	<%if(searchBean.getP()>=6){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()-5 %>&f=${f}"><%=searchBean.getP()-5 %></a>
	<%} %>
	<%if(searchBean.getP()>=5){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()-4 %>&f=${f}"><%=searchBean.getP()-4 %></a>
	<%} %>
	<%if(searchBean.getP()>=4){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()-3 %>&f=${f}"><%=searchBean.getP()-3 %></a>
	<%} %>
	<% if(searchBean.getP()>=3){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()-2 %>&f=${f}"><%=searchBean.getP()-2 %></a>
	<%} %>
	<% if(searchBean.getP()>=2){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()-1 %>&f=${f}"><%=searchBean.getP()-1 %></a>
	<%} %>
	
	<a class="active" href="/<%=param %>/index.do?p=<%=searchBean.getP() %>&f=${f}"><%=searchBean.getP() %></a>
	
	<%if( (searchBean.getP()+1<=searchBean.getCp()) && ((searchBean.getP()<=9) || (searchBean.getP()>=7)) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+1 %>&f=${f}"><%=searchBean.getP()+1 %></a>
	<%} %>
	<% if( (searchBean.getP()+2<=searchBean.getCp()) && (searchBean.getP()<=8) || (searchBean.getP()>=7) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+2 %>&f=${f}"><%=searchBean.getP()+2 %></a>
	<%} %>
	<% if( (searchBean.getP()+3<=searchBean.getCp()) && (searchBean.getP()<=7) || (searchBean.getP()>=7) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+3 %>&f=${f}"><%=searchBean.getP()+3 %></a>
	<%} %>
	<% if( (searchBean.getP()+4<=searchBean.getCp()) && (searchBean.getP()<=6) || (searchBean.getP()>=7) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+4 %>&f=${f}"><%=searchBean.getP()+4 %></a>
	<%} %>
	<% if( (searchBean.getP()+5<=searchBean.getCp()) && (searchBean.getP()<=5) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+5 %>&f=${f}"><%=searchBean.getP()+5 %></a>
	<%} %>
	<% if( (searchBean.getP()+6<=searchBean.getCp()) && (searchBean.getP()<=4) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+6 %>&f=${f}"><%=searchBean.getP()+6 %></a>
	<%} %>
	<% if( (searchBean.getP()+7<=searchBean.getCp()) && (searchBean.getP()<=3) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+7 %>&f=${f}"><%=searchBean.getP()+7 %></a>
	<%} %>
	<% if( (searchBean.getP()+8<=searchBean.getCp()) && (searchBean.getP()<=2) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+8 %>&f=${f}"><%=searchBean.getP()+8 %></a>
	<%} %>
	<% if( (searchBean.getP()+9<=searchBean.getCp()) && (searchBean.getP()<=1) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+9 %>&f=${f}"><%=searchBean.getP()+9 %></a>
	<%} %>
	<%if( (searchBean.getCp()>=2) && (searchBean.getP()+1<=searchBean.getCp()) ){ %>
	<a href="/<%=param %>/index.do?p=<%=searchBean.getP()+1 %>&f=${f}">下一页&gt;</a>
	<%} %>
</div>
