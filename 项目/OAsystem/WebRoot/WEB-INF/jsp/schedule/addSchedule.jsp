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
			  <li>位置：日程管理</li>
			  <li class="am-active">添加日程</li>
			</ol>
			<div class="am-g">
				<div class="am-u-lg-5 am-u-md-15 am-u-sm-centered">
					<form class="am-form m-form-horizontal"
						action="${pageContext.request.contextPath}/schedule_saveSchedule.action"
						method="post">
						<div class="am-g am-margin-top">
							<div class="am-u-sm-2 am-text-right">标题</div>
							<div class="am-u-sm-10">
								<div class="am-form-group ">
									<div class="field">
									<input type="text" name="time" data-validate="required:时间不能为空" readonly="readonly" onclick="WdatePicker();">
									</div>
								</div>
							</div>
							<div class="am-u-sm-3"></div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-2 am-text-right">计划内容</div>
							<div class="am-u-sm-10">
								<div class="am-form-group ">
									<div class="field">
									<textarea rows="10" cols="20" class="xheditor" data-validate="required:计划内容不能为空" id="plan" placeholder="计划内容" name="plan"></textarea>
									</div>
								</div>
							</div>
							<div class="am-u-sm-3"></div>
						</div>

						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-primary" value="添加" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/index/foot.jsp"%>
	<script src="${pageContext.request.contextPath}/plugins/xheditor/xheditor-1.2.2.min.js"></script>
  </body>
</html>
