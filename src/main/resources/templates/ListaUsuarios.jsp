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
				<table style="width:100%; margin-top:5%; text-align:center; ">
					<tr>
						<th>Correo</th>
						<th>Nombre</th>
						<th>Primer Apellido</th>
						<th>Segundo Apellido</th>
						<th>Direccion</th>
						<th>Localidad</th>
						<th>Provincia</th>
						<th>Telefono</th>
						<th>Dni</th>
					</tr>
					<c:forEach items="${sessionScope.listaUsuarios}" var="usuario" varStatus="status" begin="0" end="${sessionScope.listaUsuarios.size() - 1}">
					<tr>
						<td style="margin:1%;"><c:out value="${usuario.email}"></c:out></td>
						<td style="margin:1%;"><c:out value="${usuario.nombre}"></c:out></td>
						<td style="margin:1%;"><c:out value="${usuario.apellido1}"></c:out></td>
						<td style="margin:1%;"><c:out value="${usuario.apellido2}"></c:out></td>
						<td style="margin:1%;"><c:out value="${usuario.direccion}"></c:out></td>
						<td style="margin:1%;"><c:out value="${usuario.localidad}"></c:out></td>
						<td style="margin:1%;"><c:out value="${usuario.provincia}"></c:out></td>
						<td style="margin:1%;"><c:out value="${usuario.telefono}"></c:out></td>
						<td style="margin:1%;"><c:out value="${usuario.dni}"></c:out></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<%@ include file="Footer.jsp" %>
		</div>
	</body>
</html>