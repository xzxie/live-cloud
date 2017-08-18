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
	    			<form method="get" action="/admin/packages.do" id="form">
	    				<input type="hidden" class="input" name="file_id" id="file_id" value="${file_id }" />
	    				<tr>
	    					<td class="desc">
	    						<label>ID:</label>
	    					</td>
	    					<td>
	    						<input type="text" name="id" id="id" />
	    					</td>
	    					<td class="desc">
	    						<label>套餐名:</label>
	    					</td>
	    					<td>
	    						<input type="text" name="package_name" id="package_name" />
	    					</td>
	    					<td class="desc"></td>
	    					<td>
	    						<input type="button" name="search" onclick="query()" class="search-btn" value="查 询" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td class="desc">
	    						<label>套餐描述:</label>
	    					</td>
	    					<td>
	    						<input type="text" name="package_description" id="package_description" />
	    					</td>
	    					<td class="desc">
	    						<label>创建时间:</label>
	    					</td>
	    					<td>
	    						<input type="text" name="create_time" id="create_time" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" />
	    					</td>
	    					<td class="desc"></td>
	    					<td>
	    						<input type="button" name="search" onclick="newPackage()" class="search-btn btn-orange" value="新 增" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td class="desc">
	    						<label>生效时间:</label>
	    					</td>
	    					<td>
	    						<input type="text" name="effect_time" id="effect_time" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" />
	    					</td>
	    					<td class="desc">
	    						<label>失效时间:</label>
	    					</td>
	    					<td colspan="3">
	    						<input type="text" name="expire_time" id="expire_time" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td class="desc">
	    						<label>类型:</label>
	    					</td>
	    					<td>
	    						<select name="type" id="type" class="select100">
	    							<option value="">全部</option>
	    							<option value="0">正常</option>
	    							<option value="1">删除</option>
	    						</select>
	    					</td>
	    					<td class="desc">
	    						<label>状态:</label>
	    					</td>
	    					<td colspan="3">
	    						<select name="status" id="status" class="select100">
	    							<option value="">全部</option>
	    							<option value="0">正常</option>
	    							<option value="1">删除</option>
	    						</select>
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
		    					<th>套餐名</th>
		    					<th>套餐描述</th>
		    					<th>套餐类型</th>
		    					<th>创建人</th>
		    					<th>创建时间</th>
		    					<th>修改人</th>
		    					<th>修改时间</th>
		    					<th>生效时间</th>
		    					<th>失效时间</th>
		    					<th>状态</th>
		    					<th>操作</th>
		    				</tr>
	    				</thead>
	    				<tbody>
	    				<c:choose>
	    					<c:when test="${fn:length(list) <= 0}">
	    						<tr><td colspan="10">暂无数据!</td></tr>
	    					</c:when>
	    					<c:otherwise>
	    						<c:forEach items="${list }" var="map">
	    						<tr>
			    					<td>${map.id }</td>
			    					<td>${map.package_name }</td>
			    					<td>${map.package_description }</td>
			    					<td>${map.type }</td>
			    					<td>${map.create_user }</td>
			    					<td>${fn:substring(map.create_time,0,19) }</td>
			    					<td>${map.update_user }</td>
			    					<td>${fn:substring(map.update_time,0,19) }</td>
			    					<td>${fn:substring(map.effect_time,0,19) }</td>
			    					<td>${fn:substring(map.expire_time,0,19) }</td>
			    					<td>
			    						<c:if test="${map.status=='0' }">正常</c:if>
			    						<c:if test="${map.status=='1' }">删除</c:if>
			    					</td>
			    					<td>
			    						<c:if test="${map.status=='0' }">
			    							<a href="javascript:void(0);" onclick="del(${map.id })" target="_self">删除</a>
			    							<a href="javascript:void(0);" onclick="update(${map.id })" target="_self">编辑</a>
			    						</c:if>
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
  <script type="text/javascript">
  	// 查询
  	function query() {
  		$("#form").submit();
  	}
  	
  	// 新增套餐
  	function newPackage() {
  		var requestUri = getRequestURI();
  		window.location.href = requestUri + "&operation=toInsert";
  	}
  	
  	// 删除套餐
  	function del(id) {
		layer.open({
			type: 1, //Page层类型
			area: ['400px', '200px'],
			title: '你好，layer。',
			shade: 0.6, //遮罩透明度
			maxmin: false, //允许全屏最小化
			anim: 0, //0-6的动画形式，-1不开启
			content: '<div style="padding:50px;">这是一个非常普通的页面层，传入了自定义的html</div>'
		});
  	}
  	
  	// 编辑套餐
  	function update(id) {
  		
  	}
  </script>
</html>