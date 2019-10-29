<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户设置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
    	<%@include file="/WEB-INF/jsp/main.jsp" %>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">用户设置</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">个人资料</div>
						<!-- /.panel-heading -->
						<c:if test="${user.image == null }">
							<img src="${pageContext.request.contextPath}/image/profile1.jpg" alt="" class="img-circle" style="display: inline-block ; width: 60px; height: 60px"/>
						</c:if>
						<c:if test="${user.image !=null }">
							<img src="${pageContext.request.contextPath}/image/upload/${user.image}" alt="" class="img-circle" style="display: inline-block ; width: 60px; height: 60px"/>
						</c:if>
						<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#ImageAddDialog">点击更换头像</a>${errorInfo}
						<table class="table table-bordered table-striped">
							<thead>
								<tr><td>ID</td><td>${user.userid }</td></tr>
								<tr><td>用户名</td><td>${user.username }</td></tr>
								<tr><td>昵称</td><td>${user.nickname }</td></tr>
								<tr><td>职位</td><td>${user.userrole }</td></tr>
							</thead>
						</table>
						<div class="modal-footer">
							<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#userEditDialog">修改资料</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="modal fade" id="userEditDialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
			<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改个人资料</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="${pageContext.request.contextPath}/user/update.action" method="get">
						<input type="hidden" id="edit_user_id" name="userid"/>
						<div class="form-group">
							<label for="edit_userName" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" value="${user.username }" placeholder="用户名称" name="username">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_userphone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" value="${user.nickname }" placeholder="昵称" name="nickname">
							</div>
						</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary" onclick="updateUser()">保存修改</button>
					</div>
				</form>					
			</div>
		</div>
    </div>
	</div>
	 <div class="modal fade" id="ImageAddDialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">上传头像</h4>
				</div>
				<div class="modal-body"> 
					<form class="form-horizontal" action="${pageContext.request.contextPath}/user/uploadImage.action" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="edit_address" class="col-sm-2 control-label">请选择:</label>
							<div class="col-sm-10">
								<input type="file" class="form-control" name="file" id="file"/>
							</div>
						</div>							
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary" onclick="upload()">上传</button>
						</div>	
					</form>
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
		function upload(){
			var fileDir = $("#file").val();
			var suffix = fileDir.substr(fileDir.lastIndexOf("."));
			if("" == fileDir){
				alert("请选择图片");
				return false;
			}
			if(".jpg" != suffix && ".jpeg" != suffix && ".png" != suffix && ".pneg" != suffix){
				alert("图片格式不正确,上传失败！");
				return false;
			}else{
				alert("上传成功！");
				window.location.reload();
				return true;
			}
		}
	</script>
  </body>
</html>
