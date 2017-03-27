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
<title>Jacky Big Company</title>


</head>
<body>
	<a href ="new_oper.jsp" class="btn btn-default" >新增站點</a><br>
	<a href ="oper.controller?oper_action=list" class='btn btn-default'>站點列表</a><br>
	<a href ="new_nurse.jsp" class="btn btn-default">新增護士</a><br>
	<a href ="nurse.controller?nurse_action=list" class="btn btn-default">護士列表</a><br>
	
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>