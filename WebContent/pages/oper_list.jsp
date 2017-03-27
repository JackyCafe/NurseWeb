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
 	<th>Oper Id</th>
 	<th>name </th>
 	<th>update time</th>
 	<th></th>
 	<th></th> 
	<c:if test="${not empty select}">

		<c:forEach var="row" items="${select}">
			<tr>
			<td><c:out value="${row.operId }" /> </td>
			<td><c:out value="${row.name }" /> </td>
			<td><c:out value="${row.updateTime }" /> </td>
			<td><a href="update_oper.jsp" class='btn btn-default'>view</a></td>
			<td><a href="oper.controller?operid=<c:out value="${row.operId }" />&oper_action=delete"
			class='btn btn-default'
			>delete</a></td>
			</tr>
		</c:forEach>
	</c:if>
	 </Table>
	
</body>
</html>