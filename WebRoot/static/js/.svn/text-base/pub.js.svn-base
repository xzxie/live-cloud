$(function(){
	
	// 设置header的title
	var item = $("#item").val();
	if (item === "article") {
		$("#header-title").text("最新文章");
		$("#header-post a").attr("href", "/pub/article_post.do");
		$("#header-post a").text("发布文章");
	} else if (item === "question") {
		$("#header-title").text("最新问答");
		$("#header-post a").attr("href", "/pub/question_post.do");
		$("#header-post a").text("发布提问");
	}
	
	// 搜索enter事件
	$("#keyword").on("keydown", function(event){
		var keyword = $("#keyword").val();
		if (event.keyCode == "13") {
			if (item.length>0 && keyword.length>0) {
				$("#search-form").submit();
			}
		}
	});
});

// 文章详细页
$(".list-container .list").each(function(index, ele){
	$(this).on("click", function() {
		var articleId = $(this).data("id");
		if (articleId!=null && articleId!=undefined && articleId!="") {
			window.location.href = "/pub/article_detail.do?id=" + articleId;
		}
	});
});