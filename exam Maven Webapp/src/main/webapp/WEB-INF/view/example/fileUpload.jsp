<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'example.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<form action="/exam/example" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="to" value="fileupload">
		userShowName:<input type="text" name="userShowName" ><br>
		Age:<input type="text" name="age" ><br>
		userImage:<input type="file" name="userImage" ><br>
		userPhone:<input type="text" name="userPhone" ><br>
		userEmail:<input type="text" name="userEmail" ><br>
		userType:<input type="text" name="userType" ><br>
		<input type="submit"  value="提交"><br>
	</form>
  </body>
</html>
