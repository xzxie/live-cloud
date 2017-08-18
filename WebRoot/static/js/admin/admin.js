$(function(){
	// 展开上次点击的目录
	var jsonStr = $("#_dir_file_").val();
	if (jsonStr.length > 0) {
		// 设置title
		var dir_file = $.parseJSON(jsonStr);
		$("#_dir_description_").text(dir_file.dirDesc);
		$("#_file_description_").text(dir_file.fileDesc);
		// 展开
		toggleCate(dir_file.dirId);
		// 设置file被选中样式
		$("#li_file_" + dir_file.fileId + " a").css({"color":"white", "background-color":"#96c2e2", "padding":"5px"});
	}
});

function toggleCate(parent_id){
	var parent = $("#li_cate_" + parent_id);
	var ul_file = $("#ul_file_" + parent_id);
	
	if ($( "#ul_file_" + parent_id ).has("li").length == 0){
		var cellMap={};
		cellMap.parent_id = parent_id;
		$.ajax({
			url:"/admin/subCategory.do",
			async:false,
			data:cellMap,
			type:"post",
			dataType:"json",
			success:function(r){
				if(r && r.DATA && r.DATA.length > 0){//操作成功
					var innerHtml = "";
					for (var index=0; index<r.DATA.length; index++) {
						var id = r.DATA[index].id;
						var description = r.DATA[index].description;
						var href= r.DATA[index].href;
						innerHtml += "<li id='li_file_" + id + "' class='li_file'>" + 
										"<a class='blue' href='" + href + "?file_id=" + id + "' target='_self'>" + description + "</a>" +
									"</li>";
					}
					ul_file.html(innerHtml);
				}else{
					//alert("加载失败 或 无下级....");
				}
			},
			error:function(r){
				layer.open({
					type: 1, //page层类型
					area: ['400px', '200px'],
					title: '提示',
					shade: 0.6, //遮罩透明度
					maxmin: false, //允许全屏最小化
					anim: 0, //0-6的动画形式，-1不开启
					content: '<div style="padding:50px;">当前为非登录状态.</div>'
				});
				window.location.href="/admin/index.do";
			}
		});
	} else if (ul_file.has("li").length > 0) {
		ul_file.toggle();
	}
}

function getRequestURI() {
	var requestUri = window.location.pathname;
	var file_id = $("#file_id").val();
	requestUri = requestUri + "?file_id=" + file_id;
	return requestUri;
}
