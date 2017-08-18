<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.SeoUtil" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <jsp:include page="/jsp/admin/meta.jsp"></jsp:include>
	
	<style type="text/css">
		body{background-color: #f7fafc; background-image: none;}
		.login_box{text-align: center; margin:200px 220px;}
		.box{text-align: center; padding: 7px 0; clear: left;}
		.login_box input{border: 1px solid #bbb; margin-left: 10px; height: 41px; border-radius:3px; color:#272626; padding: 0 10px;}
		.loginBtn{background-color: #0096df; border:1px solid #0096df; width:90px; border-radius:3px; color:white;}
		.loginBtn:hover{background-color: #66cefd; border: 1px solid #66cefd; cursor: pointer;}
		img{margin-left: 10px;border-radius: 3px;}
	</style>
  </head>
  
  <body>
  	<div class="login_box">
  		<div class="box" style="margin-bottom: 150px;"><a href="/admin/index.do" style="color: #999999;font-size: 30px;text-decoration: none;box-shadow: 0px 2px 20px 5px;border-radius: 5px;padding: 10px;"><%=SeoUtil.siteName %> - 后台管理</a></div>
	    <form action="/admin/index.do" method="post" name="admin_form" onsubmit="return validateCode()">
	    	<div class="box">
		    	<input type="text" name="username" id="username" size="30" placeholder="用户名" autocomplete="off">
		    	<input type="password" id="password" name="password" size="30" placeholder="密码" autocomplete="off">
		    	<!-- 
		    	<input type="text" name="yanzheng" id="yanzheng" size="20" placeholder="验证码" autocomplete="off"/>
		    	<a href="javascript:void();"><img id="regimg" onclick="refCode();" style="float: left;" src="/servlet/getimg?from=adminIndex&t=<%=System.currentTimeMillis() %>"/></a>
		    	-->
		    	<input type="submit" value="登陆" class="loginBtn" style="border:1px solid #66cefd;color: white">
		    	<p id="errorinfo" style="color: red; margin-left: 10px;"></p>
	    	</div>
	    </form>
    </div>
    <div id="particles"></div>
  </body>
  <%@include file="/jsp/admin/admincommonjs.jsp" %>
  <script type="text/javascript" src="/static/js/particles.min.js"></script>
  <script type="text/javascript" src="/static/js/particle-init.js"></script>
  <script type="text/javascript">
    if (top.location != self.location) {  
        top.location = self.location;  
    }

    function refCode(){
	    jQuery('#regimg').attr("src",'/servlet/getimg?date='+new Date());
    }
    function validateCode(){
    	 jQuery('#errorinfo').empty();
    	 var userName= jQuery('#username').val();
    	 if(userName==''){
    		 jQuery('#errorinfo').append('温馨提示：请输入用户名！');
    		 return false;
    	 }
    	 var password= jQuery('#password').val();
    	 if(password==''){
    		 jQuery('#errorinfo').append('温馨提示：请输入密码！');
    		 return false;
    	 }
    	 
    	 //var yzcode= jQuery('#yanzheng').val();
    	 //if(yzcode==''){
    	 //	 jQuery('#errorinfo').append('温馨提示：请输入验证码！');
    	 //	 return false
    	 //}
    }
	</script>
</html>