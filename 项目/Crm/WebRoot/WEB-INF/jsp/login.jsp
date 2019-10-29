<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- font files  -->
<link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Nunito:400,300,700' rel='stylesheet' type='text/css'>
<!-- /font files  -->
<!-- css files -->
<link href="../css/style.css" rel='stylesheet' type='text/css' media="all" />
</head>
<body>


<h1>欢迎来到客户信息管理系统</h1>
<div class="log">
	<div class="content1">
		<h2>登录</h2>
		 <form action="${pageContext.request.contextPath }/user/login.action"
		method="post">
			<input type="text" name="username" value="用户名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '用户名';}">
			<input type="password" name="userpassword" value="PASSWORD" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'PASSWORD';}">
			<div class="button-row">
				<input type="submit" class="register" value="Login">				
				<div class="clear"></div>
			</div>
		</form>
	</div>
	<div class="content2">
		<h2>注册</h2>
			${message }
		   <form action="${pageContext.request.contextPath }/user/register.action"
		method="post">
			<input type="text" name="username" value="请输入用户名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入用户名';}">
			<input type="password" name="userpassword" value="PASSWORD" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'PASSWORD';}">
			<input type="tel" name="nickname" value="请输入昵称" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入昵称';}">	
			<input type="submit" class="register" value="Register">
		</form>
	</div>
	<div class="clear"></div>
</div>





</body>
</html>