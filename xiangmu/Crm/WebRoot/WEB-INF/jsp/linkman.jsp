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
    <title>联系人管理</title>
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
					<h1 class="page-header">联系人管理</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-inline" action="${pageContext.request.contextPath }/linkMan/list.action" method="get">
						<div class="form-group">
							<label for="customerName">客户名称</label> 
							<input type="text" class="form-control" id="linkName" value="${linkName }" name="linkName">
						</div>
						<button type="submit" class="btn btn-primary">查询</button>
					</form>
				</div>
				</div>
				<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#linkManAddDialog" >新增</a>
				<a href="javascript:;" class="btn btn-danger" onclick="deleteLinkMan()">批量删除</a>
				<a href="${pageContext.request.contextPath}/linkMan/export.action" class="btn btn-primary">导出Excel</a>
				<a href="#" class="btn btn-primary" data-toggle="modal" data-target="#ExcelAddDialog">导入Excel</a>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">联系人列表</div>
							<table class="table table-bordered table-striped">
								<thead>
									<tr>
										<th width="25"><input type="checkbox" id="all" value="" onchange="ckAll()"/></th>
										<th>ID</th>
										<th>联系人名称</th>
										<th>所属客户</th>
										<th>联系人性别</th>
										<th>手机号码</th>
										<th>固定号码</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.rows}" var="row">
										<tr>
											<td><input type="checkbox" value="${row.link_id}" name="id"/></td>
											<td>${row.link_id}</td>
											<td>${row.link_name}</td>
											<td>${row.cust_name}</td>
											<td>${row.link_sex}</td>
											<td>${row.link_phone}</td>
											<td>${row.link_mobile}</td>
											<td>
												<a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#linkManEditDialog" onclick="editLinkMan(${row.link_id})">修改</a>							
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
  	<div class="modal fade" id="linkManEditDialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改联系人信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="edit_linkMan_form">
						<input type="hidden" id="edit_link_id" name="link_id"/>
						<div class="form-group">
							<label for="edit_LinkName" class="col-sm-2 control-label">联系人</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_linkname" placeholder="联系人名称" name="link_name">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_linkName" class="col-sm-2 control-label">所属客户</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_customerName" placeholder="所属客户" name="cust_name">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_linkName" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<select	class="form-control" id="edit_linksex" placeholder="联系人性别" name="link_sex">
									<option value="" selected="selected">--请选择--</option>
									<option value="男">男</option>
					   				<option value="女">女</option>
								</select>
							</div>
						</div>
							<div class="form-group">
								<label for="edit_linkPhone" class="col-sm-2 control-label">手机号码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="edit_linkphone" placeholder="手机号码" name="link_phone">
								</div>
							</div>
							<div class="form-group">
								<label for="edit_linkMobile" class="col-sm-2 control-label">固定电话</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="edit_linkmobile" placeholder="固定电话" name="link_mobile">
								</div>
							</div>						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary" onclick="updateLinkMan()">修改</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="linkManAddDialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加联系人信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="add_linkMan_form">
						<input type="hidden" id="edit_link_id" name="link_id"/>
						<div class="form-group">
							<label for="edit_LinkName" class="col-sm-2 control-label">联系人</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_linkname" placeholder="联系人名称" name="link_name">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_linkName" class="col-sm-2 control-label">所属客户</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_customerName" placeholder="所属客户" name="cust_name">
							</div>
						</div>
						<div class="form-group">
							<label for="edit_linkName" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<select	class="form-control" id="edit_linksex" placeholder="联系人性别" name="link_sex">
									<option value="" selected="selected">--请选择--</option>
									<option value="男">男</option>
					   				<option value="女">女</option>
								</select>
							</div>
						</div>
							<div class="form-group">
								<label for="edit_linkPhone" class="col-sm-2 control-label">手机号码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="edit_linkphone" placeholder="手机号码" name="link_phone">
								</div>
							</div>
							<div class="form-group">
								<label for="edit_linkMobile" class="col-sm-2 control-label">固定电话</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="edit_linkmobile" placeholder="固定电话" name="link_mobile">
								</div>
							</div>						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary" onclick="addLinkMan()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="ExcelAddDialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">上传Excel文件</h4>
				</div>
				<div class="modal-body"> 
					<form class="form-horizontal" action="${pageContext.request.contextPath}/linkMan/import.action" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="edit_address" class="col-sm-2 control-label">请选择:</label>
							<div class="col-sm-10">
								<input type="file" class="form-control" name="file" id="file"/>
							</div>
						</div>							
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary" onclick="importExcel()">上传</button>
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
		function editLinkMan(id) {
			$.ajax({
				type:"get",
				url:"<%=basePath%>linkMan/edit.action",
				data:{"id":id},
				success:function(data) {
					$("#edit_link_id").val(data.link_id);
					$("#edit_linkname").val(data.link_name);
					$("#edit_customerName").val(data.cust_name);
					$("#edit_linksex").val(data.link_sex);
					$("#edit_linkphone").val(data.link_phone);
					$("#edit_linkmobile").val(data.link_mobile);					
				}
			});
		}
		
		function updateLinkMan(){
			$.post("<%=basePath%>linkMan/update.action",$("#edit_linkMan_form").serialize(),function(data){
				alert("联系人信息更新成功！");
				window.location.reload();
			});
		}
		function addLinkMan(){
			$.post("<%=basePath%>linkMan/insert.action",$("#add_linkMan_form").serialize(),function(data){
				alert("联系人信息添加成功！");
				window.location.reload();
			});
		}	
		
		var oall = document.getElementById("all");
		var oid = document.getElementsByName("id");
		oall.onclick=function(){
			for(var i=0;i<oid.length;i++){
				//所有的选择框和全选一致
				oid[i].checked=oall.checked;
			}				
		};
		//点击复选框
		for (var i = 0; i < oid.length; i++) {
			oid[i].οnclick = function() {
				//判断是否全部选中,遍历集合
				for (var j = 0; j < oid.length; j++) {
					if (oid[j].checked == false) {
						oall.checked = false;
						break;
					} else {
						oall.checked = true;
					}
				}
			};
			}
			function deleteLinkMan() {
			var Checkbox = false;
			$("input[name='id']").each(function(){
				if(this.checked==true){
					Checkbox = true;
				}
			});
			if(Checkbox){
			if (confirm('确实要删除该客户吗?')) {
				//确认删除
				var ids = [];
				var n = 0;
				for(var i=0;i<oid.length;i++){
					if(oid[i].checked==true){//选中为true
						var id=oid[i].value;
					if(n==0){
					ids+=+id;
					}else{
						ids+=","+id;
					}
						n++;
					}			
				}
				$.post("<%=basePath%>linkMan/delete.action",{"ids":ids},function(data){
						window.location.reload();
						alert("客户删除更新成功！");		
				});
				return true;
			}else{			
				return false;
			}
			}else{
				alert("请选择要删除的数据");
			}
		}
		function importExcel(){
			var fileDir = $("#file").val();
			var suffix = fileDir.substr(fileDir.lastIndexOf("."));
			if("" == fileDir){
				alert("请选择Excel文件");
				return false;
			}
			if(".xls" != suffix && ".xlsx" != suffix){
				alert("文件格式不正确");
				return false;
			}
			alert("Excel上传成功！");
			window.location.reload();
			return true;		
		}
	</script>
  </body>
</html>
