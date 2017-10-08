<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
<form  action="log">

<table align="center" valign="center">
     <tr><th>user:</th><th><input type="text" name="username" /></th></tr> 
     <tr><th>pass:</th><th><input type="password" name="password" /></th></tr>
       <tr><th> <input type="submit" value="log" /> </tr>
        <!--向服务器发送的数据，必须有name属性，提交按钮就不必写name-->
        </table>
        
    </form>
</div>


</body>
</html>