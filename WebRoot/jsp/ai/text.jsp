<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<%@ page import="com.util.SeoUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>文字识别 - AI - <%=SeoUtil.siteName %></title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=Constant.STATIC_PREFIX %>/static/js/jquery-uploadify/uploadify.css?t=<%=Constant.VERSION %>"/>
<link rel="stylesheet" type="text/css" href="<%=Constant.STATIC_PREFIX %>/static/css/ai.css?t=<%=Constant.VERSION %>">
</head>
  
<body>
  	<jsp:include page="/include/header.jsp"></jsp:include>
  	
	<div class="ai-container">
		<div class="section section-card-text">
			<div class="introduce">
				<div class="title">通用文字识别</div>
				<div class="description">基于业界领先的深度学习技术，依托全网海量优质数据为您提供多场景、多语种、高精度的整图文字检测和识别服务</div>
				<div>
					<span><a href="">立即使用</a></span>
					<span><a href="">技术文档</a></span>
				</div>
			</div>
		</div>
		<div class="section section-tip">在线演示</div>
		<div class="section section-content">
			<div class="upload-box">
				<form action="" id="upload-form" method="post">
					<input type="hidden" name="type" id="type">
					<input type="hidden" name="filePath" id="filePath">
					<input type="file" name="uploadify" id="uploadify-btn" style="margin: 10px 0px 20px 0px;;">
					<div id="fileQueue"></div>
					<div style="margin-top: 20px;">
						<input type="text" class="file-url" name="file-url" id="file-url" placeholder="请输入网络URL" onclick="this.focus();this.select()" />
						&nbsp;&nbsp;
						<span id="checkUrl" class="uploadify-button" style="height: 30px; line-height: 30px; width: 120px; padding: 7px 20px; cursor: pointer;">
							<span class="uploadify-button-text">识别网络图片</span>
						</span>
					</div>
				</form>
			</div>
			<div class="display-box">
				<div class="img-display">
					<img id="ocr_demo" alt="上传图片" title="上传图片" width="400px" height="315px" src="/static/image/ai/ocr_demo.png">
				</div>
				<div class="code-display">
					<textarea id="code" style="width:400px; height:315px;" onclick="this.focus();this.select()"></textarea>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
  </body>
  <script type="text/javascript" src="<%=Constant.STATIC_PREFIX %>/static/js/layer-v3.0.3/layer.js?t=<%=Constant.VERSION %>"></script>
  <script src="<%=Constant.STATIC_PREFIX %>/static/js/jquery-uploadify/jquery.uploadify.js?t=<%=Constant.VERSION %>" type="text/javascript"></script>
  <script type="text/javascript">
  $(document).ready(function() {
    $("#uploadify-btn").uploadify({
        'swf'       : '/static/js/jquery-uploadify/uploadify.swf',
        'uploader'  : '/uploadfile',
        'folder'         : '/upload',
        'queueID'        : 'fileQueue',
        'cancelImg'      : '/static/js/jquery-uploadify/uploadify-cancel.png',
        'buttonText'     : '上传文件',
        'auto'           : true,
        'multi'          : false,
        'wmode'          : 'transparent',
        'simUploadLimit' : 999,
        'fileTypeExts'   : '*.bmp;*.png;*.jpg;*.jpeg',
        'fileTypeDesc'   : 'Picture Files',
        'onUploadSuccess': function(file, data, response) {
        	if (response) {
        		$("#filePath").val(data);
        		$("#ocr_demo").attr("src", data);
        		asyncOCR();
        	} else {
        		var layHtml = "<div style='padding:50px;'>上传失败</div>";
				layer.open({
					type: 1,
					area: ['400px', '200px'],
					title: '提示',
					shade: 0.6,
					maxmin: false,
					anim: 0,
					content: layHtml
				});
        	}
        }
    });
    $("#checkUrl").on("click", function(){
    	checkURL();
    });
  });
  
  function checkURL() {
  	var imgurl = $("#file-url").val();
  	if (imgurl.length == '') {
  		$("#file-url").focus();
  		return;
  	}
  	var imgtype = '';
  	if (imgurl.indexOf("/")>0) {
  		imgtype = imgurl.substring(imgurl.lastIndexOf(".")+1, imgurl.length);
  	}
  	if (imgtype != "png" && imgtype != "jpeg" && imgtype != "bmp" && imgtype != "jpg") {
		var layHtml = "<div style='padding:50px;'>图片格式不正确，图片格式支持png/bmp/jpg/jpeg.</div>";
		layer.open({
			type: 1,
			area: ['400px', '200px'],
			title: '提示',
			shade: 0.6,
			maxmin: false,
			anim: 0,
			content: layHtml
		});
		$("#layui-layer1").css({"position":"fixed", "top":"0", "bottom":"0", "left":"0", "right":"0", "margin":"auto"});
		$(".layui-layer-move").css();
	}
	$("#type").val(1);
	$("#filePath").val(imgurl);
  	$("#ocr_demo").attr("src", imgurl);
  	asyncOCR();
  }
  
  function asyncOCR() {
  	$.ajax({
		url:"/ai/text_handle.do",
		data:$('#upload-form').serialize(),
		type:"post",
		dataType:"json",
		success:function(r){
			var result = r.RESULT;
			$("#code").val(result);
		},
		error:function(r){
			alert("网络错误....");
		}
	});
  }
  </script>
</html>