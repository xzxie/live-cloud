var options = {

    /*
     * 验证成功以后的回调函数
     * 需要调用者实现这个函数，并将参数 token,checkAddress,sid 传到服务器端
     *
     * @必须参数
     */
    onSuccess: function( obj ) {
        /* 
         * 点触 SDK会对 obj 赋值，obj 共有3个属性值分别是 token/checkAddress/sid 
         * 均为字符串类型 ,请开发者将 3个属性值传输到后端服务，以进行二次验证
         */
    },

    /*
     * 表单是否填写完成的校验函数
     * 
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     */
    readyCheck: function(){
        /*
         * 若用户名或其他必须字段没有填写，可返回如下对象
         * return {status: false, errorMsg: "表单未完成的提示信息"}
         */

        /*
         * 若需正常进行点触校验，则返回 {status: true, ckCode:''} ，ckCode用法请咨询点触技术服务,底部有联系方式
         * return {status: true, ckCode:''}
         */
    },

    /*
     * 需要监控键盘行为的输入框 id 
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     */
    behaviorDom: '',

    /*
     * 验证失败以后的回调函数
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     */
    onFailure:function(){
        /*
         * 验证码验证失败事件。
         * 可以写入验证失败后的提示信息，提高用户体验
         *
         */
    },

    /*
     * 关闭验证码事件
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     */
    onClose:function(){
        /*
         * 点击关闭按钮关闭验证码事件
         *
         */
    },

    /*
     * 点击验证码图案事件
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     */
    onClick:function(){
        /*
         * 验证码图案每一次点击的回调函数
         *
         */
    },

    /*
     * 自定义点击图案
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     */
    hoverImg:"url",

    /*
     * 自定义验证失败后显示的图案
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     */
    failureImg:"url",

    /*
     * 设置验证码遮罩层
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     *
     */
     isOpenMask:function(env){
        /*
         * 设置验证码的遮罩层
         *  return true;  设置遮罩层
         *  return false; 取消遮罩层
         *  默认 return false;
         *
         *  可以使用官方提供的 env 参数
         *  mob:屏幕宽度小于530
         *  pc: 屏幕宽度大于530
         */
        if(env == "pc"){
            return true;    //屏幕大于530px时 return true 或者 false 进行设置遮罩层设置
        }else if(env=="mob"){
            return true;    //屏幕小于530px时 return true 或者 false 进行设置遮罩层设置
        }
     },

     /*
     * 点击遮罩层事件
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     *
     */
    onMaskClick:function(e){
        /*
         *  如果有遮罩，用户点击遮罩触发的事件
         *  建议用法：可以利用该事件 注册 关闭验证码的 动作
         */
    },

    /*
     * 设置验证码位置
     *
     * @非必须参数,具体使用可联系点触技术服务,底部有联系方式
     *
     */
    isCaptchaFloat:function(env){
        /*
         * 设置验证码的显示位置。
         * 建议用法：配合env参数使用。 
         *
         */
    }
}

// 页面初始化赋值
TouClick.ready(function(){
    /*
     * @param 嵌入点ID
     * @param 配置参数
     */
    TouClick('captcha-target',{
        onSuccess : function(obj){
            $("#token").val(obj.token);
            $("#checkAddress").val(obj.checkAddress);
            $("#sid").val(obj.sid);
        }
    });
});

// 验证
function captcha() {
	var success = false;
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
				success = true; 
			} else {
				success = false;
			}
		},
		error:function(r){
			success = false;
		}
	});
	return success;
}