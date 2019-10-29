<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>OA办公</strong> <small>后台管理系统</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
    <li>
    	<s:if test="#session.noReadCount==0">
    		<a href="${pageContext.request.contextPath}/sms_smslist.action" id="tz"><span class="am-icon-envelope-o"></span> 未读消息<span class="am-badge am-badge-warning"></span></a>
    	</s:if><s:else>
    		<a href="${pageContext.request.contextPath}/sms_smslist.action" id="tz"><span class="am-icon-envelope-o"></span> 未读消息<span class="am-badge am-badge-warning"><s:property value="#session.noReadCount"/></span></a>
    	</s:else>
    </li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-user"></span> ${sessionScope.user.nikeName } <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="${pageContext.request.contextPath}/user_personal"><span class="am-icon-user"></span> 账户信息</a></li>
          <li><a href="${pageContext.request.contextPath}/user_logout"><span class="am-icon-power-off"></span> 退出</a></li>
          <li><a href="${pageContext.request.contextPath}/user_resetPwd"><span class="am-icon-lock"></span> 修改密码 </a></li>
        </ul>
      </li>
    </ul>
  </div>
</header>