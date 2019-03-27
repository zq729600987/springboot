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
    <script type="text/javascript" src="static/js/jquery/jquery-3.3.1.js"></script>
</head>
<body>
    <div>
        <form id="login_form">
            <div>
                <label>用户名:</label><input type="text" placeholder="请输入用户名" id="username">
            </div>
            <div>
                <label>密码:</label><input type="text" placeholder="请输入密码" id="password">
            </div>
            <input type="button" id="submit" value="登录">
        </form>
    </div>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#submit").click(function(){
                $.ajax({
                    type:"post",
                    url:"/doLogin",
                    data:{
                        "username":$("#username").val(),
                        "password":$("#password").val()
                    },
                    dataType:"json",
                    success:function (data) {
                        console.log(data);
                    }
                })
            });
        });
    </script>
</body>
</html>
