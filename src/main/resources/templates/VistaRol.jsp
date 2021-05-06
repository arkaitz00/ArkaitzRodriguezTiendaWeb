<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear Rol</title>
<link rel="stylesheet" href="static/css/style.css">
</head>
<body>
	<div class="login">
		<h1>Crear rol</h1>
	    <form method="post" action="CrearRol">
	    	<input type="text" name="idRol" placeholder="Id rol" required="required" />
	        <input type="text" name="nombreRol" placeholder="Nombre del rol" required="required" />
	        <button type="submit" class="btn btn-primary btn-block btn-large">Crear rol</button>
	    </form>
	    <br>
	    <form method="post" action="Bienvenido.jsp">
	    	<button type="submit" class="btn btn-primary btn-block btn-large">Volver</button>
	    </form>
	</div>
</body>
</html>