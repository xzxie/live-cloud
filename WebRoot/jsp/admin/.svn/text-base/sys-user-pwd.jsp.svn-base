<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="/jsp/admin/meta.jsp" %>
	<style type="text/css">
		.submit-table{width: 60%; margin: 100px auto; font-weight: bold;}
		.submit-table tr{}
		.submit-table tr td{margin: 10px 5px;padding: 10px 5px;}
		.input{border: 1px solid #ccc;color: #222;height: 30px;width: 220px;padding: 0 0 0 3px;}
		.input:focus{border-color: #4d90fe;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(77, 144, 254, 0.6);}
		.search-btn{width: 185px; padding: 5px 15px; border-radius:4px;}
	</style>
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
	    			<table class="submit-table">
	    			<form method="post" action="/admin/sysUserPwd.do" id="form" onsubmit="return validate();">
	    				<input type="hidden" class="input" name="file_id" value="${file_id }" id="file_id" />
	    				<input type="hidden" class="input" name="operation" value="update" id="operation" />
	    				<input type="hidden" class="input" name="id" value="${sessionScope.adminUserinfo.id }" id="id" />
	    				<tr>
	    					<td class="align-right"></td>
	    					<td><span class="red" id="tips"></span></td>
	    				</tr>
	    				<tr>
	    					<td class="align-right">
	    						<label>用户名:</label>
	    					</td>
	    					<td class="align-left">
	    						<input type="text" class="input" name="username" value="${sessionScope.adminUserinfo.username }" disabled="disabled" id="username" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td class="align-right">
	    						<label>原始密码:</label>
	    					</td>
	    					<td class="align-left">
	    						<input type="password" class="input" name="password" id="password" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td class="align-right">
	    						<label>新密码:</label>
	    					</td>
	    					<td class="align-left">
	    						<input type="password" class="input" name="passwordNew" id="passwordNew" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td class="align-right">
	    						<label>再次输入新密码:</label>
	    					</td>
	    					<td class="align-left">
	    						<input type="password" class="input" name="passwordNewRepeat" id="passwordNewRepeat" />
	    					</td>
	    				</tr>
	    				<tr>
	    					<td></td>
	    					<td>
	    						<input type="submit" name="search" class="search-btn" value="修 改" />
	    					</td>
	    				</tr>
	    				</form>
	    			</table>
	    		</div>
	    	</div>
	    </div>
    </div>
  </body>
  <%@include file="/jsp/admin/admincommonjs.jsp" %>
  <script type="text/javascript">
  function validate(){
  	$("#tips").empty();
  	var password = $("#password").val();
  	var passwordNew = $("#passwordNew").val();
  	var passwordNewRepeat = $("#passwordNewRepeat").val();
  	if (password == "") {
  		$("#tips").text("填写原始密码。");
  		return false;
  	} else if (passwordNew == "") {
  		$("#tips").text("填写新密码。");
  		return false;
  	} else if (passwordNewRepeat == "") {
  		$("#tips").text("再次填写新密码。");
  		return false;
  	}
  	if (password == passwordNew) {
  		$("#tips").text("新设置的密码不能与原密码相同。");
  		return false;
  	} else if (passwordNew != passwordNewRepeat) {
  		$("#tips").text("两次输入新密码必须相同。");
  		return false;
  	}
  	var cellMap = $("#form").serialize();
  	$.ajax({
		url:"/admin/sysUserPwd.do",
		async:false,
		data:cellMap,
		type:"post",
		dataType:"json",
		success:function(r){
			if(r && r.RESULT == "success"){//操作成功
				$("#tips").text("修改密码成功!");
			}else if (r.RESULT == "fail") {
				$("#tips").text("操作失败！");
			}
		},
		error:function(r){
			$("#tips").text("网络连接错误!");
		}
	});
  	return true;
  }
  </script>
</html>