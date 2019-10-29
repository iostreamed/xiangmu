<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/WEB-INF/jsp/public/header.jspf"%>
    <script type="text/javascript">
    	$(function(){
    		$.post('${pageContext.request.contextPath}/sms_noReadCount.action');
    	});
    </script>
  </head>
  
  <body>
 	<%@include file="/WEB-INF/jsp/index/header.jsp"%>
	<%@include file="/WEB-INF/jsp/public/tools.jsp" %>
	<div class="am-cf admin-main">
		<%@include file="/WEB-INF/jsp/index/menu.jsp"%>
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
      				<small>位置</small>：<small>消息管理</small>
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
									<th>发送人</th>
									<th>消息</th>
									<th>发送时间</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#session.smslist.list" var="sms">
									<tr>
										<td><s:property value="#sms.id"/></td>
										<td><s:property value="#sms.userName"/></td>
										<td><s:property value="#sms.sender"/></td>
										<td><s:property value="#sms.message"/></td>
										<td><s:date name="#sms.sendTime" format="yyyy-MM-dd hh:mm:ss"/></td>
										<td>
											<s:if test="#sms.isRead==1">
												未读
											</s:if><s:else>
												已读
											</s:else>
										</td>
										<td>
											<div class="am-dropdown" data-am-dropdown>
											<button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
											<ul class="am-dropdown-content">
												<li>
													<s:if test="#sms.isRead==1">
														<a href="${pageContext.request.contextPath}/sms_updateIsRead.action?id=<s:property value="#sms.id"/>" class="statusIsRead">已读</a>
													</s:if>
												</li>
												<li>
													<s:if test="#sms.isRead==1">
														<a href="#" onclick='return confirm("消息未读，无法删除")' class="deleteSms">删除</a>
													</s:if>
													<s:else>
														<a href="${pageContext.request.contextPath}/sms_deleteSms.action?id=<s:property value="#sms.id"/>" onclick='return confirm("是否删除消息？")' class="deleteSms">删除</a>
													</s:else>
												</li>
											</ul>
										</div>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="am-cf">
							<s:if test="#session.smslist.page==0">当前 1/<s:property
									value="#session.smslist.totalPage" />
    					页,总 <s:property value="#session.smslist.totalCount" /> 条记录
    				</s:if>
							<s:else>
						当前 <s:property value="#session.smslist.page" />/<s:property
									value="#session.smslist.totalPage" />
						页,总 <s:property value="#session.smslist.totalCount" /> 条记录
					</s:else>
							<div class="am-fr">
								<ul class="am-pagination ">
									<li><a
										href="${pageContext.request.contextPath}/sms_smslist.action?page=1">首页</a></li>
									<s:if test="#session.smslist.page != 1">
										<li><a
											href='${pageContext.request.contextPath}/sms_smslist.action?page=<s:property value="#session.smslist.page-1"/>'>&laquo;</a>
										</li>
									</s:if>

									<s:iterator var="i" begin="1" end="#session.smslist.totalPage">
										<s:if test="#session.smslist.page == #i">
											<li class="active"><a href="#"><s:property
														value="#i" /></a></li>
										</s:if>
										<s:if test="#session.smslist.page != #i">
											<li><a
												href="${pageContext.request.contextPath}/smslist_smslist.action?page=<s:property value="#i"/>"><s:property
														value="#i" /></a></li>
										</s:if>
									</s:iterator>
									<s:if
										test="#session.smslist.page != #session.smslist.totalPage">
										<li><a
											href="${pageContext.request.contextPath}/smslist_smslist.action?page=<s:property value="#session.smslist.page+1"/>">&raquo;</a>
										</li>
									</s:if>
									<li><a
										href="${pageContext.request.contextPath}/sms_smslist.action?page=<s:property value="#session.smslist.totalPage"/>">末页</a></li>
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
