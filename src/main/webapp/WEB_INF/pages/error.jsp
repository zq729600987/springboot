<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>访问错误</title>
	</head>
	<body>
		<div style="margin: 100px auto; width:300px;height:120px;">
		<h1>系统提示</h1>
		<div style="margin:10px 0px;">${message}</div>
		</div>
	</body>
</html>