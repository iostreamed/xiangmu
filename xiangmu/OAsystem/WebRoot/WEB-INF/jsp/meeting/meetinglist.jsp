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
      				<small>位置</small>：<small>会议管理</small>
      			</div>
			</div>
			<form action="${pageContext.request.contextPath}/meeting_meetinglist.action" method="post">
				<div class="am-g">
					<div class="am-u-lg-2">
				    	<div class="am-input-group am-input-group-secondary am-form-group">
				      		<a href="${pageContext.request.contextPath}/meeting_addMeeting.action"><span class="am-input-group-label">新增</span></a>
				    	</div>
				 	</div>
				 	<div class="am-u-lg-3">
				    <div class="am-input-group am-input-group-secondary am-form-group">
				      <span class="am-input-group-label">发送人</span>
				      <input type="text" name="sender" id="sender" placeholder="发送人" value="" class="am-form-field"/>
				    </div>	    
				 </div>
				 <div class="am-u-lg-1">
				    <div class="am-input-group ">
				      <input class="am-btn am-btn-primary" type="submit" value="搜索"/>
				      <input type="hidden" id="currentPage" name="currentPage" value="1" />
				    </div>
				 </div>
				</div>
			</form>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-table-hover table-main am-text-nowrap">
							<thead>
								<tr>
									<th>编号</th>
									<th>发送人</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>地点</th>
									<th>标题</th>
									<th>内容</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#session.meetinglist.list" var="meeting">
									<tr>
										<td><s:property value="#meeting.id"/></td>
										<td><s:property value="#meeting.sender"/></td>
										<td><s:date name="#meeting.startTime" format="yyyy-MM-dd"/></td>
										<td><s:date name="#meeting.endTime" format="yyyy-MM-dd"/></td>
										<td><s:property value="#meeting.address"/></td>
										<td><s:property value="#meeting.title"/></td>
										<td><s:property value="#meeting.content"/></td>
										<td>
											<div class="am-dropdown" data-am-dropdown>
											<button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
											<ul class="am-dropdown-content">
												<li><a href="${pageContext.request.contextPath}/meeting_updateMeeting.action?id=<s:property value="#meeting.id"/>" class="modifyMeeting">编辑</a></li>
												<li><a href="${pageContext.request.contextPath}/meeting_deleteMeeting.action?id=<s:property value="#meeting.id"/>" onclick='return confirm("是否删除会议？")' class="deleteMeeting">删除</a></li>
											</ul>
										</div>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="am-cf">
							<s:if test="#session.meetinglist.page==0">当前 1/<s:property
									value="#session.meetinglist.totalPage" />
    					页,总 <s:property value="#session.meetinglist.totalCount" /> 条记录
    				</s:if>
							<s:else>
						当前 <s:property value="#session.meetinglist.page" />/<s:property
									value="#session.meetinglist.totalPage" />
						页,总 <s:property value="#session.meetinglist.totalCount" /> 条记录
					</s:else>
							<div class="am-fr">
								<ul class="am-pagination ">
									<li><a
										href="${pageContext.request.contextPath}/meeting_meetinglist.action?page=1">首页</a></li>
									<s:if test="#session.meetinglist.page != 1">
										<li><a
											href='${pageContext.request.contextPath}/meeting_meetinglist.action?page=<s:property value="#session.meetinglist.page-1"/>'>&laquo;</a>
										</li>
									</s:if>

									<s:iterator var="i" begin="1" end="#session.meetinglist.totalPage">
										<s:if test="#session.meetinglist.page == #i">
											<li class="active"><a href="#"><s:property
														value="#i" /></a></li>
										</s:if>
										<s:if test="#session.meetinglist.page != #i">
											<li><a
												href="${pageContext.request.contextPath}/meeting_meetinglist.action?page=<s:property value="#i"/>"><s:property
														value="#i" /></a></li>
										</s:if>
									</s:iterator>
									<s:if
										test="#session.meetinglist.page != #session.meetinglist.totalPage">
										<li><a
											href="${pageContext.request.contextPath}/meeting_meetinglist.action?page=<s:property value="#session.meetinglist.page+1"/>">&raquo;</a>
										</li>
									</s:if>
									<li><a
										href="${pageContext.request.contextPath}/meeting_meetinglist.action?page=<s:property value="#session.meetinglist.totalPage"/>">末页</a></li>
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
