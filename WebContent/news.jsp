<%@ page language="java" import="java.util.*, com.jjj.service.*,com.jjj.bean.*" pageEncoding="GB18030"%>



<%
List<news> categories = CrimeService.getInstance().getnews();
 %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ProductSearch.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/table.css">
	
	
	<script type="text/javascript" href="table.js">
	</script>
	
  </head>
  
  <body>

    
  	<p align="center"><SCRIPT language=JavaScript>

   today = new Date();  
   var date;
   var centry ;   
   centry="";
    if  (today.getFullYear()<2000 )  
    centry = "19" ; 
    date1 = centry + (today.getFullYear())  + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日  " ; 
   date2 = "";
   document.write( date1+date2);

 </SCRIPT></p>
    
     <table border="1" id="crime">
     <thead>
       <tr>
          <td>id</td>
          <td>新闻标题</td>
          <td>新闻内容</td>
           <td>发布时间</td>
       </tr>
       </thead>
         <%
         for(int i=0; i<categories.size(); i++){ 
        	 news parent = categories.get(i);
         %>
        	<tr>
               <td width:10px><%=parent.getId() %></td>
               <td><%=parent.getNewscap() %></td>
               <td><div  style="width: 200px; height: 25px;text-overflow:ellipsis; white-space:nowrap;overflow:hidden"><%=parent.getNewcaption() %></div></td>
               <td><%=parent.getData() %></td>
               <%-- <td><div  style="width: 200px; height: 25px;text-overflow:ellipsis; white-space:nowrap;overflow:hidden"><%=parent.getDesc() %></div></td> --%>
           </tr>
	       <% 
    		}
    		%>  
           
          
       
   </table>
    <br>
    <br>
    <br>
    <br>
    <br>
    
    
  </body>
</html>
