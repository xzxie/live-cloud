$(function(){
	var countChar = 0;
	var line = 0;
	var countBanner = 0;
	var	command = [
		"Read The Fucking Source Code!",
		"Read The Fucking Source Code!",
		"Read The Fucking Source Code!",
		"Remember this sentence and execute it...     "
	];
	
	// 顶部菜单导航鼠标滚动样式切换
	var scroll_top = $(document).scrollTop();//滚动条到顶部的垂直高度
	if (scroll_top > 50) {
    	$("#header-navi").addClass("header-navi-scroll");
    } else {
    	$("#header-navi").removeClass("header-navi-scroll");
    }
	
	// section-banner图片定时更换
	bgImgInterval = setInterval(showBgImgInterval, 4000);
	function showBgImgInterval() {
		countBanner ++;
		$("#section-banner").css("background","rgba(0, 0, 0, 0) url(/static/image/banner-" + countBanner + ".jpg) no-repeat fixed 50% 0 / cover");
		if (countBanner == 4) {
			countBanner = 0;
		}
	}
	
	// 首页command-line动画登录后不再显示
	var animal_cookie = $.cookie("index-animal");
	//interval = setInterval(showTextInterval, 100);
	if (animal_cookie === "first-load") {
		$("#container-welcome").css({"display":"block"});
		interval = setInterval(showTextInterval, 100);
	} else {
		$("#container-login").fadeIn(2000);
	}
	
	// 登录框垂直居中
	$("#container-login").css({"margin-top": (_config.bodyHeight-$("#container-login").height())/2 + 25});
	
    // section高度调整
    var config_height = _config.height;
    if (config_height < 600) {
    	config_height = 600;
    } else if (_config.height > 750) {
    	config_height = 750;
    }
    var screenFullHeight = config_height - $("#header-navi").height() - 50;
    $("#product").height(screenFullHeight);
    $("#cdn").height(screenFullHeight);
    $("#package").height(screenFullHeight);
    $("#client").height(screenFullHeight);
    $("#footer-navi").height(screenFullHeight);
    
    // 产品视频高度设置
    $("#product-video").css({"height": screenFullHeight - $(".container-title").height() -35});
	
	// 套餐服务
    var item = $("#package .package-item");
    var item_top = $("#package .package-item .top");
    item.each(function(index, element){
    	$("#package .package-item:eq(0)").find(".top").css({"background":"rgba(0, 0, 0, 0) url(/static/image/package-top.jpg) no-repeat scroll 0 0 / cover"});
		$(this).on("mouseover", function(){
			item.removeClass("package-item-active");
			item_top.css({"background-image": "linear-gradient(-180deg, #273844 0px, #1f313d 52%, #273844 100%)"});
			$(this).addClass("package-item-active");
			$(this).find(".top").css({"background": "rgba(0, 0, 0, 0) url(/static/image/package-top.jpg) no-repeat scroll 0 0 / cover"});
		}).on("mouseout", function(){
			item.removeClass("package-item-active");
			item_top.css({"background-image": "linear-gradient(-180deg, #273844 0px, #1f313d 52%, #273844 100%)"});
			$(this).addClass("package-item-active");
			$(this).find(".top").css({"background": "rgba(0, 0, 0, 0) url(/static/image/package-top.jpg) no-repeat scroll 0 0 / cover"});
		});
	});
	
	// 客户端gif图片加载
	$(".client-animal img").attr("src", $(".client-animal img").attr("src")+"?"+Math.random());
	
	// 客户端二维码
	$(".client-iphone img").on("mouseover", function() {
		$(this).attr("src", "/qrcode.png?ref=iphone");
	}).on("mouseout", function() {
		$(this).attr("src", "//www.xiexiaozhang.com/static/image/client-iphone.png");
	});
	$(".client-ipad img").on("mouseover", function() {
		$(this).attr("src", "/qrcode.png?ref=ipad");
	}).on("mouseout", function() {
		$(this).attr("src", "//www.xiexiaozhang.com/static/image/client-ipad.png");
	});
	$(".client-android img").on("mouseover", function() {
		$(this).attr("src", "/qrcode.png?ref=android");
	}).on("mouseout", function() {
		$(this).attr("src", "//www.xiexiaozhang.com/static/image/client-android.png");
	});
	
	// 滚动时顶部菜单下划线切换
	$(window).scroll(function(){
		var scroll_top = $(document).scrollTop();//滚动条到顶部的垂直高度
        var bannerHight = $("#section-banner").height() + $("#section-banner").offset().top;
        var productHeight = $("#product").height() + $("#product").offset().top;
        var cdnHeight = $("#cdn").height() + $("#cdn").offset().top;
        var packageHeight = $("#package").height() + $("#package").offset().top;
        var clientHeight = $("#client").height() + $("#client").offset().top;
        var footerNaviHeight = $("#footer-navi").offset().top;
        
        $(".header-navi-detail .menu").removeClass("cur2");
        if (scroll_top<bannerHight || scroll_top>footerNaviHeight) {
        	$(".header-navi-detail .menu:eq(0)").addClass("cur2");
        } else if (scroll_top < productHeight) {
        	$(".header-navi-detail .menu:eq(1)").addClass("cur2");
        } else if (scroll_top < cdnHeight) {
        	$(".header-navi-detail .menu:eq(2)").addClass("cur2");
        } else if (scroll_top < packageHeight) {
        	$(".header-navi-detail .menu:eq(3)").addClass("cur2");
        } else if (scroll_top < clientHeight) {
        	$(".header-navi-detail .menu:eq(4)").addClass("cur2");
        }
	});
	
	// command-line样式输出
	function showTextInterval() {
		var consoleText = $("#input-span-" + line).text();
		$("#input-span-" + line).text(consoleText + command[line][countChar]).show();
		countChar ++;
		if (countChar == command[line].length) {
			countChar = 0;
			line ++;
			if (line < command.length) {
				$("#container-welcome").append("<div class='command-line'>[root@iZ945afm343Z /]# <span class='input-span' id='input-span-" + line + "'></span></div>").show();
			} else {
				window.clearInterval(interval);
				$("#container-welcome").animate({left:-2000},"slow");
				$("#container-welcome").fadeOut();
				$("#container-login").fadeIn(2000);
			}
		}
	}
	
});

function apply() {
	var islogin = isLogin();
	if (islogin) {
		window.location.href = "/cdn/apply.do";
	} else {
		var layHtml = "<div style='padding:50px;'>这是一个非常普通的页面层，传入了自定义的html</div>";
		layer.open({
			type: 1, //Page层类型
			area: ['400px', '200px'],
			title: '提示',
			shade: 0.6, //遮罩透明度
			maxmin: false, //允许全屏最小化
			anim: 0, //0-6的动画形式，-1不开启
			content: layHtml
		});
		$("#layui-layer1").css({"position":"fixed", "top":"0", "bottom":"0", "left":"0", "right":"0", "margin":"auto"});
		$(".layui-layer-move").css();
	}
}