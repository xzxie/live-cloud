<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.Constant"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>云烟阁 - 为直播而生</title>
	<jsp:include page="/include/meta.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="/static/css/index.css?t=<%=Constant.VERSION %>">
  </head>
  
  <body>
  	<jsp:include page="/include/header.jsp"></jsp:include>
  	
  	<!-- banner -->
	<div id="section-banner" class="lazy">
		<div id="container-welcome">
			<div class="header">Welcome to aliyun Elastic Compute Service!</div>
			<div class="command-line">[root@iZ945afm343Z /]# <span class="input-span" id="input-span-0"></span></div>
		</div>
		<div id="container-login">
			<form action="" method="post">
				<div>
					<input type="text" name="username" id="username" class="input radius2" placeholder="QQ / Phone">
				</div>
				<div>
					<input type="password" name="password" id="password" class="input radius2" placeholder="密码">
				</div>
				<div>
					<input type="button" id="login-btn" class="radius2" value="登 录">
				</div>
				<div>
					<div class="qq-phone-login font13"><a href="/user/login_qq.do">QQ</a>    |    <a href="/user/login_phone.do">Phone</a></div>
					<div class="reg font13"><a href="/user/reg.do">还未注册 ?</a></div>
				</div>
			</form>
		</div>
	</div>
	
	<!-- product -->
	<div class="section" id="product">
		<div class="container-title">
			<hr>
			<span class="title">产品介绍</span>
		</div>
		<div class="container"></div>
	</div>
	
	<!-- cdn -->
	<div class="section" id="cdn">
		<div class="container-title">
			<hr>
			<span class="title">CDN加速</span>
		</div>
		<div class="container"></div>
	</div>
	
	<!-- package -->
	<div class="section" id="package">
		<div class="container-title">
			<hr>
			<span class="title">套餐服务</span>
		</div>
		<div class="container"></div>
	</div>
	
	<!-- client -->
	<div class="section" id="client">
		<div class="container-title">
			<hr>
			<span class="title">客户端</span>
		</div>
		<div class="container"></div>
	</div>
	
	<!-- footer-navi -->
	<div class="section" id="footer-navi">
		<div class="container-title">
			<hr>
			<span class="title">底部导航</span>
		</div>
		<div class="container">
			<div>关于我们</div>
			<div>合作共赢</div>
			<div>网站导航</div>
			<div>网站备案</div>
		</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<jsp:include page="/include/commonjs.jsp"></jsp:include>
	<script type="text/javascript">
		var countChar = 0;
		var line = 0;
		var countBanner = 0;
		var	command = [
			"Read The Fucking Source Code!",
			"Read The Fucking Source Code!",
			"Read The Fucking Source Code!",
			"重要的事情说三遍......     "
		];
		$(document).ready(function(){
			// banner初始化大小
			var initBannerHeight = window.innerHeight - $("#header-navi").height();
			/* $("#section-banner").css({"height": initBannerHeight}); */
			// 依据屏幕高度变化调节banner大小
			/* $(window).resize(function(){
				if (window.innerHeight - $("#header-navi").height() > 500) {
					$("#section-banner").css({"height": (window.innerHeight - $("#header-navi").height())});
				}
			}); */
			
			interval = setInterval(showTextInterval, 100);
			bgImgInterval = setInterval(showBgImgInterval, 4000);
			$(".header-navi-detail a").each(function(){
				$(this).on("mouseover", function(){
					$(".header-navi-detail a").removeClass("cur1");
					$(this).addClass("cur1");
				})
				.on("mouseout", function(){
					$(this).addClass("cur1");
				})
				.on("click", function(){
					$(this).addClass("cur1");
				});
			});
			
			//滚动时顶部按钮下划线改变
			$(window).scroll(function(){
				var scroll_top = $(document).scrollTop();//滚动条到顶部的垂直高度
		        var bannerHight = $("#section-banner").height() + $("#section-banner").offset().top;
		        var productHeight = $("#product").height() + $("#product").offset().top;
		        var cdnHeight = $("#cdn").height() + $("#cdn").offset().top;
		        var packageHeight = $("#package").height() + $("#package").offset().top;
		        var clientHeight = $("#client").height() + $("#client").offset().top;
		        var footerNaviHeight = $("#footer-navi").offset().top;
		        
		        $(".header-navi-detail a").removeClass("cur1");
		        if (scroll_top<bannerHight || scroll_top>footerNaviHeight) {
		        	$(".header-navi-detail a:eq(0)").addClass("cur1");
		        } else if (scroll_top < productHeight) {
		        	$(".header-navi-detail a:eq(1)").addClass("cur1");
		        } else if (scroll_top < cdnHeight) {
		        	$(".header-navi-detail a:eq(2)").addClass("cur1");
		        } else if (scroll_top < packageHeight) {
		        	$(".header-navi-detail a:eq(3)").addClass("cur1");
		        } else if (scroll_top < clientHeight) {
		        	$(".header-navi-detail a:eq(4)").addClass("cur1");
		        }
			});
			
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
			
			function showBgImgInterval() {
				countBanner ++;
				$("#section-banner").css("background","rgba(0, 0, 0, 0) url(/static/image/banner-" + countBanner + ".jpg) no-repeat fixed 50% 0 / cover");
				if (countBanner == 4) {
					countBanner = 0;
				}
			}
			
		});
	</script>
  </body>
</html>
