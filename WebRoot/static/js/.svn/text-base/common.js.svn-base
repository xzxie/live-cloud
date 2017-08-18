// 初始化
$(function(){
	//图片懒加载
	$(".lazy").lazyload({effect:"fadeIn"});
	
    //置顶显示/隐藏
    $(window).scroll(function(){
        var w_height = $(window).height();//浏览器高度
        var scroll_top = $(document).scrollTop();//滚动条到顶部的垂直高度
        if (scroll_top > w_height/20) {
        	$("#totop").fadeIn(500);
        } else {
        	$("#totop").fadeOut(500);
        }
        //顶部样式改变
        if (scroll_top > 50) {
        	$("#header-navi").addClass("header-navi-scroll");
        } else {
        	$("#header-navi").removeClass("header-navi-scroll");
        }
    });
    //置顶
    $("#totop").on("mouseover", function(){
    	$("#totop").css({"background-image":"url(/static/image/totop.png)", "background-position":"0% 100%"});
    }).on("mouseout", function(){
    	$("#totop").css({"background-image":"url(/static/image/totop.png)", "background-position":"50% 0%"});
    }).on("click", function(){
    	$("body,html").animate({scrollTop: 0 }, 500);
    });
})