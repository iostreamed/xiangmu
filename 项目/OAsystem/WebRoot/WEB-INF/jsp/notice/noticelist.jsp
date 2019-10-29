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
      				<small>位置</small>：<small>公告管理</small>
      			</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-table-hover table-main am-text-nowrap">
							<thead>
								<tr>
									<th>编号</th>
									<th>发送人</th>
									<th>公告标题</th>
									<th>公告内容</th>
									<th>发送时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#session.noticelist.list" var="notice">
									<tr>
										<td><s:property value="#notice.id"/></td>
										<td><s:property value="#notice.sender"/></td>
										<td><s:property value="#notice.title"/></td>
										<td><s:property value="#notice.content"/></td>
										<td><s:date name="#notice.sendTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<div class="am-dropdown" data-am-dropdown>
											<button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
											<ul class="am-dropdown-content">
												<li>
													<s:if test="#session.user.userName==#notice.userName">
														<a href="${pageContext.request.contextPath}/notice_updateNotice.action?id=<s:property value="#notice.id"/>" class="modifyNotice">编辑</a>
													</s:if><s:else>
														<a href="#" onclick='return confirm("非本人操作，无法修改！")' class="modifyNotice">编辑</a>
													</s:else>
												</li>
												<li>
													<s:if test="#session.user.userType==1">
														<a href="${pageContext.request.contextPath}/notice_deleteNotice.action?id=<s:property value="#notice.id"/>" onclick='return confirm("是否删除公告？")' class="deleteNotice">删除</a>
													</s:if><s:else>
														<a href="#" onclick='return confirm("权限不足，删除失败！")' class="deleteNotice">删除</a>
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
							<s:if test="#session.noticelist.page==0">当前 1/<s:property
									value="#session.noticelist.totalPage" />
    					页,总 <s:property value="#session.noticelist.totalCount" /> 条记录
    				</s:if>
							<s:else>
						当前 <s:property value="#session.noticelist.page" />/<s:property
									value="#session.noticelist.totalPage" />
						页,总 <s:property value="#session.noticelist.totalCount" /> 条记录
					</s:else>
							<div class="am-fr">
								<ul class="am-pagination ">
									<li><a
										href="${pageContext.request.contextPath}/notice_noticelist.action?page=1">首页</a></li>
									<s:if test="#session.noticelist.page != 1">
										<li><a
											href='${pageContext.request.contextPath}/notice_noticelist.action?page=<s:property value="#session.noticelist.page-1"/>'>&laquo;</a>
										</li>
									</s:if>

									<s:iterator var="i" begin="1" end="#session.noticelist.totalPage">
										<s:if test="#session.noticelist.page == #i">
											<li class="active"><a href="#"><s:property
														value="#i" /></a></li>
										</s:if>
										<s:if test="#session.noticelist.page != #i">
											<li><a
												href="${pageContext.request.contextPath}/notice_noticelist.action?page=<s:property value="#i"/>"><s:property
														value="#i" /></a></li>
										</s:if>
									</s:iterator>
									<s:if
										test="#session.noticelist.page != #session.noticelist.totalPage">
										<li><a
											href="${pageContext.request.contextPath}/notice_noticelist.action?page=<s:property value="#session.noticelist.page+1"/>">&raquo;</a>
										</li>
									</s:if>
									<li><a
										href="${pageContext.request.contextPath}/notice_noticelist.action?page=<s:property value="#session.noticelist.totalPage"/>">末页</a></li>
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
