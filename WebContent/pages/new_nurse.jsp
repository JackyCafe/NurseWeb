<%@page import="model.service.OperService"%>
<%@page import="model.dao.OperBeanDAO"%>
 <%@page import="org.hibernate.SessionFactory"%>
<%@page import="model.dao.NurseBeanDAO"%>
<%@page import="model.service.NurseService"%>
<%@page import="javax.servlet.ServletContext"%>
<%@page import=" org.springframework.context.ApplicationContext"%>
<%@page import=" org.springframework.web.context.WebApplicationContext"%>
<%@page import=" org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style>
#container {
	position: absolute;
	top: 100px;
	left: 50px;
	width: 500px;
	height: 300px;
}

#to {
	position: absolute;
	top: 100px;
	left: 250px;
	width: 100px;
	height: 150px;
}

#from {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100px;
	height: 150px;
}

#add {
	position: absolute;
	top: 100px;
	left: 175px;
	width: 50px;
}

#remove {
	position: absolute;
	top: 150px;
	left: 175px;
	width: 50px;
}
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#add").click(function() {
					doAdd()
				})
				$("#remove").click(function() {
					doRemove()
				})

				function doAdd() {
					$('#from option:selected').each(
							function() {
								var str = $(this).text();
								$('#to').append(
										$("<option></option>").text(str))
								$('#from option:selected').remove()

							})

				}

				function doRemove() {
					$('#to option:selected').each(
							function() {
								var str = $(this).text();
								$('#from').append(
										$("<option></option>").text(str))
								$('#to option:selected').remove()

							})

				}

			});
</script>
<title>Jacky Big Company</title>
</head>
<body>
	<%
		ServletContext app = this.getServletContext();
		ApplicationContext context =
			WebApplicationContextUtils.getWebApplicationContext(app);
 		NurseService nurseService = (NurseService) context.getBean("nurseService");
		OperService operService = (OperService) context.getBean("operService");
		List<OperBean> opers = operService.select();
		request.setAttribute("opers", opers);
	%>

	<form action="nurse.controller" method='post' id='nurseform'>
		<a href="index.jsp" class="btn btn-default">返回</a>
		 <input
			type="submit" name="nurse_action" class="btn btn-default"
			value="Insert"/><br /> 
			<label for="nurse_id">員工編號</label> 
			<input  type ="text" id="nurse_id" name="nurse_id" ><br /> 
			<label for="nurse_name">護士姓名</label>
			 <input type ="text" id="nurse_name" name="nurse_name"><br />
			 <div id='container'>
 				<select name="from" multiple="multiple" id='from' >
				<c:forEach var="i" items="${opers}">
					<option selected value=<c:out value="${ i.operId}" />>
						<c:out value="${ i.name}" /></option>
				</c:forEach>
			</select>
			</div> 
			
	</form>		

		 	<button id='add' class="btn btn-default">>></button>
			<button id='remove' class="btn btn-default"><<</button>
			 <select name="list" multiple="multiple" id='to'>

			</select> 

 
 	


</body>
</html>