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
		<%@include file="/WEB-INF/jsp/index/menu.jsp"%>
		<div class="admin-content">
			<ol class="am-breadcrumb">
			  <li>位置：通讯录管理</li>
			  <li class="am-active">修改通讯录</li>
			</ol>
			<form action="${pageContext.request.contextPath}/address_saveUpdate.action" method="post">
				<div class="am-tab-panel am-fade am-in am-active" id="tab2">
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">用户账号</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="name" value="${address.name }"
										data-validate="required:用户账号不能为空,username:格式错误" placeholder="用户账号">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">用户性别</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<select name="sex" >
										<option value="男">--选择用户性别--</option>
										<option value="男"
											<s:if test="sex==男">selected="selected"</s:if>>
											男</option>
										<option value="女"
											<s:if test="sex==女">selected="selected"</s:if>>
											女</option>
									</select>
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">移动电话</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="mobile" value="${address.mobile }"
										data-validate="required:电话号码不能为空,mobile:格式错误" placeholder="电话号码">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">邮箱</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="email" value="${address.email }"
										data-validate="required:emali账号不能为空,email:格式错误" placeholder="email">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">qq</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="qq" value="${address.qq }"
										data-validate="required:qq账号不能为空,qq:格式错误" placeholder="qq">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">地址</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="address" value="${address.address }"
										data-validate="required:用户地址不能为空" placeholder="用户地址">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">邮政编码</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="postcode" value="${address.postcode }"
										data-validate="required:邮政编码不能为空,postcode:格式错误" placeholder="邮政编码">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-margin">
						<input type="submit" class="am-btn am-btn-primary" value="修改"/>
					</div>
				</div>
			</form>
		</div>
	</div>
  </body>
</html>
