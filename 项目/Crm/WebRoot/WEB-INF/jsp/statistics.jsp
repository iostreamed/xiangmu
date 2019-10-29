<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
  	<meta name="renderer" content="webkit">
  	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!-- Bootstrap Core CSS -->
	<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet"/>

	<!-- MetisMenu CSS -->
	<link href="<%=basePath%>css/metisMenu.min.css" rel="stylesheet"/>

	<!-- DataTables CSS -->
	<link href="<%=basePath%>css/dataTables.bootstrap.css" rel="stylesheet"/>

	<!-- Custom CSS -->
	<link href="<%=basePath%>css/sb-admin-2.css" rel="stylesheet"/>

	<!-- Custom Fonts -->
	<link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet"
		type="text/css"/>
	<link href="<%=basePath%>css/boot-crm.css" rel="stylesheet"
		type="text/css"/>

  </head>
  
  <body>
    <div id="wrapper">
    	<%@include file="/WEB-INF/jsp/main.jsp"%>
    	<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">图表统计</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div>
				<div id="main"
					style="height:450px;border:1px solid #ccc;padding:10px;float: left;width: 50%;"></div>
				<div id="mainMap"
					style="height:450px;border:1px solid #ccc;padding:10px;float: left;width: 50%;"></div>
			</div>
    </div>
    </div>
    <!-- jQuery -->
	<script src="<%=basePath%>js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath%>js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="<%=basePath%>js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
	<script src="<%=basePath%>js/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<%=basePath%>js/sb-admin-2.js"></script>
	<!-- eChart Javascript -->
	<script src="<%=basePath%>js/echarts/echarts.js"></script>
	<script type="text/javascript">
		var ctx = "${pageContext.request.contextPath}";
		require.config({
			paths : {
				echarts : ctx + "/js/echarts"
			}
		});
		require([ 'echarts', 'echarts/chart/pie','echarts/chart/bar' ], function(ec) {
			var datasource = "${map.datasource}";
			var datasource1 = "${map.datasource1}";
			var myChart = ec.init(document.getElementById('main'));
			var option = {
				title : {
					text : '客户来源比例',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					x : 'left',
					data : [ '电话营销', '网络营销' ]
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'pie', 'funnel' ],
							option : {
								funnel : {
									x : '25%',
									width : '50%',
									funnelAlign : 'left',
									max : 1548
								}
							}
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				series : [ {
					name : '客户来源比例',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : datasource,
						name : '电话营销'
					}, {
						value : datasource1,
						name : '网络营销'
					} ]
				} ]
			};
			myChart.setOption(option);
			var dataindustry = "${map.dataindustry}";
			var dataindustry1 = "${map.dataindustry1}";
			var dataindustry2 = "${map.dataindustry2}";
			var dataindustry3 = "${map.dataindustry3}";
			var dataindustry4 = "${map.dataindustry4}";
        	var myChart2 = ec.init(document.getElementById('mainMap'));
				var option2 = {
					title : {
						text : '客户行业',
						x : 'center'
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						x : 'left',
						data : [ '数量' ]
					},
					toolbox : {
						show : true,
						feature : {
							mark : {
								show : true
							},
							dataView : {
								show : true,
							readOnly : false
							},
							magicType : {
								show : true,
								type : [ 'line', 'bar' ]
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : true,
					xAxis : [ {
						type : 'category',
						data : [ '教育培训', '电子商户', '对外贸易', '酒店旅游', '房地产']
					} ],
					yAxis : [ {
						type : 'value'
					} ],
					series : [{
						name:'数量',
						type:'bar',
						data:[dataindustry,dataindustry1,dataindustry2,dataindustry3,dataindustry4]
					}]
								
											
					};
				myChart2.setOption(option2);
			});
	</script>
  </body>
</html>
