<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	
 		<div class="page-bar">
			<ul class="page-num-ul clearfix">
				<li>共${page.totalCount }条记录&nbsp;&nbsp; ${page.currentPage }/${page.totalPage }页</li>
				<c:if test="${page.currentPage > 1}">
					<a href="javascript:page_nav(document.forms[0],1);">首页</a>
					<a href="javascript:page_nav(document.forms[0],${page.currentPage-1});">上一页</a>
				</c:if>
				<c:if test="${page.currentPage < page.totalPage }">
					<a href="javascript:page_nav(document.forms[0],${page.currentPage+1 });">下一页</a>
					<a href="javascript:page_nav(document.forms[0],${page.totalPage});">最后一页</a>
				</c:if>
				&nbsp;&nbsp;
			</ul>		
		 <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />页
	      <button type="button" class="page-btn" onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button> 
		</span>
		</div> 
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/rollpage.js"></script>
</html>