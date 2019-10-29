<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="/WEB-INF/jsp/public/header.jspf"%>   
  </head>
  
  <body>
  	<%@include file="/WEB-INF/jsp/index/header.jsp"%>
	<%@include file="/WEB-INF/jsp/public/tools.jsp" %>
	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<%@include file="/WEB-INF/jsp/index/menu.jsp"%>
		<!-- sidebar end -->
		<div class="admin-content">
			<ol class="am-breadcrumb">
			  <li>位置：用户管理</li>
			  <li class="am-active">添加用户</li>
			</ol>
			<form action="${pageContext.request.contextPath}/user_saveUser.action" method="post">
				<div class="am-tab-panel am-fade am-in am-active" id="tab2">
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">用户账号</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="userName"
										data-validate="required:用户账号不能为空,username:格式错误" placeholder="用户账号">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">用户密码</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="password" id="password"
										data-validate="required:用户密码不能为空" placeholder="用户密码">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">重复密码</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="rePassword"
										data-validate="required:用户密码不能为空,repeat#password:两次密码输入不一致"
										placeholder="重复密码">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>


					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">用户类型</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<select name="userType">
										<option value="0">--选择用户类型--</option>
										<option value="1"
											<s:if test="userType==1">selected="selected"</s:if>>
											系统管理员</option>
										<option value="2"
											<s:if test="userType==2">selected="selected"</s:if>>
											经理</option>
										<option value="3"
											<s:if test="userType==3">selected="selected"</s:if>>
											普通员工</option>
									</select>
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">用户状态</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<select name="userStatus">
										<option value="0">--选择用户状态--</option>
										<option value="1"
											<s:if test="userStatus==1">selected="selected"</s:if>>
											启用</option>
										<option value="2"
											<s:if test="userStatus==2">selected="selected"</s:if>>
											停用</option>
									</select>
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>


					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">用户昵称</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="nikeName"
										data-validate="required:用户昵称不能为空" placeholder="用户昵称">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
				</div>
				<s:actionerror/>
				<div class="am-margin">
					<input type="submit" class="am-btn am-btn-primary" value="添加"/>
				</div>
			</form>
		</div>		
	</div>
</body>
</html>
