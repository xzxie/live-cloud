<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.util.Constant" %>
<%
	String _switch = request.getParameter("switch");
	String code = request.getParameter("code");
	String site_switch = request.getParameter("site-switch");
	String cache_switch = request.getParameter("cache-switch");
	
	if (StringUtils.isNotBlank(_switch) && "xiexiaozhang".equals(code)) {
		if ("site-switch".equals(_switch)) {
			Constant.site_open_close_switch = site_switch;
		} else if ("cache-switch".equals(_switch)) {
			Constant.cache_open_close_swtich = cache_switch;
		}
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网站配置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
		body{margin: auto;padding: auto;}
	</style>
  </head>
  
  <body>
  	<hr/>
  	
  	<%-- 开启 /关闭网站 --%>
  	<form action="" method="get" name="site-form" id="site-form"> 
  		网站：
  		<select name="site-switch">
  			<option value="open" <%if("open".equals(Constant.site_open_close_switch)){ %>selected="selected"<%} %>>开启</option>
  			<option value="close" <%if("close".equals(Constant.site_open_close_switch)){ %>selected="selected"<%} %>>关闭</option>
  		</select>
  		<input type="text" name="code">
  		<input type="hidden" name="switch" value="site-switch">
  		<input type="submit" value="设置">
  	</form>
    <hr/>
    
    <%-- 开启/关闭缓存 --%>
    <form action="" method="get" name="cache-form" id="cache-form">
    	缓存：
    	<select name="cache-switch">
  			<option value="open">开启</option>
  			<option value="close">关闭</option>
  		</select>
  		<input type="text" name="code">
  		<input type="hidden" name="switch" value="cache-switch">
  		<input type="submit" value="设置">
    </form>
    <hr/>
    
  </body>
</html>
