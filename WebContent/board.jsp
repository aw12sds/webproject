<%@ page language="java" import="java.util.*, com.jjj.service.*,com.jjj.bean.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    List<pass> categories = CrimeService.getInstance().getpass();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("username")==null)
{
	response.sendRedirect("404.jsp");
}
 %>

<br>
 <%
         for(int i=0; i<categories.size(); i++){ 
        	 pass parent = categories.get(i);
         %>
         
         <%=parent.getPass() %>
           <% 
    		}
    		%>  
</body>
</html>