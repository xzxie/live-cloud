<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="/jsp/admin/meta.jsp" %>
  </head>
  
  <body>
  	<div class="container">
	  	<%@include file="/jsp/admin/top.jsp" %>
	    <div class="body-container">
	    	<jsp:include page="/jsp/admin/navi.jsp"></jsp:include>
	    	<div class="content-container">
	    		<div class="title">
	    			<span id="_dir_description_"></span> >> <span id="_file_description_"></span>
	    		</div>
	    		<!-- 填充部分 -->
	    		<div class="body">
	    			<table class="query-table">
	    			<form method="get" action="/admin/sysUser.do" id="form">
	    				<input type="hidden" class="input" name="file_id" value="${file_id }" id="file_id" />
	    				<tr>
	    					<td class="desc">
	    						<label>ID:</label>
	    					</td>
	    					<td>
	    						<input type="text" name="id" id="id" />
	    					</td>
	    					<td class="desc">
	    						<label>用户名:</label>
	    					</td>
	    					<td>
	    						<input type="text" name="username" id="username" />
	    					</td>
	    					<td class="desc"></td>
	    					<td>
	    						<input type="button" name="search" class="search-btn" value="查 询" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td class="desc">
	    						<label>状态:</label>
	    					</td>
	    					<td colspan="3">
	    						<select name="status" id="status">
	    							<option value="">全部</option>
	    							<option value="0">正常</option>
	    							<option value="1">删除</option>
	    						</select>
	    					</td>
	    					<td class="desc"></td>
	    					<td>
	    						<input type="button" name="search" class="search-btn btn-orange" value="新 增" />
	    					</td>
	    				</tr>
	    				</form>
	    			</table>
	    			<table class="record-table">
	    				<tr>
	    					<td>共有 <span class="record">${fn:length(list)}</span> 条记录</td>
	    				</tr>
	    			</table>
	    			<table class="result-table">
	    				<thead>
		    				<tr>
		    					<th>ID</th>
		    					<th>用户名</th>
		    					<th>创建时间</th>
		    					<th>状态</th>
		    					<th>操作</th>
		    				</tr>
	    				</thead>
	    				<tbody>
	    				<c:choose>
	    					<c:when test="${fn:length(list) <= 0}">
	    						<tr><td colspan="5">暂无数据!</td></tr>
	    					</c:when>
	    					<c:otherwise>
	    						<c:forEach items="${list }" var="map">
	    						<tr>
			    					<td>${map.id }</td>
			    					<td>${map.username }</td>
			    					<td>${fn:substring(map.create_time,0,19)}</td>
			    					<td>
			    						<c:if test="${map.status=='0' }">正常</c:if>
			    						<c:if test="${map.status=='1' }">删除</c:if>
			    					</td>
			    					<td>
			    						<c:if test="${map.status=='0' }"><a href="javascript:void(0);" onclick="" target="_self">删除</a></c:if>
			    					</td>
		    					</tr>
		    					</c:forEach>
	    					</c:otherwise>
	    				</c:choose>
	    				</tbody>
	    			</table>
	    			<table class="page-table">
	    				<tr>
	    					<td><div id="page"></div></td>
	    				</tr>
	    			</table>
	    		</div>
	    		<!-- //填充部分 -->
	    	</div>
	    </div>
    </div>
  </body>
  <%@include file="/jsp/admin/admincommonjs.jsp" %>
  <script src="/static/js/laypage-v1.3/laypage.js"></script>
  <script type="text/javascript">
  	laypage({
	  cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
	  pages: 11, //总页数
	  skip: true, //是否开启跳页
	  skin: 'yahei', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
	  groups: 5 //连续显示分页数
	});
  </script>
</html>