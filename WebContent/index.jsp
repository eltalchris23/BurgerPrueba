<!DOCTYPE html>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html:html lang="es">
	<head>
		<title>Iniciar Sesion</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="<c:url value='css/style.css'/>">
		<script src="<c:url value='http://code.jquery.com/jquery-1.11.1.min.js'/>"></script>
		<script src="<c:url value='js/dataTables.js'/>"></script>
		<script src="<c:url value='http://code.jquery.com/ui/1.11.2/jquery-ui.js'/>"></script>
		<script src="<c:url value='js/validate.js'/>"></script>
		<script src="<c:url value='js/functions.js'/>"></script>
	</head>
	<body>
		<form id="frmLogin" name="frmLogin" action="login.do" method="post">
			<h1>Registro</h1>
			<input type="text" id="usuario" name="usuario" placeholder="Ingresa tu Usuario" />
			<input type="password" id="clave" name="clave" placeholder="Ingresa tu Contraseña" />
			
			<%-- <input type="button" id="aceptar" value="Aceptar" /> --%>
			
			<input type="submit" value="Aceptar" />
			
			<div id="respuesta"></div> 
		</form>
	
	</body>
	
</html:html>


