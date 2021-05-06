<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="curso.java.tienda.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="grid-container">
			<%@ include file="Navegacion.jsp" %>
			<div class="Article">
				<table style="width:100%; margin-top:5%; text-align: center;">
					<tr>
						<th>Id Rol</th>
						<th>Nombre</th>
					</tr>
					<c:forEach items="${sessionScope.listaRoles}" var="rol" varStatus="status" begin="0" end="${sessionScope.listaRoles.size() - 1}">
					<tr>
						<td style="margin:1%;"><c:out value="${rol.id}"></c:out></td>
						<td style="margin:1%;"><c:out value="${rol.rol}"></c:out></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<%@ include file="Footer.jsp" %>
		</div>
	</body>
</html>