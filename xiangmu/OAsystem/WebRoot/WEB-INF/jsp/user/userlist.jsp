<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="/WEB-INF/jsp/public/header.jspf"%>
	<script type="text/javascript">
	</script>
  </head>
  
  <body>
   	<%@include file="/WEB-INF/jsp/index/header.jsp"%>
	<%@include file="/WEB-INF/jsp/public/tools.jsp" %>
	<div class="am-cf admin-main">
	<!-- sidebar start -->
		<%@include file="/WEB-INF/jsp/index/menu.jsp"%>
	<!-- sidebar end -->
	
		<!-- content start -->
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
      				<small>位置</small>：<small>用户管理</small>
      			</div>
			</div>
			<form action="${pageContext.request.contextPath}/user_userlist.action" method="post">
			<div class="am-g">
   				<div class="am-u-lg-2">
				    <div class="am-input-group am-input-group-secondary am-form-group">
				      <a href="${pageContext.request.contextPath}/user_addUser.action"><span class="am-input-group-label">新增</span></a>
				    </div>
				 </div>		
				<div class="am-u-lg-3">
				    <div class="am-input-group am-input-group-secondary am-form-group">
				      <span class="am-input-group-label">用户名</span>
				      <input type="text" name="userName" id="userName" placeholder="用户名/账号" value="" class="am-form-field"/>
				    </div>
				 </div>
				 <div class="am-u-lg-3">
				    <div class="am-input-group am-input-group-secondary am-form-group">
				      <span class="am-input-group-label">用户状态</span>
				      <select name="userStatus"  class="am-form-field">
			            <option value="">--请选择--</option>
		                <option value="1" <s:if test="userStatus==1">selected="selected"</s:if>>
		              	 	 已启用
		                </option>
		                <option value="2" <s:if test="userStatus==2">selected="selected"</s:if>>
		              	 	 已停用
		                </option>
			          </select>
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
				<form action="${pageContext.request.contextPath}/user_userlist.action" method="post" id="form1" class="am-form">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-table-hover table-main am-text-nowrap">
							<thead>
								<tr>
									<th>编号</th>
									<th>用户账号</th>
									<th>用户昵称</th>
									<th>用户类型</th>
									<th>用户状态</th>
									<th>上次登录时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#session.userlist.list" var="user">
								<tr>
									<td><s:property value="#user.id"/></td>
									<td><s:property value="#user.userName"/></td>
									<td><s:property value="#user.nikeName"/></td>
									<td>
											<s:if test="#user.userType==1">
												系统管理员
											</s:if>
											<s:elseif test="#user.userType==2">
												经理
											</s:elseif><s:else>
												普通用户
											</s:else>
									</td>
									<td>
											<s:if test="#user.userStatus==1">
												已启用
											</s:if><s:else>
												已停用
											</s:else>
									</td>
									<td><s:date name="#user.loginTime" format="yyyy-MM-dd HH:mm:ss"/></td>							
									<td>
										<div class="am-dropdown" data-am-dropdown>
											<button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
											<ul class="am-dropdown-content">
												<li><a href="${pageContext.request.contextPath}/user_updateStatus.action?id=<s:property value="#user.id"/>" class="statusUser">													
														<s:if test="#user.userStatus==1">
																	 停用
														</s:if><s:else>
																	 启用
														</s:else>
												</a></li>
												<li>
													<s:if test="#session.user.userName==#user.userName">
														<a href="${pageContext.request.contextPath}/user_updateUser.action?id=<s:property value="#user.id"/>" class="modifyUser">编辑</a>
													</s:if><s:else>
														<a href="#" onclick='return confirm("非本人操作，无法修改！")' class="modifyUser">编辑</a>
													</s:else>
												</li>
												<li>
													<s:if test="#session.user.userType==1">
														<a href="${pageContext.request.contextPath}/user_deleteUser.action?id=<s:property value="#user.id"/>" onclick='return confirm("是否删除用户？")' class="deleteUser">删除</a>
													</s:if><s:else>
														<a href="#" onclick='return confirm("权限不足，删除失败！")' class="deleteUser">删除</a>
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
    				<s:if test="#session.userlist.page==0">当前 1/<s:property value="#session.userlist.totalPage"/>
    					页,总 <s:property value="#session.userlist.totalCount"/> 条记录
    				</s:if>
    				<s:else>
						当前 <s:property value="#session.userlist.page"/>/<s:property value="#session.userlist.totalPage"/>
						页,总 <s:property value="#session.userlist.totalCount"/> 条记录
					</s:else>
					<div class="am-fr">
						<ul class="am-pagination ">
							<li><a
							href="${pageContext.request.contextPath}/user_userlist.action?page=1">首页</a></li><s:if test="#session.userlist.page != 1">
							<li><a href='${pageContext.request.contextPath}/user_userlist.action?page=<s:property value="#session.userlist.page-1"/>'>&laquo;</a>
							</li>
						</s:if>
						
						<s:iterator var="i" begin="1" end="#session.userlist.totalPage">
							<s:if test="#session.userlist.page == #i">
								<li class="active"><a href="#"><s:property value="#i" /></a></li>
							</s:if>
							<s:if test="#session.userlist.page != #i">
								<li><a
									href="${pageContext.request.contextPath}/user_userlist.action?page=<s:property value="#i"/>"><s:property value="#i"/></a></li>
							</s:if>
						</s:iterator>
						<s:if test="#session.userlist.page != #session.userlist.totalPage">
							<li><a
								href="${pageContext.request.contextPath}/user_userlist.action?page=<s:property value="#session.userlist.page+1"/>">&raquo;</a>
							</li>
						</s:if>
						<li><a
							href="${pageContext.request.contextPath}/user_userlist.action?page=<s:property value="#session.userlist.totalPage"/>">末页</a></li>
						</ul>
					</div>	
   				</div>
   				</div>
			</form>
				</div>
			</div>
		</div>	
	</div>
  </body>
</html>

