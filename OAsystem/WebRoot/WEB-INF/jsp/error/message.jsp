<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
 <%@include file="/WEB-INF/jsp/public/header.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/index/header.jsp" %>
<div class="am-cf admin-main">
  <!-- sidebar start -->
	<%@include file="/WEB-INF/jsp/index/menu.jsp" %>
  <!-- sidebar end -->

<!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">ERROR</strong> / <small>That’s an error</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <h2 class="am-text-center am-text-xxxl am-margin-top-lg">ERROR. Not Found</h2>
        <p class="am-text-center">您还未登录系统</p>
        <p class="am-text-center"> <a href="${ pageContext.request.contextPath }/user_login.action">>>去登录</a></p>
        <pre class="page-404"> 
        </pre>
      </div>
    </div>
  </div>
  <!-- content end -->
</div>
</body>
</html>
