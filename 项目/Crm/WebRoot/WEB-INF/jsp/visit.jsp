<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="itcast" uri="http://itcast.cn/common/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    
    <title>拜访客户记录</title>
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
					<h1 class="page-header">拜访客户记录</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-inline" action="${pageContext.request.contextPath }/visit/list.action" method="get">
						<div class="form-group">
							<label for="customerName">拜访客户名称</label> 
							<input type="text" class="form-control" id="visit_interviewer" value="${visit_interviewer }" name="visit_interviewer">
						</div>
						<button type="submit" class="btn btn-primary">查询</button>
					</form>
				</div>
			</div>
			<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#visitAddDialog" >新增</a>
			<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">拜访记录列表</div>
							<table class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>ID</th>
										<th>负责人</th>
										<th>拜访时间</th>
										<th>拜访客户</th>
										<th>拜访地点</th>
										<th>拜访详情</th>
										<th>下次拜访时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.rows}" var="row">
									<c:if test="${user.userid==row.user_id }">
										<tr>
											<td>${row.visit_id}</td>
											<td>${user.nickname }</td>
											<td><fmt:formatDate value="${row.visit_time}" pattern="YYYY-MM-dd" /></td>
											<td>${row.visit_interviewer}</td>
											<td>${row.visit_address}</td>
											<td>${row.visit_detail}</td>
											<td><fmt:formatDate value="${row.visit_nexttime}" pattern="YYYY-MM-dd" /></td>
											<td>
												<a href="javascript:;" class="btn btn-primary btn-xs" onclick="deleteVisit(${row.visit_id})">删除</a>							
											</td>
										</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							<div class="col-md-12 text-right">
								<itcast:page url="${pageContext.request.contextPath }/visit/list.action" />
							</div>
						</div>
					</div>
				</div>
    	</div>
    </div>
    <div class="modal fade" id="visitAddDialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加拜访记录</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="add_visit_form">
						<input type="hidden" id="edit_visit_id" name="visit_id"/>
						<input type="hidden" id="edit_user_id" value="${user.userid }" name="user_id"/>
						<div class="form-group">
							<label for="edit_visittime" class="col-sm-2 control-label">拜访时间</label>
							<div class="col-sm-10">
								<input type="text"   class="Wdate form-control" id="edit_visittime" readonly="readonly" onclick="WdatePicker();"  placeholder="拜访时间" name="visit_time">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_visitinterviewer" class="col-sm-2 control-label">拜访客户</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_visitinterviewer" placeholder="拜访客户" name="visit_interviewer">
							</div>
						</div>
							<div class="form-group">
								<label for="edit_linkPhone" class="col-sm-2 control-label">拜访地点</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="edit_visitaddress" placeholder="拜访地点" name="visit_address">
								</div>
							</div>
							<div class="form-group">
								<label for="edit_linkMobile" class="col-sm-2 control-label">拜访详情</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="edit_detail" placeholder="拜访详情" name="visit_detail">
								</div>
							</div>
							<div class="form-group">
							<label for="edit_visittime" class="col-sm-2 control-label">下次拜访时间</label>
							<div class="col-sm-10">
								<input type="text" class="Wdate form-control" id="edit_visitnexttime" readonly="readonly" onclick="WdatePicker();" placeholder="下次拜访时间" name="visit_nexttime">
							</div>
						</div>					
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary" onclick="addVisit()">保存</button>
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
	<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function addVisit(){
			$.post("<%=basePath%>visit/insert.action",$("#add_visit_form").serialize(),function(data){
				alert("拜访记录添加添加成功！");
				window.location.reload();
			});
		}
		function deleteVisit(id){
			if(confirm("确定要删除拜访记录吗？")){
				$.post("<%=basePath%>visit/delete.action",{"id":id},function(data){
					alert("记录删除更新成功！");
					window.location.reload();
					});
			}
		}	
	</script>
  </body>
</html>
