<%@ page language="java" import="java.util.*,com.util.Constant,com.util.SeoUtil,com.util.WebUtil" pageEncoding="utf-8"%>
<meta http-equiv="keywords" content="<%=SeoUtil.keywords %>">
<meta http-equiv="description" content="<%=SeoUtil.description %>">
<meta content="text/html;charset=utf-8" http-equiv="Content-Type" />
<meta content="width=device-width" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta name="renderer" content="webkit" />
<meta name="baidu-site-verification" content="FjAui43P9Z" />
<meta name="google-site-verification" content="8d-cityJ7RidZvTGDDiDvA8uvyPT7QN0dcQwfvG-Ltg" />
<meta property="wb:webmaster" content="1b0880236a301a9c" />
<link rel="shortcut icon" href="<%=Constant.STATIC_PREFIX %>/static/image/favicon.ico?t=<%=Constant.VERSION %>" />
<link rel="stylesheet" type="text/css" href="<%=Constant.STATIC_PREFIX %>/static/css/common.css?t=<%=Constant.VERSION %>">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<%if(WebUtil.isLocal()){ %>
<script src="https://cdn.bootcss.com/vue/2.4.2/vue.js"></script>
<script src="https://cdn.bootcss.com/vuex/2.3.1/vuex.js"></script>
<script src="https://cdn.bootcss.com/vue-router/2.7.0/vue-router.js"></script>
<%} else { %>
<script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
<script src="https://cdn.bootcss.com/vuex/2.3.1/vuex.min.js"></script>
<script src="https://cdn.bootcss.com/vue-router/2.7.0/vue-router.min.js"></script>
<%} %>