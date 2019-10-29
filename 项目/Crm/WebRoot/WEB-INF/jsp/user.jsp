<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="itcast" uri="http://itcast.cn/common/"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<meta name="description" content=""/>
	<meta name="author" content=""/>
    
    <title>用户管理</title>
    
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
    <div class="wrapper">
    	<%@include file="/WEB-INF/jsp/main.jsp"%>
    	<div id="page-wrapper">
    		<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">用户管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
    	<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">用户列表</div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>用户名</th>
								<th>昵称</th>
								<th>职位</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.rows}" var="row">
								<tr>
									<td>${row.userid}</td>
									<td>${row.username}</td>
									<td>${row.nickname}</td>
									<td>${row.userrole}</td>
									<td>
										<a href="javascript:;" class="btn btn-primary btn-xs" onclick="deleteUser(${row.userid})">删除</a>							
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="col-md-12 text-right">
						<itcast:page url="${pageContext.request.contextPath }/linkMan/list.action" />
					</div>
				</div>
			</div>
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
	<script type="text/javascript">
		function deleteUser(id){
			if(confirm("确定要删除用户吗？")){
				$.post("<%=basePath%>user/delete.action",{"id":id},function(data){
					alert("客户删除更新成功！");
					window.location.reload();
					});
			}
		}
	</script>
  </body>
</html>
