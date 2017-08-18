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
	    		<div class="body">
	    			<table class="query-table">
	    			<form method="post" action="/admin/category.do" id="cateForm">
	    				<input type="hidden" class="input" name="file_id" value="${file_id }" id="file_id" />
	    				<tr>
	    					<td>
	    						<label>描述:</label>
	    						<input type="text" name="description" id="description" />
	    					</td>
	    					<td>
	    						<label>级别:</label>
	    						<select name="level" id="level">
	    							<option value="">全部</option>
	    							<option value="1">目录</option>
	    							<option value="2">页面</option>
	    						</select>
	    					</td>
	    					<td>
	    						<input type="button" name="search" class="search-btn" value="查 询" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td>
	    						<label>编码:</label>
	    						<input type="text" name="value" id="value" />
	    					</td>
	    					<td>
	    						<label>状态:</label>
	    						<select name="status" id="status">
	    							<option value="">全部</option>
	    							<option value="0">正常</option>
	    							<option value="1">删除</option>
	    						</select>
	    					</td>
	    					<td>
	    						<input type="button" name="search" class="search-btn btn-orange" value="新 增" />
	    					</td>
	    				</tr>
	    			</form>
	    			</table>
	    			<table class="record-table">
	    				<tr>
	    					<td>共有 <span class="record">${fn:length(cateList)}</span> 条记录</td>
	    				</tr>
	    			</table>
	    			<table class="result-table">
	    				<thead>
		    				<tr>
		    					<th>ID</th>
		    					<th>描述</th>
		    					<th>上级ID</th>
		    					<th>链接</th>
		    					<th>级别</th>
		    					<th>编码</th>
		    					<th>创建时间</th>
		    					<th>状态</th>
		    					<th>操作</th>
		    				</tr>
	    				</thead>
	    				<tbody>
	    				<c:choose>
	    					<c:when test="${fn:length(cateList) <= 0}">
	    						<tr><td colspan="9">暂无数据!</td></tr>
	    					</c:when>
	    					<c:otherwise>
	    						<c:forEach items="${cateList }" var="cate">
	    						<tr>
			    					<td>${cate.id }</td>
			    					<td>${cate.description }</td>
			    					<td>${cate.parent_id }</td>
			    					<td>${cate.href }</td>
			    					<td>${cate.level }</td>
			    					<td>${cate.value }</td>
			    					<td>${fn:substring(cate.create_time,0,19)}</td>
			    					<td>
			    						<c:if test="${cate.status=='0' }">正常</c:if>
			    						<c:if test="${cate.status=='1' }">删除</c:if>
			    					</td>
			    					<td>
			    						<c:if test="${cate.status=='0' }"><a href="javascript:void(0);" onclick="" target="_self">删除</a></c:if>
			    					</td>
		    					</tr>
		    					</c:forEach>
	    					</c:otherwise>
	    				</c:choose>
	    				</tbody>
	    			</table>
	    		</div>
	    	</div>
	    </div>
    </div>
  </body>
  <%@include file="/jsp/admin/admincommonjs.jsp" %>
</html>