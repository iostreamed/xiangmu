<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">客户信息管理系统 </a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">
				<c:if test="${user.image==null }">
					<img src="${pageContext.request.contextPath}/image/profile1.jpg" alt="" class="img-circle" style="display: inline-block ; width: 35px; height: 35px"/>
				</c:if><c:if test="${user.image != null }">
					<img src="${pageContext.request.contextPath}/image/upload/${user.image}" alt="" class="img-circle" style="display: inline-block ; width: 35px; height: 35px"/>
				</c:if>
					${sessionScope.user.nickname}
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="${pageContext.request.contextPath }/user/person.action"><i class="fa fa-user fa-fw"></i> 用户设置</a></li>
					<li><a href="#" data-toggle="modal" data-target="#rePasswordDialog"><i class="fa fa-user fa-fw"></i>修改密码</a></li>
					<li class="divider"></li>
					<li><a href="/Crm/user/gologin.action"><i class="fa fa-sign-out fa-fw"></i>
							退出登录</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
	

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="查询内容...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search" style="padding: 3px 0 3px 0;"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<li><a href="${pageContext.request.contextPath}/customer/statistics.action"><i
							class="fa fa-dashboard fa-fw"></i> 首页</a></li>
					<li><a href="${pageContext.request.contextPath}/customer/list.action" class="active"><i
							class="fa fa-edit fa-fw"></i> 客户管理</a></li>
					<li><a href="${pageContext.request.contextPath}/linkMan/list.action"><i class="fa fa-edit fa-fw"></i>联系人管理</a></li>
					<li><a href="${pageContext.request.contextPath}/visit/list.action"><i class="fa fa-edit fa-fw"></i>拜访客户记录</a></li>
					<c:if test="${user.userrole=='系统管理员'}">
						<li><a href="${pageContext.request.contextPath}/user/list.action"><i class="fa fa-edit fa-fw"></i>用户管理</a></li>
					</c:if>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>
	<div class="modal fade" id="rePasswordDialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" >
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改密码</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="${pageContext.request.contextPath}/user/repassword.action" method="post">
						<input type="hidden" id="userid" name="userid"/>
						<div class="form-group">
								<label class="col-sm-2 control-label">原密码</label>
								<div class="col-sm-10">
									<input type="password" readonly="readonly" class="form-control" id="oldpassword" value="${user.userpassword }" placeholder="原密码" name="oldpassword">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">新密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="newpassword" placeholder="新密码" name="newpassword">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">重复新密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="renewpassword" placeholder="重复新密码" name="renewpassword">
								</div>
							</div>	
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button type="submit" class="btn btn-primary" onclick="updatepassword()">修改</button>
							</div>			
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function updatepassword(){
		var newpassword = $("#newpassword").val();
		var renewPassword = $("#renewpassword").val();
		if(newpassword == renewPassword){
			alert("修改成功");
			window.location.reload();
			return true;
		}else{
			alert("两次输入的新密码不一致，修改失败！");
			window.location.reload();
			return false;
		}
	}
	</script>