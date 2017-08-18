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
    
    // 顶部菜单按钮鼠标移入移出事件
	$(".header-navi-detail .menu").each(function(){
		$(this).on("mouseover", function(){
			$(".header-navi-detail .menu").removeClass("cur2");
			$(this).addClass("cur2");
		})
		.on("mouseout", function(){
			$(this).addClass("cur2");
		})
		.on("click", function(){
			$(this).addClass("cur2");
		});
	});
	
	var requestUri = window.location.pathname;
	if (requestUri == "/") {
		$("#main-bar").addClass("cur2");
	}
    
    // 顶部菜单点击事件
	$("#main-bar").on("click", function(){
		window.top.location.href = "/";
	});
	$("#product-bar").on("click", function(){
		if ($("#product-bar").data("login") == false) {
			$("html,body").animate({scrollTop: $("#product").offset().top+4 }, 500);
		} else if ($("#product-bar").data("login") == true) {
			window.top.location.href = "/product/index.do";
		}
	});
	$("#cdn-bar").on("click", function(){
		if ($("#cdn-bar").data("login") == false) {
			$("html,body").animate({scrollTop: $("#cdn").offset().top+4 },1000);
		} else if ($("#cdn-bar").data("login") == true) {
			window.top.location.href = "/cdn/index.do";
		}
	});
	$("#package-bar").on("click", function(){
		if ($("#package-bar").data("login") == false) {
			$("html,body").animate({scrollTop: $("#package").offset().top+4 },1000);
		} else if ($("#package-bar").data("login") == true) {
			window.top.location.href = "/package/index.do";
		}
	});
	$("#client-bar").on("click", function(){
		if ($("#client-bar").data("login") == false) {
			$("html,body").animate({scrollTop: $("#client").offset().top+4 },1000);
		} else if ($("#client-bar").data("login") == true) {
			window.top.location.href = "/client/index.do";
		}
	});
    
    // 解决方案下拉框
    $("#solution-bar").on("mouseover", function(){
    	$(".solution-drop-box").show();
    }).on("mouseout", function(){
    	$(".solution-drop-box").hide();
    });
    $(".solution-drop-box .item").on("mouseover", function(){
    	$(this).find("a").css({"color":"white"});
    }).on("mouseout", function(){
    	$(this).find("a").css({"color":"#666"});
    });
    // AI下拉框
    $("#ai-bar").on("mouseover", function(){
    	$(".ai-drop-box").show();
    }).on("mouseout", function(){
    	$(".ai-drop-box").hide();
    });
    $(".ai-drop-box .item").on("mouseover", function(){
    	$(this).find("a").css({"color":"white"});
    }).on("mouseout", function(){
    	$(this).find("a").css({"color":"#666"});
    });
    
    //开发者下拉显示
    $(".developer").on("mouseover", function(){
    	$(".developer .more").show();
    }).on("mouseout", function(){
    	$(".developer .more").hide();
    });
    
    //登录用户下拉框
    $(".userinfo").on("mouseover", function(){
    	$(".userinfo .more").show();
    }).on("mouseout", function(){
    	$(".userinfo .more").hide();
    });
})

