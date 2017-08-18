<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
	    			网站综合统计
	    		</div>
	    		<div class="body">
	    		
	    			<!-- 用户注册量、套餐购买量 -->
	    			<table class="result-table">
	    				<thead>
		    				<tr>
		    					<th>近一周注册用户量</th>
		    					<th>近二周注册用户量</th>
		    					<th>近一月注册用户量</th>
		    					<th>近一周购买套餐量</th>
		    					<th>近二周购买套餐量</th>
		    					<th>近一月购买套餐量</th>
		    				</tr>
	    				</thead>
	    				<tbody>
		    				<tr>
		    					<td>20</td>
		    					<td>30</td>
		    					<td>40</td>
		    					<td>10</td>
		    					<td>15</td>
		    					<td>20</td>
		    				</tr>
	    				</tbody>
	    			</table>
	    			
	    			<table class="result-table">
	    				<thead>
		    				<tr>
		    					<th>近一周注册用户量</th>
		    					<th>近二周注册用户量</th>
		    					<th>近一月注册用户量</th>
		    					<th>近一周购买套餐量</th>
		    					<th>近二周购买套餐量</th>
		    					<th>近一月购买套餐量</th>
		    				</tr>
	    				</thead>
	    				<tbody>
		    				<tr>
		    					<td>20</td>
		    					<td>30</td>
		    					<td>40</td>
		    					<td>10</td>
		    					<td>15</td>
		    					<td>20</td>
		    				</tr>
	    				</tbody>
	    			</table>
	    			
	    			<div id="chart" style="height: 400px;"></div>
	    			
	    		</div>
	    	</div>
	    </div>
    </div>
  </body>
  <%@include file="/jsp/admin/admincommonjs.jsp" %>
  <script type="text/javascript" src="/static/js/echarts-3.5.4/echarts.js"></script>
  <script type="text/javascript">
  		// 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('chart')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['销量']
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"销量",
                    "type":"bar",
                    "data":[5, 20, 40, 10, 10, 20]
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
  </script>
</html>
