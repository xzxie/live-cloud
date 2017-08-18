<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="/jsp/admin/meta.jsp" %>
  </head>
  
  <body>
  	<div class="container">
	  	<%@include file="/jsp/admin/top.jsp" %>
	    <div class="body-container">
	    	<jsp:include page="/jsp/admin/navi.jsp"></jsp:include>
	    	<div class="content-container">
	    		<div class="title">
	    			<span id="_dir_description_"></span> >> <span id="_file_description_"></span>
	    		</div>
	    		<div class="body">
	    			<table class="query-table">
	    			<form method="get" action="/admin/statistics.do" id="form">
	    				<input type="hidden" class="input" name="file_id" value="${file_id }" id="file_id" />
	    				<tr>
	    					<td>
	    						<div id="chart1" style="height: 400px;"></div>
	    					</td>
	    				</tr>
	    				</form>
	    			</table>
	    		</div>
	    	</div>
	    </div>
    </div>
  </body>
  <%@include file="/jsp/admin/admincommonjs.jsp" %>
  <script type="text/javascript" src="/static/js/echarts-3.5.4/echarts.js"></script>
  <script type="text/javascript">
  		// 基于准备好的dom，初始化echarts图表
        var myChart1 = echarts.init(document.getElementById('chart1'));
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['套餐']
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["1月","2月","3月","4月","5月","6月"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"套餐",
                    "type":"bar",
                    "data":[5, 20, 40, 10, 10, 20]
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart1.setOption(option);
  </script>
</html>