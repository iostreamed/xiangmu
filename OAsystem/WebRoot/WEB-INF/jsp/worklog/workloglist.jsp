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
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
      				<small>位置</small>：<small>工作日志</small>
      			</div>
			</div>
				<div class="am-g">
					<div class="am-u-lg-2">
				    	<div class="am-input-group am-input-group-secondary am-form-group">
				      		<a href="${pageContext.request.contextPath}/worklog_addWorklog.action"><span class="am-input-group-label">新增</span></a>
				    	</div>
				 	</div>				 	
				</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-table-hover table-main am-text-nowrap">
							<thead>
								<tr>
									<th>编号</th>
									<th>用户名</th>
									<th>日志标题</th>
									<th>日志内容</th>
									<th>日志时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#session.workloglist.list" var="worklog">
									<tr>
										<td><s:property value="#worklog.id"/></td>
										<td><s:property value="#worklog.userName"/></td>
										<td><s:property value="#worklog.title"/></td>
										<td><s:property value="#worklog.description"/></td>
										<td><s:date name="#worklog.logTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<div class="am-dropdown" data-am-dropdown>
											<button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
											<ul class="am-dropdown-content">
												<li><a href="${pageContext.request.contextPath}/worklog_updateWorklog.action?id=<s:property value="#worklog.id"/>" class="modifyWorklog">编辑</a></li>
												<li><a href="${pageContext.request.contextPath}/worklog_deleteWorklog.action?id=<s:property value="#worklog.id"/>" onclick='return confirm("是否删除日志？")' class="deleteWorklog">删除</a></li>
											</ul>
										</div>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="am-cf">
							<s:if test="#session.workloglist.page==0">当前 1/<s:property
									value="#session.workloglist.totalPage" />
    					页,总 <s:property value="#session.workloglist.totalCount" /> 条记录
    				</s:if>
							<s:else>
						当前 <s:property value="#session.workloglist.page" />/<s:property
									value="#session.workloglist.totalPage" />
						页,总 <s:property value="#session.workloglist.totalCount" /> 条记录
					</s:else>
							<div class="am-fr">
								<ul class="am-pagination ">
									<li><a
										href="${pageContext.request.contextPath}/worklog_workloglist.action?page=1">首页</a></li>
									<s:if test="#session.workloglist.page != 1">
										<li><a
											href='${pageContext.request.contextPath}/worklog_workloglist.action?page=<s:property value="#session.workloglist.page-1"/>'>&laquo;</a>
										</li>
									</s:if>

									<s:iterator var="i" begin="1" end="#session.workloglist.totalPage">
										<s:if test="#session.workloglist.page == #i">
											<li class="active"><a href="#"><s:property
														value="#i" /></a></li>
										</s:if>
										<s:if test="#session.workloglist.page != #i">
											<li><a
												href="${pageContext.request.contextPath}/worklog_workloglist.action?page=<s:property value="#i"/>"><s:property
														value="#i" /></a></li>
										</s:if>
									</s:iterator>
									<s:if
										test="#session.workloglist.page != #session.workloglist.totalPage">
										<li><a
											href="${pageContext.request.contextPath}/worklog_workloglist.action?page=<s:property value="#session.workloglist.page+1"/>">&raquo;</a>
										</li>
									</s:if>
									<li><a
										href="${pageContext.request.contextPath}/worklog_workloglist.action?page=<s:property value="#session.workloglist.totalPage"/>">末页</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
