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
			  <li class="am-active">修改日程</li>
			</ol>
			<form action="${pageContext.request.contextPath}/schedule_saveUpdate.action" method="post">
				<div class="am-tab-panel am-fade am-in am-active" id="tab2">				
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">日程时间</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="time" value="${schedule.time }"
										data-validate="required:时间不能为空" placeholder="日程时间" readonly="readonly" onclick="WdatePicker();">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">计划</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="plan" value="${schedule.plan }"
										data-validate="required:计划不能为空" placeholder="计划">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<s:actionerror/>
					<div class="am-margin">
						<input type="submit" class="am-btn am-btn-primary" value="修改"/>
					</div>
				</div>
			</form>
		</div>
	</div>
  </body>
</html>
