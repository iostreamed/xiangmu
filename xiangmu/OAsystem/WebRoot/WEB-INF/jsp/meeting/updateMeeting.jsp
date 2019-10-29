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
			  <li>位置：会议管理</li>
			  <li class="am-active">修改会议</li>
			</ol>
			<form action="${pageContext.request.contextPath}/meeting_saveUpdate.action" method="post">
				<div class="am-tab-panel am-fade am-in am-active" id="tab2">
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">发送人</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="sender" value="${meeting.sender }"
										data-validate="required:用户账号不能为空,username:格式错误" placeholder="用户账号" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">开始时间</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="startTime" value="${meeting.startTime }"
										data-validate="required:时间不能为空" placeholder="开始时间" readonly="readonly" onclick="WdatePicker();">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">结束时间</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="endTime" value="${meeting.endTime }"
										data-validate="required:时间不能为空" placeholder="结束时间" readonly="readonly" onclick="WdatePicker();">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">地点</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="address" value="${meeting.address }"
										data-validate="required:开会地点不能为空" placeholder="地点">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">标题</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="title" value="${meeting.title }"
										data-validate="required:标题不能为空,chinese:请输入中文" placeholder="标题">
								</div>
							</div>
						</div>
						<div class="am-u-sm-3"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-2 am-text-right">内容</div>
						<div class="am-u-sm-7">
							<div class="am-form-group ">
								<div class="field">
									<input type="text" name="content" value="${meeting.content }"
										data-validate="required:内容不能为空,chinese:请输入中文" placeholder="内容">
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
