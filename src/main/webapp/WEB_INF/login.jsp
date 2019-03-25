<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户登录</title>
    <script type="text/javascript" src="static/jquery/jquery-3.3.1.js"></script>
</head>
<body>
    <div>
        <form id="login_form">
            <label>用户名:</label><input type="text" placeholder="请输入用户名" id="username">
            <label>密码:</label><input type="text" placeholder="请输入密码" id="password">
            <input type="button" id="login_button">
        </form>
    </div>
    <script type="text/javascript">
        $("login_button").click(function(){
            $.ajax({
                type:"post",
                url:"/doLogin",
                data:{
                    "username":$("username").val(),
                    "password":$("password").val()
                },
                dataType:"json",
                success:function (data) {
                    console.log(data);
                }
            })
        });
    </script>
</body>
</html>