// 邮箱验证
function isEmail(email) {
	var regular = /^([a-z0-9A-Z]+[-|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
	return (regular.test(email));
}

// 手机号验证
function isPhone(phone) {
	var regular = /^1[34578]\d{9}$/;
	return (regular.test(phone));
}

// 有效密码验证
function isPassword(password) {
	var regular = /^[0-9a-zA-Z]{6,15}$/;
	return (regular.test(password));
}

// 为空
function isNull(string) {
	if (string=="" || string == null) {
		return true;
	}
	return false;
}

// 非空
function isNotNull(string) {
	return !isNotNull(string);
}


// 异步登录
function login_ajax() {
	var token = $("#token").val();
	var checkAddress = $("#checkAddress").val();
	var sid = $("#sid").val();
	var cellMap = {};
	cellMap.token = token;
	cellMap.checkAddress = checkAddress;
	cellMap.sid = sid;
	$.ajax({
		url:"/captcha/index.do",
		data:cellMap,
		async:false,
		type:"post",
		dataType:"json",
		success:function(r){
			var result = r.RESULT;
			if (result == "success") {
				$.ajax({
					url:"/user/login_ajax.do",
					data:$('#form').serialize(),
					type:"post",
					dataType:"json",
					success:function(r){
						var result = r.RESULT;
						if(result=="用户不存在" || result=="密码错误" || result=="用户失效" || result=="用户失效"){
							alert(result);
						}else if(result=="登录成功"){
							window.location.href="/user/home.do";
						}
					},
					error:function(r){
						alert("网络错误....");
					}
				});
			} else {
				alert("验证失败.");
			}
			
		},
		error:function(r){
			alert("网络错误....");
		}
	});
}

// 异步注册
function reg_ajax() {
	// 有效性验证
	var username = $("#username").val();
	var phone_code =  $("#phone-code").val();
	var password = $("#password").val();
	var repeat_password = $("#repeat-password").val();
	
	if (isNull(username)) {
		return;
	}
	var isPhone = isPhone(username);
	var isEmail = isEmail(username);
	
	if (!isPhone || !isEmail) {
		return;
	}
	if (isPhone) {
		if (isNull(phone_code)) {
			return;
		}
	}
	if (isNull(password)) {
		return;
	}
	var isPassword = isPassword(password);
	if (!isPassword) {
		return;
	}
	
	var token = $("#token").val();
	var checkAddress = $("#checkAddress").val();
	var sid = $("#sid").val();
	var cellMap = {};
	cellMap.token = token;
	cellMap.checkAddress = checkAddress;
	cellMap.sid = sid;
	$.ajax({
		url:"/captcha/index.do",
		data:cellMap,
		async:false,
		type:"post",
		dataType:"json",
		success:function(r){
			var result = r.RESULT;
			if (result == "success") {
				$.ajax({
					url:"/user/reg_ajax.do",
					data:$('#form').serialize(),
					type:"post",
					dataType:"json",
					success:function(r){
						var result = r.RESULT;
						if(result=="用户不存在" || result=="密码错误" || result=="用户失效" || result=="用户失效"){
							alert(result);
						}else if(result=="注册成功"){
							window.location.href="/user/home.do";
						}
					},
					error:function(r){
						alert("网络错误....");
					}
				});
			} else {
				alert("验证失败.");
			}
			
		},
		error:function(r){
			alert("网络错误....");
		}
	});
}

// QQ登录
function login_qq() {
	var url = window.location.href;
	url = url.replace(/&/g, "%26");
	window.open('/user/login_qq.do?url='+url,'new','height='+550+',,innerHeight='+550+',width='+600+',innerWidth='+600+',top='+200+',left='+200+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

// 微信登录
function login_weixin() {
	var url = window.location.href;
	url = url.replace(/&/g, "%26");
	window.open('/user/login_weixin.do?url='+url,'new','height='+550+',,innerHeight='+550+',width='+600+',innerWidth='+600+',top='+200+',left='+200+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

// 微博登录
function login_weibo() {
	var url = window.location.href;
	url = url.replace(/&/g, "%26");
	window.open('/user/login_weibo.do?url='+url,'new','height='+550+',,innerHeight='+550+',width='+600+',innerWidth='+600+',top='+200+',left='+200+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

// 判断是否登录
function isLogin() {
	var isLogin = false;
	$.ajax({
		url:"/user/isLogin.do",
		async:false,
		type:"post",
		dataType:"json",
		success:function(r){
			var result = r.RESULT;
			if(result=="1"){
				isLogin = true;
			}else if(result=="0"){
				isLogin = false;
			}
		},
		error:function(r){
			isLogin = false;
		}
	});
	return isLogin;
}

// 隐藏块
function toggle(elementId) {
	var display = $("#" + elementId).css("display");
	if (display === "block") {
		$("#" + elementId).fadeOut(500);
	} else {
		$("#" + elementId).fadeIn(500);
	}
}

// toast提示弹层
function toggleToast(textOrImg, content) {
	var toast = $(".toast").length;
	if (toast.length > 0) {
		var isDisplay = $(".toast").css("display");
		if (isDisplay == 'none' || isDisplay =='') {
			
		} else if (isDisplay == 'block') {
			
		}
	} else {
		if (textOrImg == 'text') {
			$(document).appendTo("<div class='toast'>" + content + "</div>");
		} else if (textOrImg == 'img') {
			$(document).appendTo("<div class='toast'><img src='/static/image/loading.gif'></div>");
		}
	}
}