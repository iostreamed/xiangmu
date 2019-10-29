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
      				<small>位置</small>：<small>通讯录管理</small>
      			</div>
			</div>
			<form action="${pageContext.request.contextPath }/address_addresslist.action" method="post">
				<div class="am-g">
					<div class="am-u-lg-2">
						<div class="am-input-group am-input-group-secondary am-form-group">
							<a
								href="${pageContext.request.contextPath}/address_addAddress.action"><span
								class="am-input-group-label">新增</span></a>
						</div>
					</div>
					<div class="am-u-lg-3">
				    <div class="am-input-group am-input-group-secondary am-form-group">
				      <span class="am-input-group-label">用户名</span>
				      <input type="text" name="name" id="name" placeholder="用户名/账号" value="" class="am-form-field"/>
				    </div>
				 </div>
				 <div class="am-u-lg-3">
				    <div class="am-input-group am-input-group-secondary am-form-group">
				      <span class="am-input-group-label">邮箱</span>
				      <input type="text" name="email" id="email" placeholder="email" value="" class="am-form-field"/>
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
								<th>账号</th>
								<th>性别</th>
								<th>移动电话</th>
								<th>邮箱</th>
								<th>qq</th>
								<th>地址</th>
								<th>邮政编码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#session.addresslist.list" var="address">
								<tr>
									<td><s:property value="#address.id"/></td>
									<td><s:property value="#address.name"/></td>
									<td><s:property value="#address.sex"/></td>
									<td><s:property value="#address.mobile"/></td>
									<td><s:property value="#address.email"/></td>
									<td><s:property value="#address.qq"/></td>
									<td><s:property value="#address.address"/></td>
									<td><s:property value="#address.postcode"/></td>
									<td>
										<div class="am-dropdown" data-am-dropdown>
											<button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
											<ul class="am-dropdown-content">
												<li><a href="${pageContext.request.contextPath}/address_updateAddress.action?id=<s:property value="#address.id"/>" class="modifyAddress">编辑</a></li>
												<li><a href="${pageContext.request.contextPath}/address_deleteAddress.action?id=<s:property value="#address.id"/>" onclick='return confirm("是否删除联系人？")' class="deleteAddress">删除</a></li>
											</ul>
										</div>
									</td>
								</tr>
							</s:iterator>
						</tbody>				
					</table>
					<div class="am-cf">
    				<s:if test="#session.addresslist.page==0">当前 1/<s:property value="#session.addresslist.totalPage"/>
    					页,总 <s:property value="#session.addresslist.totalCount"/> 条记录
    				</s:if>
    				<s:else>
						当前 <s:property value="#session.addresslist.page"/>/<s:property value="#session.addresslist.totalPage"/>
						页,总 <s:property value="#session.addresslist.totalCount"/> 条记录
					</s:else>
					<div class="am-fr">
						<ul class="am-pagination ">
							<li><a
							href="${pageContext.request.contextPath}/address_addresslist.action?page=1">首页</a></li><s:if test="#session.addresslist.page != 1">
							<li><a href='${pageContext.request.contextPath}/address_addresslist.action?page=<s:property value="#session.addresslist.page-1"/>'>&laquo;</a>
							</li>
						</s:if>
						
						<s:iterator var="i" begin="1" end="#session.addresslist.totalPage">
							<s:if test="#session.addresslist.page == #i">
								<li class="active"><a href="#"><s:property value="#i" /></a></li>
							</s:if>
							<s:if test="#session.addresslist.page != #i">
								<li><a
									href="${pageContext.request.contextPath}/address_addresslist.action?page=<s:property value="#i"/>"><s:property value="#i"/></a></li>
							</s:if>
						</s:iterator>
						<s:if test="#session.addresslist.page != #session.addresslist.totalPage">
							<li><a
								href="${pageContext.request.contextPath}/address_addresslist.action?page=<s:property value="#session.addresslist.page+1"/>">&raquo;</a>
							</li>
						</s:if>
						<li><a
							href="${pageContext.request.contextPath}/address_addresslist.action?page=<s:property value="#session.addresslist.totalPage"/>">末页</a></li>
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
