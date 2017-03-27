<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix ="fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.min.js"></script>
<title>Jacky Big Company</title>
</head>
<body>

	<form action='<c:url value="/pages/oper.controller" />' method="post">
		<a href="index.jsp" class="btn btn-default">返回</a>
		<input type ="submit" name="oper_action" class="btn btn-default" value="Insert"></input><br/>
		<label for ="oper_name">站點名稱</label>
		<input text="" id= "oper_name"name="oper_name" value=" "><br/>
 	 </form>

   
</body>
</html>