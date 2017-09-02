var ai = ai || {};
ai.ocr = ai.ocr || {};


ai.ocr = {
		checkURL : function() {
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
				var layHtml = "<div style='padding:50px;'>图片格式不正确.</div>";
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
		},
		
		upload : function() {
			$("#type").val(2);
		},
		
		asyncOCR : function() {
		  	$.ajax({
				url:"/ai/text.do",
				data:$('#upload-form').serialize(),
				type:"post",
				dataType:"json",
				success:function(r){
					//var result = r.RESULT;
					//$("#code").val(result);
					alert(123433);
				},
				error:function(r){
					alert("网络错误....");
				}
			});
		}
}