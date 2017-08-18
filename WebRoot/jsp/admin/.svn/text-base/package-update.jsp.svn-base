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
	    			<table class="result-table">
	    			<form method="get" action="/admin/packages.do" id="form">
	    				<input type="hidden" class="input" name="file_id" value="${file_id }" id="file_id" />
	    				<input type="hidden" class="input" name="operation" value="insert" id="operation" />
	    				<tr>
	    					<td>
	    						<label>套餐名</label>
	    					</td>
	    					<td>
	    						<input type="text" name="package_name" id="package_name" class="input150" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td>
	    						<label>套餐类型</label>
	    					</td>
	    					<td>
	    						<select name="type" id="type" class="select150">
	    							<option value="1">1月</option>
	    							<option value="2">3月</option>
	    							<option value="3">6月</option>
	    							<option value="4">1年</option>
	    							<option value="5">永久</option>
	    						</select>
	    					</td>
	    				</tr>
	    				<tr>
	    					<td>
	    						<label>生效时间</label>
	    					</td>
	    					<td>
	    						<input type="text" onclick="laydate()" name="effect_time" id="effect_time" class="input150" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td>
	    						<label>失效时间</label>
	    					</td>
	    					<td>
	    						<input type="text" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" name="expire_time" id="expire_time" class="input150" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td>
	    						<label>状态</label>
	    					</td>
	    					<td>
	    						<select name="type" id="type" class="select150">
	    							<option value="0">正常</option>
	    							<option value="1">删除</option>
	    						</select>
	    					</td>
	    				</tr>
	    				<tr>
	    					<td>
	    						<label>套餐描述</label>
	    					</td>
	    					<td>
	    						<textarea rows="5" cols="50" name="package_description" id="package_description"></textarea>
	    					</td>
	    				</tr>
	    				<tr>
	    					<td colspan="2">
	    						<input type="button" name="search" onclick="updatePackage()" class="search-btn btn-orange" value="编 辑" />
	    					</td>
	    				</tr>
	    				</form>
	    			</table>
	    		</div>
	    		<!-- //填充部分 -->
	    		
	    	</div>
	    </div>
    </div>
  </body>
  <%@include file="/jsp/admin/admincommonjs.jsp" %>
  <script type="text/javascript">
  	// 编辑套餐
  	function updatePackage() {
  		//$("#form").submit();
  	}
  </script>
</html>