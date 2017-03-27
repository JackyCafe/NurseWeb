<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="../css/jquery-confirm.css" rel="stylesheet">
<link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
<title>Jacky Big Company</title>

</head>
<body>
 	<Table>
 	<th>員工編號</th>
 	<th>員工姓名 </th>
 	<th>修改時間</th>
 	<th>站點</th>
 	<th></th> 
	<c:if test="${not empty nurses}">

		<c:forEach var="row" items="${nurses}">
			<c:forEach var = "row2" items="${row.operBean }">
			<tr>
			<td><c:out value="${row.nurseId }" /> </td>
			<td><c:out value="${row.name }" /> </td>
			<td><c:out value="${row.updateTime }" /> </td>
 			<td><c:out value="${row2.name }" /> </td>
 			<td><a href="oper.controller?operid=<c:out value="${row2.operId }" />&oper_action=delete"
			class='btn btn-default'
			>delete</a></td> 
			</tr>
			</c:forEach>
		</c:forEach>
	</c:if>
	 </Table>
	
</body>
</html>