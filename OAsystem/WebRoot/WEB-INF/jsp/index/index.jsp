<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
 <%@include file="/WEB-INF/jsp/public/header.jspf" %>
</head>
<body>
	
<%@include file="/WEB-INF/jsp/index/header.jsp" %>

<%@include file="/WEB-INF/jsp/public/tools.jsp" %>

<div class="am-cf admin-main">
  <!-- sidebar start -->
	<%@include file="/WEB-INF/jsp/index/menu.jsp" %>
  <!-- sidebar end -->
  
  <!-- content start -->
    <div class="admin-content">
    <div class="am-g">
      <div class="am-u-sm-12">
        <h2 class="am-text-center am-text-xxxl am-margin-top-lg">welcome access system</h2>
        <p class="am-text-center am-text-xxxl">${user.userName }</p>
        <p class="am-text-center am-text-xxxl"><jsp:useBean id="now" class="java.util.Date" scope="page"/>
				<fmt:formatDate value="${now}" pattern="yyyy年MM月dd日" /></p>
       
      </div>
    </div>
  </div>
  </div>
  <script type="text/javascript">	
			$(function() {
				if (location.href.indexOf("#reloaded") == -1) {
					location.href = location.href + "#reloaded";
					location.reload(true);
				}
				$.post('${pageContext.request.contextPath}/notice_findByLeast.action');
				$.post('${pageContext.request.contextPath}/sms_noReadCount.action');
			});
	</script>		
</body>
</html>

